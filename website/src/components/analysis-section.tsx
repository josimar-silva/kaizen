import { Suspense } from "react";

import AnalysisContent from "@/components/analysis-content";
import { Separator } from "@/components/ui/separator";
import { Skeleton } from "@/components/ui/skeleton";

interface AnalysisSectionProps {
  analysisPath: string;
}

export default function AnalysisSection({
  analysisPath,
}: Readonly<AnalysisSectionProps>) {
  return (
    <>
      <Separator />
      <div className="space-y-4">
        <h3 className="text-lg font-semibold text-foreground">Analysis</h3>
        <Suspense fallback={<Skeleton className="h-32 w-full rounded-lg" />}>
          <div className="prose prose-gray max-w-none dark:prose-invert lg:prose-lg markdown-content">
            <AnalysisContent analysisPath={analysisPath} />
          </div>
        </Suspense>
      </div>
    </>
  );
}
