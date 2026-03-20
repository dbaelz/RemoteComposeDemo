# AGENTS for Remote Compose project

## Purpose
This document defines what an automated agent may do in the repository and how it should behave.
It is tailored to the current project and supports the workflow where the agent prepares changes locally and the user reviews and commits them manually.

## Scope
- Goal: help make targeted code changes, run and validate the project locally, create patches or change sets, and provide clear PR/commit descriptions for the human reviewer.
- Not in scope: automatically pushing commits to remote branches, publishing model files, exfiltrating secrets, or making production deployments.

## Agent persona & permissions
- Operates on the repository in the local workspace only.
- May edit files in-place in the working tree on branch `main` (or whichever branch is checked out locally), but must never push to the remote or open PRs automatically.
- Must produce a patch artifact (diff/patch file) or an explicit list of changed files + suggested commit message and PR description for the human to review and commit.

## Environment & how to run
- Project type: Monorepo with multiple Gradle modules for server, clients, and shared code.
- Useful commands (run locally; agent should include these in instructions rather than executing remote actions):
  - Start server application: ./gradlew :server:run

## Project overview
This is a demo project for [Remote Compose](https://developer.android.com/jetpack/androidx/releases/compose-remote).
Remote Compose allows you to run a Compose UI on a remote server and display it on a client device.
Currently, the library supports only Android. Nevertheless, the project includes a desktop client to implement it when it's supported.
This project includes a Ktor server application that serves the Compose UI and two client applications: one for Android and one for desktop (JVM).


## Repo most important modules and  files (key files & symbols)
- `README.md` — project summary and basic information about the project
- `server` directory: Server code. The server is a Ktor application
- `composeApp` directory: Kotlin Multiplatform client supporting Android and desktop
- `shared` directory: Contains shared code for client and server

## Core rules
The agent must follow these rules when making changes, running tests, and preparing patches for review. The agent should prioritize small, targeted changes that are easy to review and validate locally.

### Allowed actions (what the agent may do)
- Run local builds and tests in the developer environment and report results.
- Modify source and test files in the working tree to implement fixes or features.
- Generate a patch (uncommitted diff) capturing all modifications.
- Produce a suggested commit message and a concise PR description template.
- Create or update documentation files such as `README.md` or `AGENTS.md` itself.
- Provide concrete example HTTP requests for testing (curl/bruno snippets) and expected outputs.

### Forbidden actions (must never do)
- Push commits or open PRs on remote repositories without explicit human approval.
- Upload, download, or exfiltrate model files, voice WAVs, or any large binary assets to external services.
- Commit secrets (API keys, passwords) to the repository. If secrets are required for testing, document them as local-only and use environment variables or local config ignored in .gitignore.

### Patch workflow
Make changes and the user commits manually. When the agent makes changes, follow this exact handoff procedure:

1. Make the edits in the working tree. Do not commit or push.
2. Produce information about your changes and short description why you did them for the human reviewer
3. Provide a short checklist for the reviewer (how to reproduce tests, run the app, and verify the changes).
4. Optionally, include an annotated list of changed files with line ranges and a short explanation for each change.

### Failure handling and retries
- If a command fails (build/test/run), the agent must:
  1. Capture and report the full error output.
  2. Attempt 1-2 local fixes if they are small and low-risk (typos, imports, obvious test assertions) and re-run tests.
  3. If not resolvable, produce a clear failure report with next steps and return the uncommitted patch (if any). Do not attempt destructive changes.
