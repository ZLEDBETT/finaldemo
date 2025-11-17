# MCP Tool Documentation

## Git Tools
git_status = View current repo state

git_add_all = Stage all meaningful changes

git_commit = Commit changes with coverage info

git_push = Push to Github

git_pull_Request = Creates PR for review

## Testing Tools
run_maven_tests = Execute Maven test suite

generate_boundary_tests = Create boundary value tests for Java methods

## Development Tools
calculator = Evaluate mathematical expressions

## Current Status
✅ **App.java** - Core application with add() and multiply() methods
✅ **AppTest.java** - Basic unit tests (4 tests passing)
✅ **AppBoundaryTestMCP.java** - Comprehensive boundary tests (2 tests passing)
✅ **100% Code Coverage** - All methods thoroughly tested

--------------------------------------------------------------------
# Installation


Python, Version 3.11+

Java, Version 11+

Maven, Version 3.6+

Node.js, Version 18+

VS Code, Version Latest

-----------------------------------------------------------------------
# Setup Commands

uv init
uv venv
& .venv\Scripts\Activate.ps1   
uv pip install fastmcp httpx mcp

Verify:
python --version
mvn -version

cd finaldemo
python server.py

----------------------------------------------------------------------------
# Connect MCP to VS Code


CTRL + SHIFT + P -> MCP: Add Server
Server URL: http://127.0.0.1:8000/sse
Name: Testing Agent

-----------------------------------------------------------------------------
# Using MCP in VS Chat

## Recent Examples (Successfully Tested):

**Test Execution:**
- Use run_maven_tests on "C:\Users\zoele\OneDrive\Documents\College\SE333Final\finaldemo"

**Boundary Test Generation:**
- Use generate_boundary_tests on App.java (add method, Integer type)
- Use generate_boundary_tests on App.java (multiply method, Integer type)

**Calculator Tool:**
- Use calculator with "5 + 3" → Returns: 8
- Use calculator with "(15 + 5) / 2" → Returns: 10.0

**Git Workflow:**
- Use git_add_all on finaldemo
- Use git_commit on finaldemo with message "Add boundary tests for App class methods"
- Use git_push on finaldemo

## Legacy Examples:
Use generate_tests on "C:\…\codebase\codebase"

Use run_maven_tests on "C:\…\codebase\codebase"

Use git_add_all on codebase

Use git_commit on codebase with message "Improve coverage"

Use git_push on codebase

Use git_pull_request on codebase

