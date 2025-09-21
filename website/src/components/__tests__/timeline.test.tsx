import "@testing-library/jest-dom";

import {
  fireEvent,
  render,
  screen,
  waitFor,
  within,
} from "@testing-library/react";
import React from "react";

import { Timeline } from "../timeline";

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

// Mock Select component to provide accessible names
jest.mock("@/components/ui/select", () => {
  const Select = ({
    children,
    value,
    onValueChange,
    "data-testid": dataTestId,
  }: any) => {
    return (
      <div data-testid={dataTestId}>
        {React.Children.map(children, (child) => {
          if (React.isValidElement(child)) {
            // @ts-ignore
            return React.cloneElement(child, { onValueChange, value });
          }
          return child;
        })}
      </div>
    );
  };

  const SelectTrigger = ({ children }: any) => {
    return <button data-testid="mock-select-trigger">{children}</button>;
  };

  const SelectValue = ({ placeholder }: any) => {
    return <span data-testid="mock-select-value">{placeholder}</span>;
  };

  const SelectContent = ({ children, onValueChange }: any) => {
    return (
      <div data-testid="mock-select-content">
        {React.Children.map(children, (child) => {
          if (React.isValidElement(child)) {
            // @ts-ignore
            return React.cloneElement(child, { onValueChange });
          }
          return child;
        })}
      </div>
    );
  };

  const SelectItem = ({ children, value, onValueChange }: any) => {
    return (
      <div data-testid="mock-select-item" onClick={() => onValueChange(value)}>
        {children}
      </div>
    );
  };

  return {
    Select,
    SelectTrigger,
    SelectValue,
    SelectContent,
    SelectItem,
  };
});

const mockCommits = {
  "2023-01-03": [
    {
      title: "Algo 3",
      notes: "Notes 3",
      link: "link3",
      language: "Java",
      type: "algorithm",
    },
    {
      title: "Algo 4",
      notes: "Notes 4",
      link: "link4",
      language: "Python",
      type: "system-design",
    },
  ],
  "2023-01-01": [
    {
      title: "Algo 1",
      notes: "Notes 1",
      link: "link1",
      language: "Rust",
      type: "algorithm",
    },
  ],
  "2023-01-02": [
    {
      title: "Algo 2",
      notes: "Notes 2",
      link: "link2",
      language: "Go",
      type: "algorithm",
    },
  ],
};

describe("Timeline", () => {
  const defaultProps = {
    commits: mockCommits,
  };

  it("should render search and filter controls", () => {
    render(<Timeline {...defaultProps} />);
    expect(
      screen.getByPlaceholderText("Search algorithms, notes, or languages..."),
    ).toBeInTheDocument();
    expect(screen.getByTestId("type-select")).toBeInTheDocument();
    expect(screen.getByTestId("language-select")).toBeInTheDocument();
    expect(screen.getByTestId("sort-select")).toBeInTheDocument();
  });

  it("should display algorithms sorted by date by default", () => {
    render(<Timeline {...defaultProps} />);
    const titles = screen
      .getAllByRole("heading", { level: 3 })
      .map((h) => h.textContent);
    expect(titles).toEqual(["Algo 3", "Algo 4", "Algo 2", "Algo 1"]);
  });

  it("should filter by search query", async () => {
    render(<Timeline {...defaultProps} />);
    fireEvent.change(
      screen.getByPlaceholderText("Search algorithms, notes, or languages..."),
      { target: { value: "nonexistent" } },
    );
    await waitFor(() => {
      expect(screen.getByText("No entries found")).toBeInTheDocument();
    });
  });

  it("should filter by language", async () => {
    render(<Timeline {...defaultProps} />);
    const languageSelect = screen.getByTestId("language-select");
    fireEvent.click(within(languageSelect).getByTestId("mock-select-trigger"));
    const selectContent = within(languageSelect).getByTestId(
      "mock-select-content",
    );
    fireEvent.click(within(selectContent).getByText("Rust"));
    await waitFor(() => {
      expect(screen.getByText("Algo 1")).toBeInTheDocument();
      expect(screen.queryByText("Algo 2")).not.toBeInTheDocument();
    });
  });

  it("should filter by type", async () => {
    render(<Timeline {...defaultProps} />);
    const typeSelect = screen.getByTestId("type-select");
    fireEvent.click(within(typeSelect).getByTestId("mock-select-trigger"));
    const selectContent = within(typeSelect).getByTestId("mock-select-content");
    fireEvent.click(within(selectContent).getByText("System Design"));
    await waitFor(() => {
      expect(screen.getByText("Algo 4")).toBeInTheDocument();
      expect(screen.queryByText("Algo 1")).not.toBeInTheDocument();
    });
  });

  it("should sort by title", async () => {
    render(<Timeline {...defaultProps} />);
    const sortSelect = screen.getByTestId("sort-select");
    fireEvent.click(within(sortSelect).getByTestId("mock-select-trigger"));
    const selectContent = within(sortSelect).getByTestId("mock-select-content");
    fireEvent.click(within(selectContent).getByText("By Title"));
    await waitFor(() => {
      const titles = screen
        .getAllByRole("heading", { level: 3 })
        .map((h) => h.textContent);
      expect(titles).toEqual(["Algo 1", "Algo 2", "Algo 3", "Algo 4"]);
    });
  });

  it("should display no entries found message when filtered data is empty", async () => {
    render(<Timeline {...defaultProps} />);
    fireEvent.change(
      screen.getByPlaceholderText("Search algorithms, notes, or languages..."),
      { target: { value: "nonexistent" } },
    );
    await waitFor(() => {
      expect(screen.getByText("No entries found")).toBeInTheDocument();
    });
  });
});
