import fs from "fs/promises";
import path from "path";

import { getMarkdownContent } from "../markdown-service";

jest.mock("fs/promises", () => ({
  readFile: jest.fn(),
}));

describe("getMarkdownContent", () => {
  const mockPublicPath = path.join(process.cwd(), "public");

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it("should return markdown content for a valid file without frontmatter", async () => {
    const relativePath = "/solution/test-no-frontmatter.md";
    const absolutePath = path.join(mockPublicPath, relativePath);
    const mockContent = "# Hello World\nThis is a test.";

    (fs.readFile as jest.Mock).mockResolvedValue(mockContent);

    const content = await getMarkdownContent(relativePath);
    expect(content).toBe(mockContent);
    expect(fs.readFile).toHaveBeenCalledWith(absolutePath, "utf-8");
  });

  it("should return markdown content for a valid file with frontmatter", async () => {
    const relativePath = "/solution/test-with-frontmatter.md";
    const absolutePath = path.join(mockPublicPath, relativePath);
    const mockFileContent =
      "---\ntitle: Test\n---\n# Hello Frontmatter\nThis is a test with frontmatter.";
    const expectedContent =
      "# Hello Frontmatter\nThis is a test with frontmatter.";

    (fs.readFile as jest.Mock).mockResolvedValue(mockFileContent);

    const content = await getMarkdownContent(relativePath);
    expect(content).toBe(expectedContent);
    expect(fs.readFile).toHaveBeenCalledWith(absolutePath, "utf-8");
  });

  it("should return null and log an error for a non-existent file", async () => {
    const relativePath = "/solution/non-existent.md";
    const absolutePath = path.join(mockPublicPath, relativePath);

    (fs.readFile as jest.Mock).mockRejectedValue(new Error("File not found"));
    const consoleErrorSpy = jest
      .spyOn(console, "error")
      .mockImplementation(() => {});

    const content = await getMarkdownContent(relativePath);
    expect(content).toBeNull();
    expect(fs.readFile).toHaveBeenCalledWith(absolutePath, "utf-8");
    expect(consoleErrorSpy).toHaveBeenCalled();

    consoleErrorSpy.mockRestore();
  });
});
