# Kaizen Parser - AGENTS.md

## Overview

The Kaizen Parser is a Rust-based CLI tool that processes Git commit messages following a specific convention to extract structured information about algorithm solutions and system design notes. It generates a `data.json` file consumed by the frontend website.

**Tech Stack:**
- Rust (edition 2024, MSRV 1.88.0)
- git2 (Git operations)
- serde/serde_json (serialization)
- regex (commit parsing)
- rayon (parallel processing)
- clap (CLI argument parsing)
- chrono (date/time handling)

## Build and Development Commands

### Build
```bash
# Development build
cargo build

# Release build (optimized)
cargo build --release --locked --no-default-features
just build  # Same as above
```

**Release Profile Optimizations:**
- LTO: fat (link-time optimization)
- Codegen units: 1 (better optimization)
- Panic: abort (smaller binary)

### Testing
```bash
# Run tests with coverage
cargo tarpaulin --out Lcov --all-features
just test  # Same as above
```

Coverage reports are generated in Lcov format for CI/CD integration.

### Linting
```bash
# Run Clippy linter
cargo clippy --all-targets -- -D warnings
just lint  # Same as above
```

All warnings are treated as errors (`-D warnings`).

### Formatting
```bash
# Format code
cargo fmt --all
just format  # Same as above
```

### Release Preparation
```bash
just pre-release
```

This command:
- Verifies git working directory is clean
- Runs `just lint` and `just test`
- Removes `-SNAPSHOT` suffix from version in `Cargo.toml`
- Updates `Cargo.lock` via `cargo update`
- Updates root `README.md` with new parser version
- Creates a release commit

## Code Style Guidelines

### Rust Style
- Follow standard Rust formatting (enforced by `cargo fmt`)
- Use Clippy recommendations (treated as errors)
- Prefer idiomatic Rust patterns
- Leverage `rayon` for parallel processing where appropriate

### Error Handling
- Use Result types for fallible operations
- Provide meaningful error messages
- Log errors appropriately using the `log` crate
- Use `env_logger` for configurable logging

### Dependencies
- Prefer well-maintained crates from crates.io
- Pin versions in `Cargo.toml` for reproducibility
- Use `features` to keep dependencies minimal
- OpenSSL vendored for non-Windows targets

## Project Structure

```
kaizen-parser/
├── src/
│   └── main.rs          # Main parser logic and CLI entry point
├── Cargo.toml           # Dependencies and project metadata
├── Cargo.lock           # Locked dependency versions
├── justfile            # Just command runner recipes
└── README.md           # Parser-specific documentation
```

## Binary Configuration

The parser binary is named `parser`:
```toml
[[bin]]
name = "parser"
path = "src/main.rs"
```

## CLI Usage

The parser uses `clap` for command-line argument parsing. Typical usage:

```bash
parser [OPTIONS]
```

Refer to `parser --help` for available options and arguments.

## Git Commit Convention

The parser expects commit messages to follow a specific convention to extract:
- Problem title
- Developer notes
- Programming language used
- Problem type (algorithm, system design, etc.)
- GitHub/repository links

Refer to the root AGENTS.md and project documentation for the exact commit message format.

## Testing Guidelines

### Unit Tests
- Write tests for parsing logic
- Test edge cases in commit message formats
- Verify JSON output structure
- Use `cargo test` for standard test runs
- Use `cargo tarpaulin` for coverage analysis

### Test Organization
- Co-locate tests with source code using `#[cfg(test)]`
- Use descriptive test names
- Follow AAA pattern (Arrange, Act, Assert)

## Development Workflow

1. Make changes to `src/main.rs` or add new modules
2. Run `just format` to format code
3. Run `just lint` to check for issues
4. Write tests for new functionality
5. Run `just test` to verify tests pass with coverage
6. Commit changes following conventional commits (see root AGENTS.md)

## Performance Considerations

- The parser uses `rayon` for parallel Git commit processing
- Release builds are optimized with LTO and single codegen unit
- Consider memory usage when processing large repositories
- Log level can be controlled via `RUST_LOG` environment variable

## Data Output

The parser generates a `data.json` file with structured information about:
- Daily algorithm solutions
- System design notes
- Programming languages used
- Links to problem sources
- Chronological timeline data

This file is consumed by the website frontend.

## Dependencies Overview

- **git2**: Git repository operations
- **serde/serde_json**: Data serialization to JSON
- **regex**: Parsing commit message patterns
- **rayon**: Parallel commit processing
- **clap**: CLI argument parsing
- **chrono**: Date/time handling and formatting
- **log/env_logger**: Logging infrastructure
- **ordermap**: Ordered HashMap with serde support
- **tempfile**: Temporary file handling
- **url**: URL parsing and validation

## Notes for AI Agents

- The main logic is in `src/main.rs` - this is a single-file CLI tool
- Commit message parsing relies heavily on regex patterns
- The parser must handle various date formats and commit structures
- Output JSON schema must remain compatible with the website frontend
- Use `RUST_LOG=debug` for verbose logging during development
- The `--locked` flag ensures reproducible builds with exact dependency versions
- Non-Windows builds vendor OpenSSL to avoid system dependency issues
- Always run tests with coverage to maintain code quality
- Follow Rust's ownership and borrowing principles strictly
