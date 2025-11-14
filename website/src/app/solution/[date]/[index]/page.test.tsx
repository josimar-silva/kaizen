import "@testing-library/jest-dom";

import { act, render, screen } from "@testing-library/react";
import { Suspense } from "react";

import SolutionPage from "./page";

// Mock data and services
jest.mock("@/lib/db", () => ({
  kaizenData: {
    commits: {
      "2023-01-01": [
        {
          title: "Test Algo 1",
          notes: "Notes for Test Algo 1",
          link: "link1",
          language: "Java",
          type: "algorithm",
          reference: "https://example.com/link1",
          analysis: "/solution/test-algo-1-analysis.md",
        },
      ],
    },
  },
}));

jest.mock("@/lib/style-service", () => ({
  __esModule: true,
  default: {
    getLanguageIcon: jest.fn((lang: string) => `icon-${lang}`),
    getLanguageClass: jest.fn((lang) => `class-${lang}`),
  },
}));

jest.mock("@/components/analysis-section", () => ({
  __esModule: true,
  default: ({ analysisPath }: { analysisPath: string }) => (
    <div data-testid="mock-analysis-section">{`Mocked Analysis for ${analysisPath}`}</div>
  ),
}));

const mockNotFound = jest.fn(() => {
  throw new Error("NEXT_NOT_FOUND");
});

jest.mock("next/navigation", () => ({
  useRouter: () => ({
    push: jest.fn(),
  }),
  get notFound() {
    return mockNotFound;
  },
}));

describe("SolutionPage", () => {
  beforeEach(() => {
    mockNotFound.mockClear();
  });

  it("should render algorithm details correctly", async () => {
    const props = {
      params: Promise.resolve({ date: "2023-01-01", index: "0" }),
    };

    await act(async () => {
      render(
        <Suspense fallback={<div>Loading...</div>}>
          <SolutionPage {...props} />
        </Suspense>,
      );
    });

    expect(
      screen.getByRole("heading", { level: 2, name: "Test Algo 1" }),
    ).toBeInTheDocument();
    expect(screen.getByText("Notes for Test Algo 1")).toBeInTheDocument();
    expect(screen.getByText("Java")).toBeInTheDocument();
    expect(screen.getByTestId("reference-button")).toHaveAttribute(
      "href",
      "https://example.com/link1",
    );
    expect(screen.getByTestId("source-code-button")).toHaveAttribute(
      "href",
      "link1",
    );
    expect(screen.getByTestId("mock-analysis-section")).toBeInTheDocument();
    expect(
      screen.getByText("Mocked Analysis for /solution/test-algo-1-analysis.md"),
    ).toBeInTheDocument();
  });

  it("should call notFound for invalid index", async () => {
    const props = {
      params: Promise.resolve({ date: "2023-01-01", index: "99" }),
    };

    await expect(SolutionPage(props)).rejects.toThrow("NEXT_NOT_FOUND");

    expect(mockNotFound).toHaveBeenCalled();
  });
});
