import "@testing-library/jest-dom";

import { render, screen } from "@testing-library/react";

import { Footer } from "../footer";

describe("Footer", () => {
  it("should render the footer with the correct version", () => {
    process.env.APP_VERSION = "1.0.0";
    render(<Footer />);
    const copyright = screen.getByTestId("footer-copyright");
    expect(copyright).toHaveTextContent(
      `© ${new Date().getFullYear()} Josimar Silva. All rights reserved. v1.0.0`,
    );
  });

  it("should render the author link", () => {
    render(<Footer />);
    const authorLink = screen.getByTestId("footer-author-link");
    expect(authorLink).toBeInTheDocument();
    expect(authorLink).toHaveAttribute("href", "https://josimar-silva.com");
  });

  it("should render the copyright without version when version is not present", () => {
    process.env.APP_VERSION = ""; // Simulate no version
    render(<Footer />);
    const copyright = screen.getByTestId("footer-copyright");
    expect(copyright).toHaveTextContent(
      `© ${new Date().getFullYear()} Josimar Silva. All rights reserved.`,
    );
    expect(copyright).not.toHaveTextContent(/v\d+\.\d+\.\d+/); // Check for any version pattern
  });
});
