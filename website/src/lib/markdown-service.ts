import fs from "fs/promises";
import matter from "gray-matter";
import path from "path";

export async function getMarkdownContent(
  relativePath: string,
): Promise<string | null> {
  try {
    const absolutePath = path.join(process.cwd(), "public", relativePath);
    const fileContent = await fs.readFile(absolutePath, "utf-8");
    const { content } = matter(fileContent);
    return content;
  } catch (error) {
    console.error(`Error reading markdown file ${relativePath}:`, error);
    return null;
  }
}
