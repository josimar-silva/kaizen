import DataService from "../data-service";

describe("DataService", () => {
  it("should export data from db.ts", () => {
    expect(DataService.data).toBeDefined();
    expect(typeof DataService.data).toBe("object");
    expect(Object.keys(DataService.data).length).toBeGreaterThan(0);
  });
});
