import { SourceName } from "@/interfaces/algorithm";

import StyleService from "../style-service";

describe("StyleService", () => {
  it("should return correct Devicon class for Rust", () => {
    expect(StyleService.getLanguageIcon("Rust")).toBe(
      "devicon-rust-plain colored",
    );
  });

  it("should return correct Devicon class for Python", () => {
    expect(StyleService.getLanguageIcon("Python")).toBe(
      "devicon-python-plain colored",
    );
  });

  it("should return correct Devicon class for Java", () => {
    expect(StyleService.getLanguageIcon("Java")).toBe(
      "devicon-java-plain colored",
    );
  });

  it("should return correct Devicon class for Go", () => {
    expect(StyleService.getLanguageIcon("Go")).toBe("devicon-go-plain colored");
  });

  it("should return correct Devicon class for C++", () => {
    expect(StyleService.getLanguageIcon("C++")).toBe(
      "devicon-cplusplus-plain colored",
    );
  });

  it("should return correct Devicon class for TypeScript", () => {
    expect(StyleService.getLanguageIcon("TypeScript")).toBe(
      "devicon-typescript-plain colored",
    );
  });

  it("should return correct Devicon class for Kotlin", () => {
    expect(StyleService.getLanguageIcon("Kotlin")).toBe(
      "devicon-kotlin-plain colored",
    );
  });

  it("should return generic Devicon class for unknown language", () => {
    expect(StyleService.getLanguageIcon("Unknown")).toBe(
      "devicon-devicon-plain",
    );
  });

  it("should return correct color for Rust language", () => {
    expect(StyleService.getLanguageColor("Rust")).toBe("#DEA584");
  });

  it("should return correct color for Python language", () => {
    expect(StyleService.getLanguageColor("Python")).toBe("#3776AB");
  });

  it("should return correct color for Java language", () => {
    expect(StyleService.getLanguageColor("Java")).toBe("#973013ff");
  });

  it("should return correct color for Go language", () => {
    expect(StyleService.getLanguageColor("Go")).toBe("#00ADD8");
  });

  it("should return correct color for C++ language", () => {
    expect(StyleService.getLanguageColor("C++")).toBe("#00599C");
  });

  it("should return correct color for TypeScript language", () => {
    expect(StyleService.getLanguageColor("TypeScript")).toBe("#3178C6");
  });

  it("should return correct color for Kotlin language", () => {
    expect(StyleService.getLanguageColor("Kotlin")).toBe("#7F52FF");
  });

  it("should return gray color for unknown language", () => {
    expect(StyleService.getLanguageColor("Unknown")).toBe("#808080");
  });

  it("should return correct color for algorithm type", () => {
    expect(StyleService.getSolutionTypeColor("algorithm")).toBe("#8522eeff");
  });

  it("should return correct color for system-design type", () => {
    expect(StyleService.getSolutionTypeColor("system-design")).toBe("#FFBB28");
  });

  it("should return gray color for unknown solution type", () => {
    expect(StyleService.getSolutionTypeColor("Unknown")).toBe("#808080");
  });

  it("should return correct color for leetcode source", () => {
    expect(StyleService.getSourceColor(SourceName.LeetCode)).toBe("#FFA116");
  });

  it("should return correct color for project-euler source", () => {
    expect(StyleService.getSourceColor(SourceName.ProjectEuler)).toBe(
      "#11c1b6ff",
    );
  });

  it("should return correct color for coursera source", () => {
    expect(StyleService.getSourceColor(SourceName.Coursera)).toBe("#0056D2");
  });

  it("should return correct color for codewars source", () => {
    expect(StyleService.getSourceColor(SourceName.Codewars)).toBe("#B1361E");
  });

  it("should return gray color for unknown source", () => {
    expect(StyleService.getSourceColor("Unknown")).toBe("#808080");
  });

  it("should return correct human-readable name for LeetCode source", () => {
    expect(StyleService.getHumanReadableSourceName("leetcode")).toBe(
      "LeetCode",
    );
  });

  it("should return correct human-readable name for Project Euler source", () => {
    expect(StyleService.getHumanReadableSourceName("project-euler")).toBe(
      "Project Euler",
    );
  });

  it("should return correct human-readable name for Coursera source", () => {
    expect(StyleService.getHumanReadableSourceName("coursera")).toBe(
      "Coursera",
    );
  });

  it("should return correct human-readable name for Codewars source", () => {
    expect(StyleService.getHumanReadableSourceName("codewars")).toBe(
      "Codewars",
    );
  });

  it("should return the source name itself for unknown source", () => {
    expect(StyleService.getHumanReadableSourceName("unknown-source")).toBe(
      "unknown-source",
    );
  });
});
