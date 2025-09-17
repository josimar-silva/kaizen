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
});
