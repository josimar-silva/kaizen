"use client";

import { AnimatePresence, motion } from "framer-motion";
import {
  Calendar,
  ChevronLeft,
  ChevronRight,
  Clock,
  Code2,
  ExternalLink,
} from "lucide-react";
import { useState } from "react";

import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Separator } from "@/components/ui/separator";
import { Algorithm } from "@/interfaces/algorithm";
import StyleService from "@/lib/style-service";
import { formatDate, formatRelativeDate } from "@/lib/utils";

interface AlgorithmDetailModalProps {
  isOpen: boolean;
  onClose: () => void;
  date: string;
  algorithms: Algorithm[];
  initialAlgorithmIndex?: number;
}

export function AlgorithmDetailModal({
  isOpen,
  onClose,
  date,
  algorithms,
  initialAlgorithmIndex = 0,
}: Readonly<AlgorithmDetailModalProps>) {
  const [currentIndex, setCurrentIndex] = useState(initialAlgorithmIndex);

  const currentAlgorithm = algorithms[currentIndex];
  const hasMultiple = algorithms.length > 1;

  const goToPrevious = () => {
    setCurrentIndex((prev) => (prev > 0 ? prev - 1 : algorithms.length - 1));
  };

  const goToNext = () => {
    setCurrentIndex((prev) => (prev < algorithms.length - 1 ? prev + 1 : 0));
  };

  const handleClose = () => {
    setCurrentIndex(initialAlgorithmIndex);
    onClose();
  };

  if (!currentAlgorithm) return null;

  return (
    <Dialog open={isOpen} onOpenChange={handleClose}>
      <DialogContent className="w-[95vw] max-w-2xl max-h-[95vh] sm:max-h-[90vh] overflow-hidden flex flex-col p-4 sm:p-6">
        <DialogHeader className="space-y-3">
          <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
            <div className="flex items-start sm:items-center gap-3">
              <Calendar className="w-5 h-5 text-accent shrink-0 mt-0.5 sm:mt-0" />
              <div className="min-w-0 flex-1">
                <DialogTitle className="text-left text-base sm:text-lg leading-tight">
                  {formatDate(date)}
                </DialogTitle>
                <div className="flex items-center gap-2 mt-1">
                  <Clock className="w-3 h-3 text-muted-foreground shrink-0" />
                  <span className="text-xs text-muted-foreground">
                    {formatRelativeDate(date)}
                  </span>
                </div>
              </div>
            </div>

            {hasMultiple && (
              <div className="flex items-center gap-2 self-start sm:self-auto">
                <Button
                  variant="outline"
                  size="sm"
                  onClick={goToPrevious}
                  className="h-8 w-8 p-0 bg-transparent"
                  aria-label="previous algorithm"
                >
                  <ChevronLeft className="w-4 h-4" />
                </Button>
                <span className="text-sm text-muted-foreground px-2 whitespace-nowrap">
                  {currentIndex + 1} of {algorithms.length}
                </span>
                <Button
                  variant="outline"
                  size="sm"
                  onClick={goToNext}
                  className="h-8 w-8 p-0 bg-transparent"
                  aria-label="next algorithm"
                >
                  <ChevronRight className="w-4 h-4" />
                </Button>
              </div>
            )}
          </div>

          {hasMultiple && (
            <DialogDescription className="text-left text-sm">
              Viewing algorithm {currentIndex + 1} of {algorithms.length} from
              this day
            </DialogDescription>
          )}
        </DialogHeader>

        <div className="flex-1 overflow-y-auto space-y-4 sm:space-y-6 pr-1">
          <AnimatePresence mode="wait">
            <motion.div
              key={currentIndex}
              initial={{ opacity: 0, x: 20 }}
              animate={{ opacity: 1, x: 0 }}
              exit={{ opacity: 0, x: -20 }}
              transition={{ duration: 0.2 }}
              className="space-y-4 sm:space-y-6"
            >
              <Card className="bg-muted/20 border-border">
                <CardContent className="p-4 sm:p-6">
                  <div className="flex flex-col sm:flex-row sm:items-start sm:justify-between gap-4">
                    <div className="space-y-3 flex-1 min-w-0">
                      <div className="flex flex-col sm:flex-row sm:items-center gap-3">
                        <h2 className="text-xl sm:text-2xl font-bold text-foreground leading-tight">
                          {currentAlgorithm.title}
                        </h2>
                        <div className="flex flex-col sm:flex-row sm:items-center gap-2 pt-2">
                          {currentAlgorithm.reference && (
                            <Button
                              asChild
                              variant="ghost"
                              className="shrink-0 w-full sm:w-auto"
                            >
                              <a
                                href={currentAlgorithm.reference}
                                target="_blank"
                                rel="noopener noreferrer"
                                className="flex items-center justify-center gap-2"
                                data-testid="problem-button"
                              >
                                <ExternalLink className="w-4 h-4" />
                                Problem
                              </a>
                            </Button>
                          )}
                          <Button
                            asChild
                            variant="default"
                            className="shrink-0 w-full sm:w-auto hover:bg-accent/90"
                          >
                            <a
                              href={currentAlgorithm.link}
                              target="_blank"
                              rel="noopener noreferrer"
                              className="flex items-center justify-center gap-2"
                              data-testid="solution-button"
                            >
                              <ExternalLink className="w-4 h-4" />
                              Solution
                            </a>
                          </Button>
                        </div>
                      </div>

                      <Separator />

                      <div className="flex flex-col sm:flex-row sm:items-center gap-2 sm:gap-4 text-sm text-muted-foreground">
                        <div className="flex items-center gap-2">
                          <Code2 className="w-4 h-4 shrink-0" />
                          <span>Algorithm</span>
                        </div>
                        <div
                          className={`language-badge w-fit`}
                          data-testid="main-language-badge"
                        >
                          <i
                            className={StyleService.getLanguageIcon(
                              currentAlgorithm.language,
                            )}
                          />
                          {currentAlgorithm.language}
                        </div>
                        <div className="flex items-center gap-2">
                          <div className="text-sm text-foreground">
                            {formatDate(date)}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </CardContent>
              </Card>

              <Card className="border-border">
                <CardContent className="p-4 sm:p-6">
                  <div className="space-y-4">
                    <div className="flex flex-col sm:flex-row sm:items-center gap-2">
                      <h3 className="text-base sm:text-lg font-semibold text-foreground">
                        Notes & Insights
                      </h3>
                    </div>

                    <Separator />

                    <div className="prose prose-sm max-w-none">
                      <p className="text-muted-foreground leading-relaxed whitespace-pre-wrap text-sm sm:text-base break-words">
                        {currentAlgorithm.notes}
                      </p>
                    </div>
                  </div>
                </CardContent>
              </Card>

              {hasMultiple && (
                <Card className="border-border">
                  <CardContent className="p-4 sm:p-6">
                    <div className="space-y-4">
                      <h3 className="text-base sm:text-lg font-semibold text-foreground">
                        Other Algorithms from {formatDate(date)}
                      </h3>

                      <div className="grid gap-3">
                        {algorithms.map((algorithm, index) => (
                          <motion.button
                            key={index}
                            onClick={() => setCurrentIndex(index)}
                            className={`text-left p-3 rounded-lg border transition-all ${
                              index === currentIndex
                                ? "border-accent bg-accent/10"
                                : "border-border hover:border-accent/50 hover:bg-muted/20"
                            }`}
                            whileHover={{ scale: 1.02 }}
                            whileTap={{ scale: 0.98 }}
                          >
                            <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-2 sm:gap-3">
                              <div className="flex items-center gap-3 min-w-0 flex-1">
                                <div
                                  className={`language-badge text-xs shrink-0`}
                                >
                                  <i
                                    className={StyleService.getLanguageIcon(
                                      algorithm.language,
                                    )}
                                  />
                                  {algorithm.language}
                                </div>
                                <span className="font-medium text-sm truncate">
                                  {algorithm.title}
                                </span>
                              </div>
                              {index === currentIndex && (
                                <Badge
                                  variant="secondary"
                                  className="text-xs shrink-0 self-start sm:self-auto"
                                >
                                  Current
                                </Badge>
                              )}
                            </div>
                          </motion.button>
                        ))}
                      </div>
                    </div>
                  </CardContent>
                </Card>
              )}
            </motion.div>
          </AnimatePresence>
        </div>
      </DialogContent>
    </Dialog>
  );
}
