import ReactMarkdown from "react-markdown";
import rehypeRaw from "rehype-raw";
import remarkFrontmatter from "remark-frontmatter";
import remarkGfm from "remark-gfm";
import remarkMath from "remark-math";

interface MarkdownRendererProps {
  markdown: string;
}

export default function MarkdownRenderer({
  markdown,
}: Readonly<MarkdownRendererProps>) {
  return (
    <ReactMarkdown
      remarkPlugins={[remarkGfm, remarkFrontmatter, remarkMath]}
      rehypePlugins={[rehypeRaw]}
    >
      {markdown}
    </ReactMarkdown>
  );
}
