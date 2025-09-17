import { cn, formatDate, formatRelativeDate } from "../utils";

describe("utils", () => {
  describe("cn", () => {
    it("should concatenate class names correctly", () => {
      expect(cn("class1", "class2")).toBe("class1 class2");
      expect(cn("class1", false, "class3")).toBe("class1 class3");
      expect(cn("class1", null, undefined, "class2")).toBe("class1 class2");
    });

    it("should handle empty inputs", () => {
      expect(cn()).toBe("");
      expect(cn(null, undefined, false)).toBe("");
    });
  });

  describe("formatDate", () => {
    it("should format date correctly", () => {
      const dateString = "2023-10-26";
      // Mock Date to ensure consistent output regardless of locale/timezone
      const mockDate = new Date("2023-10-26T12:00:00.000Z");
      const spy = jest
        .spyOn(global, "Date")
        .mockImplementation(() => mockDate as any);

      expect(formatDate(dateString)).toBe("Thursday, October 26, 2023");

      spy.mockRestore();
    });
  });

  describe("formatRelativeDate", () => {
    beforeEach(() => {
      jest.useFakeTimers();
    });

    afterEach(() => {
      jest.useRealTimers();
    });

    it('should return "Today" for current date', () => {
      jest.setSystemTime(new Date("2023-10-26T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("Today");
    });

    it('should return "Yesterday" for previous day', () => {
      jest.setSystemTime(new Date("2023-10-27T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("Yesterday");
    });

    it('should return "X days ago" for less than 7 days ago', () => {
      jest.setSystemTime(new Date("2023-10-29T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("3 days ago");
    });

    it('should return "X weeks ago" for less than 30 days ago', () => {
      jest.setSystemTime(new Date("2023-11-10T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("2 weeks ago");
    });

    it('should return "X months ago" for less than 365 days ago', () => {
      jest.setSystemTime(new Date("2023-12-25T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("2 months ago");
    });

    it('should return "X years ago" for more than 365 days ago', () => {
      jest.setSystemTime(new Date("2024-11-30T12:00:00.000Z"));
      expect(formatRelativeDate("2023-10-26")).toBe("1 years ago");
    });
  });
});
