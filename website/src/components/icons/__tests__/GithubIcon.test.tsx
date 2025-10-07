import "@testing-library/jest-dom";

import { render, screen } from "@testing-library/react";

import { GithubIcon } from "../GithubIcon";

describe("GithubIcon", () => {
  it("should render the GitHub icon SVG", () => {
    render(<GithubIcon />);
    const svgElement = screen.getByTitle("GitHub").parentElement;
    expect(svgElement).toBeInTheDocument();
    expect(svgElement?.tagName).toBe("svg");
  });

  it("should apply the className prop", () => {
    const className = "test-class";
    render(<GithubIcon className={className} />);
    const svgElement = screen.getByTitle("GitHub").parentElement;
    expect(svgElement).toHaveClass(className);
  });
});
