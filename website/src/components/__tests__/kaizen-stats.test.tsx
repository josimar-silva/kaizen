import { render, screen } from "@testing-library/react";

import { StatPieChart } from "@/components/stat-pie-chart";

import { KaizenStats } from "../kaizen-stats";

jest.mock("@/components/stat-pie-chart", () => ({
  StatPieChart: jest.fn(() => null),
}));

afterEach(() => {
  jest.clearAllMocks();
});

describe("KaizenStats", () => {
  const mockData = {
    stats: {
      totalAlgorithms: 19,
      totalDays: 18,
      currentStreak: 0,
      longestStreak: 15,
      languageDistribution: {
        Java: 5,
        Rust: 2,
        Kotlin: 12,
      },
      monthlyActivity: {
        "2025-10": 5,
        "2025-09": 14,
      },
      sourceDistribution: {
        "project-euler": 5,
        codewars: 2,
        leetcode: 11,
        coursera: 1,
      },
      mostActiveDay: "Friday",
      typeDistribution: {
        algorithm: 19,
      },
      avgSolutionsPerWeek: 1.1,
      daysSinceLastActivity: 1,
    },
    commits: {
      "2025-10-05": [
        {
          title: "Longest Palindromic String v1",
          notes: "How can this be optimized to O(n)? 🤯",
          link: "https://github.com/josimar-silva/kaizen/commit/8566cb244f329c75f7f00adece35d6ac982e96a1",
          language: "Kotlin",
          type: "algorithm",
          reference:
            "https://leetcode.com/problems/longest-palindromic-substring",
          analysis:
            "algorithms/analysis/0019-longest-palindromic-substring-8566cb2.md",
          source: "leetcode",
          date: "2025-10-05",
        },
      ],
    },
  };

  it("should render the progress overview title", () => {
    render(<KaizenStats stats={mockData.stats} />);
    expect(screen.getByText("Progress Overview")).toBeInTheDocument();
  });

  it("should call StatPieChart with the correct props", () => {
    render(<KaizenStats stats={mockData.stats} />);
    expect(StatPieChart).toHaveBeenCalledTimes(3);
  });
});
