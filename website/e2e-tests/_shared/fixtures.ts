import { test as testBase, expect } from "@playwright/test";
import { addCoverageReport } from "monocart-reporter";

const test = testBase.extend<{
  autoTestFixture: string;
}>({
  autoTestFixture: [
    async ({ page }, use, testInfo) => {
      const isChromium = testInfo.project.name === "chromium";

      // coverage API is chromium only
      if (isChromium) {
        await Promise.all([
          page.coverage.startJSCoverage({
            resetOnNavigation: false,
          }),
          page.coverage.startCSSCoverage({
            resetOnNavigation: false,
          }),
        ]);
      }

      await use("autoTestFixture");

      if (isChromium) {
        const [jsCoverage, cssCoverage] = await Promise.all([
          page.coverage.stopJSCoverage(),
          page.coverage.stopCSSCoverage(),
        ]);
        const coverageList = [...jsCoverage, ...cssCoverage];
        await addCoverageReport(coverageList, testInfo);
      }
    },
    {
      scope: "test",
      auto: true,
    },
  ],
});

export { test, expect };
