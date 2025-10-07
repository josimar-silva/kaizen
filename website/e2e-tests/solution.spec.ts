import { test, expect } from "./_shared/fixtures";

test.describe("Solution Page", () => {
  test("should render inline code correctly without raw backticks", async ({
    page,
  }) => {
    // This page's analysis has `RandomWord.java` in backticks.
    await page.goto("/solution/2025-09-14/0");

    // The raw markdown with backticks should not be found as plain text.
    await expect(page.getByText("`RandomWord.java`")).not.toBeVisible();

    // Instead, it should be rendered as a <code> element.
    const codeElement = page.locator("code", { hasText: "RandomWord.java" });
    await expect(codeElement.first()).toBeVisible();
  });
});
