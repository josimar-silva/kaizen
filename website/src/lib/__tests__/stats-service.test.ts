import { calculateStats, getKaizenData } from "../stats-service";

// Mock DataService before it's imported by stats-service
jest.mock("../data-service", () => ({
  __esModule: true,
  default: {
    data: {
      "2025-09-15": [
        {
          title: "calculate multiples of 3 or 5",
          notes:
            "First problem of Project Euler list. Ref.: https://projecteuler.net/problem=1",
          link: "https://github.com/josimar-silva/kaizen/commit/7ded72ad7cd016340b7042671bbae9ff1442fe2c",
          language: "Kotlin",
          type: "algorithm",
        },
      ],
      "2025-09-14": [
        {
          title: "intro to Knuth's method",
          notes:
            "First assignment of Coursera's Algorithms Part 1 course. Ref.: https://www.coursera.org/learn/algorithms-part1",
          link: "https://github.com/josimar-silva/kaizen/commit/4e48e34a4372e6015c2af1655438b72534f32fc6",
          language: "Java",
          type: "algorithm",
        },
      ],
    },
  },
}));

describe("stats-service", () => {
  let mockKaizenData: any;

  beforeEach(() => {
    mockKaizenData = {
      "2025-01-01": [{ language: "Java", type: "algorithm" }],
      "2025-01-02": [{ language: "Python", type: "algorithm" }],
      "2025-01-03": [
        { language: "Java", type: "algorithm" },
        { language: "Python", type: "algorithm" },
      ],
      "2025-01-05": [{ language: "Rust", type: "algorithm" }],
      "2025-01-06": [{ language: "Rust", type: "algorithm" }],
      "2025-01-07": [{ language: "Rust", type: "algorithm" }],
    };
  });

  it("should calculate total days and total algorithms", () => {
    const stats = calculateStats(mockKaizenData);
    expect(stats.totalDays).toBe(6);
    expect(stats.totalAlgorithms).toBe(7);
  });

  it("should calculate language distribution", () => {
    const stats = calculateStats(mockKaizenData);
    expect(stats.languageDistribution).toEqual({
      Java: 2,
      Python: 2,
      Rust: 3,
    });
  });

  it("should calculate monthly activity", () => {
    const stats = calculateStats(mockKaizenData);
    expect(stats.monthlyActivity).toEqual({
      "2025-01": 7,
    });
  });

  it("should calculate current streak", () => {
    // Mock Date to control current streak calculation
    const mockDate = new Date("2025-01-07T12:00:00.000Z");
    const spy = jest
      .spyOn(global, "Date")
      .mockImplementation(() => mockDate as any);

    const stats = calculateStats(mockKaizenData);
    expect(stats.currentStreak).toBe(2);

    spy.mockRestore();
  });

  it("should calculate longest streak", () => {
    const stats = calculateStats(mockKaizenData);
    expect(stats.longestStreak).toBe(3);
  });

  it("should handle empty data for stats calculation", () => {
    const stats = calculateStats({});
    expect(stats.totalDays).toBe(0);
    expect(stats.totalAlgorithms).toBe(0);
    expect(stats.currentStreak).toBe(0);
    expect(stats.longestStreak).toBe(0);
    expect(stats.languageDistribution).toEqual({});
    expect(stats.monthlyActivity).toEqual({});
  });

  it("should return KaizenData from getKaizenData", async () => {
    // DataService.data is now populated by the pre-build script, so we don't mock it.
    // We assert that getKaizenData returns the actual data from db.ts.
    const expectedData = {
      "2025-09-15": [
        {
          title: "calculate multiples of 3 or 5",
          notes:
            "First problem of Project Euler list. Ref.: https://projecteuler.net/problem=1",
          link: "https://github.com/josimar-silva/kaizen/commit/7ded72ad7cd016340b7042671bbae9ff1442fe2c",
          language: "Kotlin",
          type: "algorithm",
        },
      ],
      "2025-09-14": [
        {
          title: "intro to Knuth's method",
          notes:
            "First assignment of Coursera's Algorithms Part 1 course. Ref.: https://www.coursera.org/learn/algorithms-part1",
          link: "https://github.com/josimar-silva/kaizen/commit/4e48e34a4372e6015c2af1655438b72534f32fc6",
          language: "Java",
          type: "algorithm",
        },
      ],
    };

    const data = await getKaizenData();
    expect(data).toEqual(expectedData);
  });
});
