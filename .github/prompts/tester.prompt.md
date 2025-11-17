---
mode: "agent"
tools: ["git_status", "git_add_all", "run_maven_tests", "git_commit", "git_push", "git_pull_request", "calculator"]
description: "Automated test coverage improvement agent."
model: "gpt-5-mini"
---

## Objective
Increase test coverage in the Java Maven project located in the same workspace.

## Workflow
1 Run git_status  
2️ Run mvn test using run_maven_tests  
3️ Parse output and identify uncovered parts  
4️ Automatically generate new JUnit test files for uncovered methods  
5️ Use git_add_all to stage changes  
6️ Use git_commit with coverage-based commit messages  
7️ If coverage improved → git_push and git_pull_request  
8️ Repeat until coverage threshold > 90%


