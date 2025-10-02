import fs from "fs/promises";
import matter from "gray-matter";
import path from "path";

export async function getMarkdownContent(
  relativePath: string,
): Promise<string | null> {
  try {
    const publicDir = path.join(process.cwd(), "public");
    const sanitizedRelativePath = relativePath.startsWith("/")
      ? relativePath.substring(1)
      : relativePath;
    const intendedPath = path.resolve(publicDir, sanitizedRelativePath);

    // Validate that the intended path is still within the public directory
    if (!intendedPath.startsWith(publicDir)) {
      console.warn(`Attempted path traversal: ${relativePath}`);
      return null;
    }

    const fileContent = await fs.readFile(intendedPath, "utf-8");
    const { content } = matter(fileContent);
    return content;
  } catch (error) {
    console.error(`Error reading markdown file ${relativePath}:`, error);
    if (error instanceof Error) {
      console.error(
        `Error name: ${error.name}, message: ${error.message}, stack: ${error.stack}`,
      );
    }
    return null;
  }
}
