# Kaizen CLI

`kaizen` is a Rust-based command-line interface (CLI) tool designed to parse Git commit messages from a specified repository and generate a structured JSON data file.

## Features

-   **Git Commit Parsing:** Extracts structured information from Git commit messages that adhere to a specific convention.
-   **Data Extraction:** Captures commit date, title, developer notes (including multiline notes), programming language, type of entry (algorithm or system design), and generates a direct link to the commit on GitHub.
-   **JSON Output:** Produces a well-formatted JSON file (`data.json`) grouped by date, ready for consumption by the frontend.
-   **Remote Repository Support:** Can clone and parse commits from remote Git repositories.

## Commit Convention

For `kaizen-parser` to correctly extract information, Git commit messages must follow this structured format:

```
algo(<date>): <title>
notes: <developer’s notes (can span multiple lines)>
language: <programming language>
```

Or for system design entries:

```
sysdes(<date>): <title>
notes: <developer’s notes (can span multiple lines)>
language: <programming language>
```

**Rules:**
-   The first line must start with `algo(<date>): <title>` or `sysdes(<date>): <title>`.
    -   `<date>` should be in `YYYY-MM-DD` format.
    -   `<title>` is the name of the algorithm or system design topic.
-   Lines starting with `notes:` contain the developer’s notes. These can span multiple lines until another section (like `language:`) or the end of the message.
-   The line starting with `language:` specifies the programming language used.

## Usage

### From a Release

You can download pre-compiled binaries for various platforms directly from the [GitHub Releases page](https://github.com/josimar-silva/kaizen/releases).

1.  Go to the releases page and find the version you want to use.
2.  Under the "Assets" section, download the archive (`.zip` for Windows, `.tar.gz` for Linux/macOS) that matches your operating system and architecture.
3.  Extract the archive. You will find the `parser` executable inside.
4.  (For Linux/macOS) Make the binary executable:
    ```bash
    chmod +x parser
    ```
5.  Run the parser:
    ```bash
    ./parser parse --repository <repository_url_or_path> --output <output_file_path>
    ```

    **Example:**
    ```bash
    ./parser parse \
        --repository https://github.com/josimar-silva/kaizen \
        --output kaizen.json
    ```

#### Choosing the right binary

On Linux, we provide binaries compiled against both `glibc` and `musl`.

*   **`glibc` binaries (e.g., `kaizen-*-linux-x64-glibc.tar.gz`)**: These are smaller and are the standard for most Linux distributions like Ubuntu, Fedora, and Debian. However, they are dynamically linked to `glibc`, so you might encounter a `GLIBC` version mismatch error if you run them on a very old Linux distribution. We build them on Ubuntu 20.04 to ensure compatibility with a wide range of systems.

*   **`musl` binaries (e.g., `kaizen-*-linux-x64-musl.tar.gz`)**: These are fully static binaries with no external dependencies. They are larger but offer maximum portability and should work on any Linux distribution, regardless of its age.

**Recommendation:** If you are unsure, or if you encounter any library-related errors with the `glibc` binary, we recommend using the `musl` binary.

### From Source

#### Build the Project

Navigate to the `kaizen-parser` directory and build the project using Cargo:

```bash
cargo build
# For a release build:
cargo build --release
```

#### Run the CLI

The executable will be located in `target/debug/parser` (or `target/release/parser` for release builds).

```bash
./target/debug/parser parse --repository <repository_url_or_path> --output <output_file_path>
```

**Example with a remote repository:**

```bash
./target/debug/parser parse \
    --repository https://github.com/josimar-silva/kaizen \
    --output kaizen.json
```

**Example with a local repository (for testing):**

```bash
./target/debug/parser parse \
    --repository /path/to/your/local/git/repo \
    --output data.json
```


## Output Format

The CLI generates a JSON file with commit data grouped by date. Example:

```json
{
  "2025-09-08": [
    {
      "title": "Two Sum",
      "notes": "Started with brute force O(n^2), optimized with a hash map to O(n).",
      "link": "https://github.com/josimar-silva/kaizen/commit/abc001",
      "language": "Rust",
      "type_of": "algo"
    },
    {
      "title": "Web Crawler",
      "notes": "First draft.\nWill improve it later.",
      "link": "https://github.com/josimar-silva/kaizen/commit/abc002",
      "language": "Go",
      "type_of": "sysdes"
    }
  ],
  "2025-09-09": [
    {
      "title": "Binary Search",
      "notes": "Recursive solution.",
      "link": "https://github.com/josimar-silva/kaizen/commit/abc004",
      "language": "Python",
      "type_of": "algo"
    }
  ]
}
```

## Development and Testing

To run all tests (unit, integration, and end-to-end):

```bash
cargo test 
```

## Want to contribute?

Check the [contributing](CONTRIBUTING.md) guidelines.

## Releasing

For details on how to release a new version of this project, see the [releasing](RELEASING.md) guidelines.
