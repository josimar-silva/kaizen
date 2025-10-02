import "@testing-library/jest-dom";

import { render, screen } from "@testing-library/react";
import { notFound } from "next/navigation";
import React from "react";

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

jest.mock("next/navigation", () => ({
  useRouter: () => ({
    push: jest.fn(),
  }),
  notFound: jest.fn(),
}));

describe("SolutionPage", () => {
  it("should render algorithm details correctly", () => {
    const props = {
      params: { date: "2023-01-01", index: "0" },
    };

    render(<SolutionPage {...props} />);

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
  });

  it("should call notFound for invalid index", () => {
    const props = {
      params: { date: "2023-01-01", index: "99" },
    };

    expect(() => render(<SolutionPage {...props} />)).toThrow();
    expect(notFound).toHaveBeenCalled();
  });
});
