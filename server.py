from fastmcp import FastMCP
import subprocess
import xml.etree.ElementTree as ET
import os

mcp = FastMCP()

# -----------------------------
# Git MCP Tools
# -----------------------------

@mcp.tool()
def git_status(repo_path: str) -> str:
    """Return git status including staged/unstaged changes and conflicts."""
    try:
        result = subprocess.run(
            ["git", "status", "--short"], cwd=repo_path,
            capture_output=True, text=True
        )
        return result.stdout if result.stdout else "Clean working tree."
    except Exception as e:
        return f"Git Status Error: {e}"

@mcp.tool()
def git_add_all(repo_path: str) -> str:
    """Stage changes but exclude build artifacts."""
    exclude = ["target", ".venv", ".idea"]
    for ex in exclude:
        subprocess.run(
            ["git", "rm", "-r", "--cached", ex],
            cwd=repo_path,
            stderr=subprocess.DEVNULL
        )
    subprocess.run(["git", "add", "-A"], cwd=repo_path)
    return "Changes staged."

def get_coverage_percent(xml_path: str) -> str:
    if not os.path.exists(xml_path):
        return "N/A"
    tree = ET.parse(xml_path)
    root = tree.getroot()
    counters = root.find(".//counter[@type='INSTRUCTION']")
    if counters is None:
        return "N/A"
    covered = int(counters.get("covered"))
    missed = int(counters.get("missed"))
    total = covered + missed
    percent = (covered / total) * 100
    return f"{percent:.1f}%"

@mcp.tool()
def git_commit(repo_path: str, message: str) -> str:
    """Commit staged changes with coverage info added to message."""
    coverage = get_coverage_percent(
        os.path.join(repo_path, "target/site/jacoco/jacoco.xml")
    )
    full_msg = f"{message}\nCoverage: {coverage}"
    result = subprocess.run(
        ["git", "commit", "-m", full_msg],
        cwd=repo_path, capture_output=True, text=True
    )
    return result.stdout or result.stderr

@mcp.tool()
def git_push(repo_path: str, remote: str = "origin") -> str:
    result = subprocess.run(
        ["git", "push", remote, "HEAD"],
        cwd=repo_path, capture_output=True, text=True
    )
    return result.stdout or result.stderr

@mcp.tool()
def git_pull_request(repo_path: str, base: str = "main",
                     title: str = "Automated PR",
                     body: str = "Automated pull request") -> str:
    try:
        result = subprocess.run(
            ["gh", "pr", "create", "--base", base,
             "--title", title, "--body", body],
            cwd=repo_path,
            capture_output=True, text=True
        )
        return f"PR created: {result.stdout}"
    except Exception as e:
        return f"PR Error: {e}"
# -----------------------------
# Maven MCP Tools
# ----------------------------- 

@mcp.tool()
def run_maven_tests(repo_path: str) -> str:
    """Run Maven tests and return the result."""
    try:
        result = subprocess.run(
            ["mvn", "test"], cwd=repo_path,
            capture_output=True, text=True
        )
        return result.stdout or result.stderr
    except Exception as e:
        return f"Error running mvn test: {e}"


@mcp.tool()
def generate_boundary_tests(repo_path: str, class_name: str, method_name: str, param_type: str) -> str:
    """Generate boundary value tests for a given Java method."""
    test_code = f"""
@Test
void test_{method_name}_boundaries() {{
    {class_name} obj = new {class_name}();
    // Lower bound
    assertDoesNotThrow(() -> obj.{method_name}({param_type}.MIN_VALUE));
    // Nominal
    assertDoesNotThrow(() -> obj.{method_name}(0));
    // Upper bound
    assertDoesNotThrow(() -> obj.{method_name}({param_type}.MAX_VALUE));
}}
"""
    test_file = os.path.join(repo_path, "src", "test", "java", f"{class_name}BoundaryTest.java")
    with open(test_file, "a") as f:
        f.write(test_code)
    return f"Boundary test added to {test_file}"

# -----------------------------
# Calculator Tool (Phase 1)
# -----------------------------
@mcp.tool()
def calculator(expression: str) -> str:
    """Evaluate a basic math expression."""
    try:
        return str(eval(expression))
    except Exception as e:
        return f"Error: {e}"

if __name__ == "__main__":
    mcp.run(transport="sse")
