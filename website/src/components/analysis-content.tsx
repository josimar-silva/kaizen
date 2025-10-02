import MarkdownRenderer from "@/components/markdown-renderer";
import { getMarkdownContent } from "@/lib/markdown-service";

interface AnalysisContentProps {
  analysisPath: string;
}

export default async function AnalysisContent({
  analysisPath,
}: Readonly<AnalysisContentProps>) {
  const analysisContent = await getMarkdownContent(analysisPath);

  if (!analysisContent) {
    return null;
  }

  return <MarkdownRenderer markdown={analysisContent} />;
}
