import { render, screen } from "@testing-library/react";

import { StatPieChart } from "@/components/stat-pie-chart";

import { KaizenStats } from "../kaizen-stats";

jest.mock("@/components/stat-pie-chart", () => ({
  StatPieChart: jest.fn((props) => {
    // This is a mock component that just renders its props for inspection
    return <div data-testid="mock-stat-pie-chart" {...props} />;
  }),
}));

jest.mock("../kaizen-stats", () => ({
  ...jest.requireActual("../kaizen-stats"),
  getSolutionTypeColor: jest.fn((name) => {
    switch (name) {
      case "algorithm":
        return "#8522eeff";
      case "system-design":
        return "#FFBB28";
      default:
        return "#808080";
    }
  }),
  getSourceColor: jest.fn((name) => {
    switch (name) {
      case "leetcode":
        return "#FFA116";
      case "project-euler":
        return "#11c1b6ff";
      case "coursera":
        return "#0056D2";
      case "codewars":
        return "#B1361E";
      default:
        return "#808080";
    }
  }),
  getLanguageColor: jest.fn((name) => {
    switch (name) {
      case "Rust":
        return "#DEA584";
      case "Python":
        return "#3776AB";
      case "Java":
        return "#973013ff";
      case "Go":
        return "#00ADD8";
      case "C++":
        return "#00599C";
      case "TypeScript":
        return "#3178C6";
      case "Kotlin":
        return "#7F52FF";
      default:
        return "#808080";
    }
  }),
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
      daysSinceFirstActivity: 1,
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

  it("should render the four stat cards with correct values", () => {
    render(<KaizenStats stats={mockData.stats} />);

    expect(
      screen.getByText(`${mockData.stats.avgSolutionsPerWeek}`),
    ).toBeInTheDocument();
    expect(screen.getByText("Avg/Week")).toBeInTheDocument();

    expect(
      screen.getByText(`${mockData.stats.longestStreak} days`),
    ).toBeInTheDocument();
    expect(screen.getByText("Best Streak")).toBeInTheDocument();

    expect(
      screen.getByText(`${mockData.stats.daysSinceFirstActivity} days`),
    ).toBeInTheDocument();
    expect(screen.getByText("Since First Commit")).toBeInTheDocument();

    expect(screen.getByText(mockData.stats.mostActiveDay)).toBeInTheDocument();
    expect(screen.getByText("Most Active Day")).toBeInTheDocument();
  });

  it("should call StatPieChart with the correct props for Types", () => {
    render(<KaizenStats stats={mockData.stats} />);
    expect(StatPieChart).toHaveBeenCalledWith(
      expect.objectContaining({
        title: "Types",
        data: [{ name: "algorithm", displayName: "Algorithm", value: 19 }],
        colors: ["#8522eeff"],
      }),
      undefined,
    );
  });

  it("should call StatPieChart with the correct props for Sources", () => {
    render(<KaizenStats stats={mockData.stats} />);
    expect(StatPieChart).toHaveBeenCalledWith(
      expect.objectContaining({
        title: "Sources",
        data: [
          { name: "project-euler", displayName: "Project Euler", value: 5 },
          { name: "codewars", displayName: "Codewars", value: 2 },
          { name: "leetcode", displayName: "LeetCode", value: 11 },
          { name: "coursera", displayName: "Coursera", value: 1 },
        ],
        colors: ["#11c1b6ff", "#B1361E", "#FFA116", "#0056D2"],
      }),
      undefined,
    );
  });

  it("should call StatPieChart with the correct props for Languages", () => {
    render(<KaizenStats stats={mockData.stats} />);
    expect(StatPieChart).toHaveBeenCalledWith(
      expect.objectContaining({
        title: "Languages",
        data: [
          { name: "Java", displayName: "Java", value: 5 },
          { name: "Rust", displayName: "Rust", value: 2 },
          { name: "Kotlin", displayName: "Kotlin", value: 12 },
        ],
        colors: ["#973013ff", "#DEA584", "#7F52FF"],
      }),
      undefined,
    );
  });
});
