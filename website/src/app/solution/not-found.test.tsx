import "@testing-library/jest-dom";

import { render, screen } from "@testing-library/react";
import React from "react";

import NotFound from "./not-found";

describe("NotFound Page", () => {
  it("should render the not found message", () => {
    render(<NotFound />);
    expect(
      screen.getByRole("heading", { name: "Solution Not Found" }),
    ).toBeInTheDocument();
    expect(
      screen.getByText("The requested solution could not be found."),
    ).toBeInTheDocument();
    expect(screen.getByRole("link", { name: "Return Home" })).toHaveAttribute(
      "href",
      "/",
    );
  });
});
