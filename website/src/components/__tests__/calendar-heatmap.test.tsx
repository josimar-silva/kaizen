import "@testing-library/jest-dom";

import { render, screen } from "@testing-library/react";
import React from "react";

import { KaizenData } from "@/interfaces/kaizen-data";

import { CalendarHeatmap } from "../calendar-heatmap";

const mockKaizenData: KaizenData = {
  stats: {
    totalAlgorithms: 100,
    totalDays: 50,
    currentStreak: 5,
    longestStreak: 10,
    languageDistribution: { Java: 1, Python: 1 },
    monthlyActivity: { "2023-10": 2 },
  },
  commits: {
    "2023-10-26": [
      {
        title: "Algo 1",
        language: "Java",
        notes: "notes",
        type: "algorithm",
        link: "",
      },
    ],
    "2023-10-25": [
      {
        title: "Algo 2",
        language: "Python",
        notes: "notes",
        type: "algorithm",
        link: "",
      },
    ],
  },
};

describe("CalendarHeatmap", () => {
  const defaultProps = {
    data: mockKaizenData,
  };

  beforeEach(() => {
    jest.useFakeTimers();
    jest.setSystemTime(new Date("2023-10-26T12:00:00.000Z")); // Mock current date
  });

  afterEach(() => {
    jest.useRealTimers();
  });

  it("should render heatmap with stats", () => {
    render(<CalendarHeatmap {...defaultProps} />);

    expect(screen.getByText("100")).toBeInTheDocument(); // totalAlgorithms
    expect(screen.getByText("50")).toBeInTheDocument(); // totalDays
    expect(screen.getByText("5")).toBeInTheDocument(); // currentStreak
    expect(screen.getByText("10")).toBeInTheDocument(); // longestStreak
  });

  it("should handle empty data gracefully", () => {
    const emptyData: KaizenData = {
      stats: {
        totalAlgorithms: 0,
        totalDays: 0,
        currentStreak: 0,
        longestStreak: 0,
        languageDistribution: {},
        monthlyActivity: {},
      },
      commits: {},
    };
    render(<CalendarHeatmap data={emptyData} />);
    // Check for the presence of the stats, which should be 0
    expect(screen.getAllByText(/^0$/).length).toBeGreaterThan(0);
  });
});
