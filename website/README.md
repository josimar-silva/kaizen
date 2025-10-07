# Kaizen Website
[![Continuous Integration (website)](https://github.com/josimar-silva/kaizen/actions/workflows/ci-website.yaml/badge.svg)](https://github.com/josimar-silva/kaizen/actions/workflows/ci-website.yaml)
[![Continuous Delivery (website)](https://github.com/josimar-silva/kaizen/actions/workflows/cd-website.yaml/badge.svg)](https://github.com/josimar-silva/kaizen/actions/workflows/cd-website.yaml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=josimar-silva_kaizen&metric=alert_status&token=1f8759fd6f5423af2e4ff724229f3a680daab6ba)](https://sonarcloud.io/summary/new_code?id=josimar-silva_kaizen)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=josimar-silva_kaizen&metric=coverage&token=1f8759fd6f5423af2e4ff724229f3a680daab6ba)](https://sonarcloud.io/summary/new_code?id=josimar-silva_kaizen)

This is the frontend for the Kaizen project, a clean and minimalist journal designed to visualize a daily history of algorithm and system design practice. 
It consumes a `data.json` file to render a GitHub-style activity calendar and a detailed timeline.

## ✨ Features

- **Calendar Heatmap**: A GitHub-style contribution graph providing an at-a-glance overview of daily activity. Darker cells indicate more contributions.
- **Timeline View**: A reverse chronological feed of all entries, showing titles, notes, and the programming language used.
- **Algorithm Detail Modal**: A modal view that displays the full notes, language, and a link to the code on GitHub for each entry.
- **Filtering and Searching**: The timeline can be searched by title or notes and filtered by programming language and entry type (algorithm or system design).
- **Statistics**: The header displays key stats, including total entries, active days, and the current/longest streak.
- **Responsive Design**: The interface is fully responsive and optimized for both desktop and mobile devices.

## 🚀 Tech Stack

- **Framework**: [Next.js](https://nextjs.org/) (React) & [TypeScript](https://www.typescriptlang.org/)
- **Styling**: [Tailwind CSS](https://tailwindcss.com/) with [shadcn/ui](https://ui.shadcn.com/) components.
- **Animations**: [Framer Motion](https://www.framer.com/motion/) for smooth transitions.
- **Linting & Formatting**: [ESLint](https://eslint.org/) & [Prettier](https://prettier.io/)
- **Testing**: [Jest](https://jestjs.io/) for unit tests and [Playwright](https://playwright.dev/) for end-to-end tests.
- **Package Manager**: [npm](https://www.npmjs.com/)
- **Task Runner**: [Just](https://just.systems/)

## 🏁 Getting Started

Follow these instructions to get the project running on your local machine.

### Prerequisites

- [Node.js](https://nodejs.org/) (v22.x or later)

### Installation

1.  Clone the repository.
2.  Navigate to the `website` directory:
    ```bash
    cd website
    ```
3.  Install the dependencies using `npm`:
    ```bash
    npm install
    ```
    Alternatively, you can use the `just` command:
    ```bash
    just install
    ```

### Running the Development Server

To start the development server, run:

```bash
just dev
```

The application will be available at [http://localhost:3000](http://localhost:3000).

## 🛠️ Available Scripts

This project uses `just` as a command runner for convenience.

- `just dev`: Starts the development server with Turbopack.
- `just build`: Builds the application for production.
- `just start`: Starts the production server (requires a build first).
- `just check`: Runs both linting and formatting checks.
- `just lint`: Lints the codebase using ESLint.
- `just format`: Formats the code using Prettier and fixes linting issues.
- `just test`: Runs all unit tests with Jest.
- `just test-e2e`: Builds the app and runs all end-to-end tests with Playwright.

## 📊 Data Flow

The frontend is statically generated and relies on a pre-build step to process the data.

1.  The raw contribution data is stored in `public/data.json`.
2.  Before the Next.js build or development server starts, the `scripts/pre-build.js` script runs automatically.
3.  This script reads `public/data.json`, transforms the `type_of` field (e.g., `algo` to `algorithm`), and generates a new file at `src/lib/db.ts`.
4.  The application then imports the data directly from `src/lib/db.ts`, ensuring the data is statically included in the build.

This process avoids fetching and parsing JSON on the client side, improving performance.

## 🧪 Testing

The project includes both unit and end-to-end tests.

- **Unit Tests**: Run all unit tests using Jest:
  ```bash
  just test
  ```
- **End-to-End Tests**: Run all E2E tests using Playwright. This command will first build the application and then run the tests against the production build.
  ```bash
  just test-e2e
  ```

## 🚢 Deployment

This is a static Next.js application.

### Static Export

To create a production-ready static build, run:

```bash
just build
```

The output will be generated in the `dist/website` directory. You can serve this directory with any static file server.

### Cloudflare Pages

Check the [Releasing](./RELEASING.md) doc for information about releasing to Clouflare Pages.

### Docker

A `Dockerfile` is included for containerized deployments using Nginx.

1.  **Build the Docker image**:
    ```bash
    just build-image
    ```
2.  **Run the container**:
    ```bash
    just start-container
    ```
The application will be available at [http://localhost:3000](http://localhost:3000).

## Want to contribute?

Check the [contributing](CONTRIBUTING.md) guidelines.

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](../LICENSE) file for details.
