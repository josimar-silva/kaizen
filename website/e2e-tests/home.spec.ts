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
});
