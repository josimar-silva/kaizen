import { ArrowLeft, Calendar, ExternalLink } from "lucide-react";
import Link from "next/link";
import { notFound } from "next/navigation";

import AnalysisSection from "@/components/analysis-section";
import { SolutionNavigation } from "@/components/solution-navigation";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { kaizenData } from "@/lib/db";
import StyleService from "@/lib/style-service";
import { formatDate, formatRelativeDate } from "@/lib/utils";

export function generateStaticParams() {
  const params: { date: string; index: string }[] = [];

  Object.keys(kaizenData.commits).forEach((date) => {
    const algorithms = kaizenData.commits[date];
    algorithms.forEach((_, index) => {
      params.push({
        date,
        index: index.toString(),
      });
    });
  });

  return params;
}

interface PageProps {
  params: Promise<{
    date: string;
    index: string;
  }>;
}

export default async function SolutionPage({ params }: PageProps) {
  const { date, index } = await params;
  const algorithmIndex = Number.parseInt(index) || 0;

  const algorithms = kaizenData.commits[date] || [];
  const currentAlgorithm = algorithms[algorithmIndex];

  if (!currentAlgorithm) {
    notFound();
  }

  const hasMultiple = algorithms.length > 1;

  return (
    <div className="min-h-screen bg-background">
      <div className="container mx-auto px-4 py-8 max-w-4xl">
        {/* Header with back button */}
        <div className="mb-12">
          <Button variant="ghost" asChild className="mb-8 -ml-3">
            <Link
              href="/"
              className="flex items-center gap-2 text-muted-foreground hover:text-foreground"
            >
              <ArrowLeft className="w-4 h-4" />
              Back to Journal
            </Link>
          </Button>

          <div className="space-y-2 mb-6">
            <div className="flex items-center gap-2 text-sm text-muted-foreground">
              <Calendar className="w-4 h-4" />
              <span>{formatRelativeDate(date)}</span>
            </div>
            <h1 className="text-4xl font-bold text-foreground">
              {formatDate(date)}
            </h1>
          </div>

          {hasMultiple && (
            <div className="flex items-center justify-between pt-4 border-t border-border">
              <span className="text-sm text-muted-foreground">
                Solution {algorithmIndex + 1} of {algorithms.length}
              </span>
              <SolutionNavigation
                date={date}
                currentIndex={algorithmIndex}
                totalCount={algorithms.length}
              />
            </div>
          )}
        </div>

        {/* Algorithm content */}
        <div className="space-y-12">
          <div className="space-y-6">
            <div className="flex flex-col sm:flex-row sm:items-start sm:justify-between gap-4">
              <div className="space-y-3 flex-1">
                <h2 className="text-3xl font-bold text-foreground leading-tight">
                  {currentAlgorithm.title}
                </h2>
                <div className="flex items-center gap-3">
                  <div className="language-badge language-default">
                    <span className="mr-2">
                      <i
                        className={StyleService.getLanguageIcon(
                          currentAlgorithm.language,
                        )}
                      />
                    </span>
                    {currentAlgorithm.language}
                  </div>
                  <Badge variant="outline" className="text-xs">
                    {currentAlgorithm.type}
                  </Badge>
                </div>
              </div>

              <div className="flex flex-col sm:flex-row sm:items-center gap-2 pt-2">
                {currentAlgorithm.reference && (
                  <Button
                    asChild
                    size="sm"
                    variant="ghost"
                    className="shrink-0 w-full sm:w-auto"
                  >
                    <a
                      href={currentAlgorithm.reference}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="flex items-center justify-center gap-2"
                      data-testid="reference-button"
                    >
                      <ExternalLink className="w-4 h-4" />
                      Reference
                    </a>
                  </Button>
                )}
                <Button
                  asChild
                  size="sm"
                  variant="default"
                  className="shrink-0 w-full sm:w-auto hover:bg-accent/90"
                >
                  <a
                    href={currentAlgorithm.link}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="flex items-center justify-center gap-2"
                    data-testid="source-code-button"
                  >
                    <ExternalLink className="w-4 h-4" />
                    Source Code
                  </a>
                </Button>
              </div>
            </div>
          </div>

          <Separator />

          <div className="space-y-4">
            <h3 className="text-lg font-semibold text-foreground">
              Notes & Insights
            </h3>
            <p className="text-muted-foreground leading-relaxed whitespace-pre-wrap text-base">
              {currentAlgorithm.notes}
            </p>
          </div>

          {currentAlgorithm.analysis && (
            <AnalysisSection analysisPath={currentAlgorithm.analysis} />
          )}

          {hasMultiple && (
            <>
              <Separator />
              <div className="space-y-4">
                <h3 className="text-lg font-semibold text-foreground">
                  Other Solutions from This Day
                </h3>
                <div className="space-y-2">
                  {algorithms.map((algorithm, index) => (
                    <Link
                      key={index}
                      href={`/solution/${date}/${index}`}
                      className={`block p-4 rounded-lg border transition-colors ${
                        index === algorithmIndex
                          ? "border-accent bg-accent/5"
                          : "border-border hover:border-accent/50 hover:bg-muted/30"
                      }`}
                    >
                      <div className="flex items-center justify-between gap-4">
                        <div className="flex items-center gap-3 min-w-0 flex-1">
                          <span className="text-sm text-muted-foreground shrink-0">
                            #{index + 1}
                          </span>
                          <span className="font-medium truncate text-foreground">
                            {algorithm.title}
                          </span>
                          <div className="language-badge text-xs ml-auto">
                            <span className="mr-1">
                              <i
                                className={StyleService.getLanguageIcon(
                                  algorithm.language,
                                )}
                              />
                            </span>
                            {algorithm.language}
                          </div>
                        </div>
                        {index === algorithmIndex && (
                          <Badge
                            variant="secondary"
                            className="text-xs shrink-0"
                          >
                            Current
                          </Badge>
                        )}
                      </div>
                    </Link>
                  ))}
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </div>
  );
}
