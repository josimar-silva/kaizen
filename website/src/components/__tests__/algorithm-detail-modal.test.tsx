import "@testing-library/jest-dom";

import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import React from "react";

import StyleService from "@/lib/style-service";

import { AlgorithmDetailModal } from "../algorithm-detail-modal";

// Mock StyleService and utility functions
jest.mock("@/lib/style-service", () => ({
  __esModule: true,
  default: {
    getLanguageIcon: jest.fn((lang) => `icon-${lang}`),
    getLanguageClass: jest.fn((lang) => `class-${lang}`),
  },
}));

jest.mock("@/lib/utils", () => ({
  formatDate: jest.fn((date) => `FormattedDate(${date})`),
  formatRelativeDate: jest.fn((date) => `RelativeDate(${date})`),
  cn: jest.fn((...args) => args.filter(Boolean).join(" ")),
}));

// Mock dialog component
jest.mock("@/components/ui/dialog", () => ({
  Dialog: ({ children, open, onOpenChange }: any) => (
    <div data-testid="mock-dialog" data-isopen={open ? "true" : "false"}>
      <button onClick={() => onOpenChange(false)}>Close Dialog</button>
      {children}
    </div>
  ),
  DialogContent: ({ children, className }: any) => (
    <div data-testid="mock-dialog-content" className={className}>
      {children}
    </div>
  ),
  DialogDescription: ({ children }: any) => <p>{children}</p>,
  DialogHeader: ({ children }: any) => <div>{children}</div>,
  DialogTitle: ({ children }: any) => <h2>{children}</h2>,
}));

const mockAlgorithms = [
  {
    title: "Algo 1",
    notes: "Notes for Algo 1",
    link: "link1",
    language: "Java",
    type: "algorithm",
  },
  {
    title: "Algo 2",
    notes: "Notes for Algo 2",
    link: "link2",
    language: "Python",
    type: "algorithm",
  },
];

const mockAlgorithmsWithLongNote = [
  {
    title: "Algo 1",
    notes: "longword".repeat(50),
    link: "link1",
    language: "Java",
    type: "algorithm",
  },
];

describe("AlgorithmDetailModal", () => {
  const defaultProps = {
    isOpen: true,
    onClose: jest.fn(),
    date: "2023-10-26",
    algorithms: mockAlgorithms,
    initialAlgorithmIndex: 0,
  };

  it("should render correctly when open", () => {
    render(<AlgorithmDetailModal {...defaultProps} />);

    expect(screen.getByRole("heading", { name: "Algo 1" })).toBeInTheDocument();
    expect(screen.getByText("Notes for Algo 1")).toBeInTheDocument();
    expect(screen.getByTestId("main-language-badge")).toHaveTextContent("Java");
    expect(
      screen.getByRole("heading", { name: "FormattedDate(2023-10-26)" }),
    ).toBeInTheDocument();
    expect(screen.getByText("RelativeDate(2023-10-26)")).toBeInTheDocument();
    expect(screen.getByRole("link", { name: /Code/i })).toHaveAttribute(
      "href",
      "link1",
    );
  });

  it("should call onClose when dialog is closed", () => {
    const onClose = jest.fn();
    render(<AlgorithmDetailModal {...defaultProps} onClose={onClose} />);
    fireEvent.click(screen.getByText("Close Dialog")); // Use the mock close button
    expect(onClose).toHaveBeenCalledTimes(1);
  });

  it("should navigate to next algorithm", async () => {
    render(<AlgorithmDetailModal {...defaultProps} />);
    fireEvent.click(screen.getByRole("button", { name: /next algorithm/i }));
    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: "Algo 2" }),
      ).toBeInTheDocument();
    });
    expect(screen.getByText("Notes for Algo 2")).toBeInTheDocument();
    expect(screen.getByTestId("main-language-badge")).toHaveTextContent(
      "Python",
    );
  });

  it("should navigate to previous algorithm", async () => {
    render(
      <AlgorithmDetailModal {...defaultProps} initialAlgorithmIndex={1} />,
    );
    expect(screen.getByRole("heading", { name: "Algo 2" })).toBeInTheDocument();
    fireEvent.click(
      screen.getByRole("button", { name: /previous algorithm/i }),
    );
    await waitFor(() => {
      expect(
        screen.getByRole("heading", { name: "Algo 1" }),
      ).toBeInTheDocument();
    });
    expect(screen.getByText("Notes for Algo 1")).toBeInTheDocument();
    expect(screen.getByTestId("main-language-badge")).toHaveTextContent("Java");
  });

  it("should display correct algorithm count when multiple algorithms exist", () => {
    render(<AlgorithmDetailModal {...defaultProps} />);
    expect(screen.getByText("1 of 2")).toBeInTheDocument();
  });

  it("should not render navigation buttons if only one algorithm", () => {
    render(
      <AlgorithmDetailModal
        {...defaultProps}
        algorithms={[mockAlgorithms[0]]}
      />,
    );
    expect(
      screen.queryByRole("button", { name: /next algorithm/i }),
    ).not.toBeInTheDocument();
    expect(
      screen.queryByRole("button", { name: /previous algorithm/i }),
    ).not.toBeInTheDocument();
  });

  it("should render language icon and class", () => {
    render(<AlgorithmDetailModal {...defaultProps} />);
    expect(StyleService.getLanguageIcon).toHaveBeenCalledWith("Java");
    // Check if the icon is rendered with the correct class
    const languageBadge = screen.getByTestId("main-language-badge");
    expect(languageBadge.querySelector("i")).toHaveClass("icon-Java");
  });

  it("should have break-words class for long notes", () => {
    const props = {
      ...defaultProps,
      algorithms: mockAlgorithmsWithLongNote,
    };
    render(<AlgorithmDetailModal {...props} />);
    const notes = screen.getByText("longword".repeat(50));
    expect(notes).toHaveClass("break-words");
  });
});
