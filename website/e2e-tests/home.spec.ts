import { test } from "./_shared/fixtures";
import { expect } from "@playwright/test";

test.beforeEach(async ({ page }) => {
  await page.goto("/");
});

test.describe("Homepage", () => {
  test("should have title and subtitle", async ({ page }) => {
    await expect(page).toHaveTitle(/Kaizen/);
    await expect(page.getByTestId("title")).toHaveText("Kaizen");
    await expect(page.getByTestId("subtitle")).toHaveText(
      "Continuous Improvement Journal",
    );
    await expect(page.getByTestId("description")).toHaveText(
      "One algorithm a day, keeps the rust away",
    );
  });

  test("should display the correct footer version", async ({ page }) => {
    const currentYear = new Date().getFullYear();
    const expectedVersionText = `© ${currentYear} Josimar Silva. All rights reserved. v\\d+\\.\\d+\\.\\d+`;
    await expect(page.getByTestId("footer-copyright")).toHaveText(
      new RegExp(expectedVersionText),
    );
  });

  test("should display the 'Progress Overview' section with key stats", async ({
    page,
  }) => {
    await expect(page.getByText("Progress Overview")).toBeVisible();

    // Check for the 4 main stat cards
    await expect(page.getByTestId("avg-solution-description")).toHaveText(
      "Avg/Week",
    );
    await expect(page.getByTestId("best-streak-description")).toHaveText(
      "Best Streak",
    );
    await expect(page.getByTestId("first-commit-description")).toHaveText(
      "Since First Commit",
    );
    await expect(page.getByTestId("most-active-day-description")).toHaveText(
      "Most Active Day",
    );

    // Check for chart titles
    await expect(page.getByTestId("chart-types")).toHaveText("Types");
    await expect(page.getByTestId("chart-sources")).toHaveText("Sources");
    await expect(page.getByTestId("chart-languages")).toHaveText("Languages");
  });

  test("should display the GitHub icon link", async ({ page }) => {
    const githubLink = page.getByLabel("GitHub Repository");
    await expect(githubLink).toBeVisible();
    await expect(githubLink).toHaveAttribute(
      "href",
      "https://github.com/josimar-silva/kaizen",
    );
    await expect(githubLink).toHaveAttribute("target", "_blank");
    await expect(githubLink).toHaveAttribute("rel", "noopener noreferrer");
    await expect(githubLink.locator("svg")).toBeVisible();
  });
});
