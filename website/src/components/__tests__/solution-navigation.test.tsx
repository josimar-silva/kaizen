import "@testing-library/jest-dom";

import { fireEvent, render, screen } from "@testing-library/react";
import { useRouter } from "next/navigation";
import React from "react";

import { SolutionNavigation } from "../solution-navigation";

// Mock useRouter
jest.mock("next/navigation", () => ({
  useRouter: jest.fn(),
}));

describe("SolutionNavigation", () => {
  const push = jest.fn();
  (useRouter as jest.Mock).mockReturnValue({ push });

  const defaultProps = {
    date: "2023-01-01",
    currentIndex: 0,
    totalCount: 3,
  };

  beforeEach(() => {
    push.mockClear();
  });

  it("should render correctly", () => {
    render(<SolutionNavigation {...defaultProps} />);
    expect(screen.getByText("1 of 3")).toBeInTheDocument();
    expect(
      screen.getByRole("button", { name: /previous/i }),
    ).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /next/i })).toBeInTheDocument();
  });

  it("should navigate to the next solution on next button click", () => {
    render(<SolutionNavigation {...defaultProps} />);
    fireEvent.click(screen.getByRole("button", { name: /next/i }));
    expect(push).toHaveBeenCalledWith("/solution/2023-01-01/1");
  });

  it("should navigate to the previous solution on previous button click", () => {
    render(<SolutionNavigation {...defaultProps} currentIndex={1} />);
    fireEvent.click(screen.getByRole("button", { name: /previous/i }));
    expect(push).toHaveBeenCalledWith("/solution/2023-01-01/0");
  });

  it("should loop to the first solution from the last one", () => {
    render(<SolutionNavigation {...defaultProps} currentIndex={2} />);
    fireEvent.click(screen.getByRole("button", { name: /next/i }));
    expect(push).toHaveBeenCalledWith("/solution/2023-01-01/0");
  });

  it("should loop to the last solution from the first one", () => {
    render(<SolutionNavigation {...defaultProps} />);
    fireEvent.click(screen.getByRole("button", { name: /previous/i }));
    expect(push).toHaveBeenCalledWith("/solution/2023-01-01/2");
  });
});
