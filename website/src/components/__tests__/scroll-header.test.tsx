import "@testing-library/jest-dom";

import { fireEvent, render, screen } from "@testing-library/react";
import React from "react";

import { ScrollHeader } from "../scroll-header";

describe("ScrollHeader", () => {
  const defaultProps = {
    children: <div>Test Content</div>,
  };

  it("should render children", () => {
    render(<ScrollHeader {...defaultProps} />);
    expect(screen.getByText("Test Content")).toBeInTheDocument();
  });

  it("should be visible by default", () => {
    render(<ScrollHeader {...defaultProps} />);
    expect(screen.getByRole("banner")).toHaveClass("translate-y-0");
  });

  it("should hide on scroll down past threshold", () => {
    render(<ScrollHeader {...defaultProps} />);

    // Initial scroll position
    Object.defineProperty(window, "scrollY", { value: 0, writable: true });
    fireEvent.scroll(window);

    // Scroll down past threshold
    Object.defineProperty(window, "scrollY", { value: 200, writable: true });
    fireEvent.scroll(window);

    expect(screen.getByRole("banner")).toHaveClass("-translate-y-full");
  });

  it("should show on scroll up", () => {
    render(<ScrollHeader {...defaultProps} />);

    // Scroll down first to hide
    Object.defineProperty(window, "scrollY", { value: 200, writable: true });
    fireEvent.scroll(window);
    expect(screen.getByRole("banner")).toHaveClass("-translate-y-full");

    // Scroll up
    Object.defineProperty(window, "scrollY", { value: 100, writable: true });
    fireEvent.scroll(window);

    expect(screen.getByRole("banner")).toHaveClass("translate-y-0");
  });

  it("should show when at top", () => {
    render(<ScrollHeader {...defaultProps} />);

    // Scroll down first to hide
    Object.defineProperty(window, "scrollY", { value: 200, writable: true });
    fireEvent.scroll(window);
    expect(screen.getByRole("banner")).toHaveClass("-translate-y-full");

    // Scroll to top
    Object.defineProperty(window, "scrollY", { value: 0, writable: true });
    fireEvent.scroll(window);

    expect(screen.getByRole("banner")).toHaveClass("translate-y-0");
  });
});
