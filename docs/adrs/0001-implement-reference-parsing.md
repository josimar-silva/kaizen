# ADR 0001: Implement Reference Parsing from Commits

*   **Date**: 2025-09-26
*   **Status**: Approved

## Context

The Kaizen project currently parses commit messages to extract information about completed algorithm and system design challenges. This information is then displayed on the website.

Users have requested the ability to include a reference link in their commit messages and have this link be easily accessible on the website. This would allow them to link to the problem description, a discussion forum, or any other relevant resource.

## Decision

We will implement a new feature to parse a `Ref.:` field from the commit message and display it as a "Reference" button on the website.

This will involve the following changes:

1.  **`kaizen-parser`**:
    *   Update the commit message parsing logic in `parser.rs` to extract the URL from the `Ref.:` field.
    *   Add a new `reference` field to the `CommitData` struct in `commit.rs`.

2.  **`website`**:
    *   Update the `Timeline.tsx` component to display a "Reference" button next to the "Code" button for each algorithm entry.
    *   Update the `AlgorithmDetailModal.tsx` component to include the "Reference" button in the modal view.
    *   Update the `kaizen-data.ts` interface to include the new `reference` field.

## Consequences

*   **Pros**:
    *   Users will be able to easily access relevant resources for each algorithm.
    *   The project will be more useful as a personal learning journal.
*   **Cons**:
    *   This will require changes to both the parser and the website.
    *   The commit message format will be slightly more complex.
