import { defineConfig, devices } from "@playwright/test";
import type { GitHubActionOptions } from "@estruyf/github-actions-reporter";

/**
 * Read environment variables from file.
 * https://github.com/motdotla/dotenv
 */
// require('dotenv').config();

/**
 * See https://playwright.dev/docs/test-configuration.
 */
export default defineConfig({
  testDir: "./e2e-tests",
  /* Run tests in files in parallel */
  fullyParallel: true,
  /* Fail the build on CI if you accidentally left test.only in the source code. */
  forbidOnly: !!process.env.CI,
  /* Retry on CI only */
  retries: process.env.CI ? 2 : 0,
  /* Opt out of parallel tests on CI. */
  workers: process.env.CI ? 1 : undefined,
  timeout: 60_000,
  /* Reporter to use. See https://playwright.dev/docs/test-reporters */
  reporter: [
    ["list"],
    [
      "@estruyf/github-actions-reporter",
      <GitHubActionOptions>{
        title: "E2E Tests",
        useDetails: true,
        showError: true,
      },
    ],
    [
      "monocart-reporter",
      {
        name: "UI Coverage",
        outputFile: "./e2e-tests-report/index.html",
        // global coverage report options
        coverage: {
          reports: [["lcovonly", { file: "ui-lcov.info" }]],
          entryFilter: () => true,
          sourceFilter: (sourcePath: string) =>
            sourcePath.search(/src\/.+/) !== -1,
        },
      },
    ],
  ],
  /* Shared settings for all the projects below. See https://playwright.dev/docs/api/class-testoptions. */
  use: {
    /* Base URL to use in tests */
    baseURL: "http://localhost:3000",
    /* Collect trace when retrying the failed test. See https://playwright.dev/docs/trace-viewer */
    trace: "on-first-retry",
    screenshot: "off",
  },

  /* Configure projects for major browsers */
  projects: [
    {
      name: "chromium",
      use: { ...devices["Desktop Chrome"] },
    },

    {
      name: "firefox",
      use: { ...devices["Desktop Firefox"] },
    },

    {
      name: "webkit",
      use: { ...devices["Desktop Safari"] },
    },

    /* Test against mobile viewports. */
    {
      name: "mobile-chrome",
      use: { ...devices["Pixel 5"] },
    },
    {
      name: "mobile-safari",
      use: { ...devices["iPhone 12"] },
    },

    /* Test against branded browsers. */
    {
      name: "microsoft-edge",
      use: { ...devices["Desktop Edge"], channel: "msedge" },
    },
    {
      name: "google-chrome",
      use: { ...devices["Desktop Chrome"], channel: "chrome" },
    },
  ],

  /* Run your local dev server before starting the tests */
  webServer: {
    command: "npm run serve",
    url: "http://localhost:3000",
    reuseExistingServer: !process.env.CI,
  },
});
