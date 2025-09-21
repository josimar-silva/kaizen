"use client";

import { AnimatePresence, motion } from "framer-motion";
import {
  Calendar,
  Clock,
  Code,
  ExternalLink,
  Filter,
  Layers,
  Search,
} from "lucide-react";
import { useMemo, useState } from "react";

import { AlgorithmDetailModal } from "@/components/algorithm-detail-modal";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Separator } from "@/components/ui/separator";
import { Commits } from "@/interfaces/kaizen-data";
import StyleService from "@/lib/style-service";
import { formatDate, formatRelativeDate } from "@/lib/utils";

interface TimelineProps {
  commits: Commits;
}

export function Timeline({ commits }: Readonly<TimelineProps>) {
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedLanguage, setSelectedLanguage] = useState<string>("all");
  const [selectedType, setSelectedType] = useState<string>("all");
  const [sortBy, setSortBy] = useState<"date" | "language" | "title">("date");
  const [itemsToShow, setItemsToShow] = useState(10);
  const [selectedDate, setSelectedDate] = useState<string | null>(null);
  const [selectedAlgorithmIndex, setSelectedAlgorithmIndex] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleAlgorithmClick = (date: string, algorithmIndex: number) => {
    setSelectedDate(date);
    setSelectedAlgorithmIndex(algorithmIndex);
    setIsModalOpen(true);
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
    setSelectedDate(null);
    setSelectedAlgorithmIndex(0);
  };

  const filteredAndSortedData = useMemo(() => {
    let entries = Object.entries(commits).flatMap(([date, algorithms]) =>
      algorithms.map((algorithm, index) => ({
        date,
        algorithm,
        index,
      })),
    );

    // Filter by search query
    if (searchQuery) {
      entries = entries.filter(
        ({ algorithm }) =>
          algorithm.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
          algorithm.notes.toLowerCase().includes(searchQuery.toLowerCase()) ||
          algorithm.language.toLowerCase().includes(searchQuery.toLowerCase()),
      );
    }

    // Filter by language
    if (selectedLanguage !== "all") {
      entries = entries.filter(
        ({ algorithm }) => algorithm.language === selectedLanguage,
      );
    }

    // Filter by type
    if (selectedType !== "all") {
      entries = entries.filter(
        ({ algorithm }) => algorithm.type === selectedType,
      );
    }

    // Sort entries
    entries.sort((a, b) => {
      switch (sortBy) {
        case "date":
          return new Date(b.date).getTime() - new Date(a.date).getTime();
        case "language":
          return a.algorithm.language.localeCompare(b.algorithm.language);
        case "title":
          return a.algorithm.title.localeCompare(b.algorithm.title);
        default:
          return 0;
      }
    });

    return entries;
  }, [commits, searchQuery, selectedLanguage, selectedType, sortBy]);

  const availableLanguages = useMemo(() => {
    const languages = new Set<string>();
    Object.values(commits).forEach((algorithms) => {
      algorithms.forEach((algorithm) => languages.add(algorithm.language));
    });
    return Array.from(languages).sort();
  }, [commits]);

  const visibleEntries = filteredAndSortedData.slice(0, itemsToShow);
  const hasMore = filteredAndSortedData.length > itemsToShow;

  return (
    <div className="space-y-4 sm:space-y-6 max-w-5xl mx-auto">
      <Card className="p-3 sm:p-4 bg-card/50 border-border">
        <div className="flex flex-col gap-3 sm:gap-4">
          <div className="relative flex-1">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-muted-foreground" />
            <Input
              placeholder="Search algorithms, notes, or languages..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-10 bg-background text-sm"
            />
          </div>

          <div className="flex flex-col sm:flex-row gap-2">
            <Select
              value={selectedType}
              onValueChange={setSelectedType}
              data-testid="type-select"
            >
              <SelectTrigger
                aria-label="Filter by type"
                className="w-full sm:w-44 bg-background"
              >
                <Layers className="w-4 h-4 mr-2" />
                <SelectValue placeholder="Type" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All Types</SelectItem>
                <SelectItem value="algorithm">
                  <div className="flex items-center gap-2">Algorithm</div>
                </SelectItem>
                <SelectItem value="system-design">
                  <div className="flex items-center gap-2">System Design</div>
                </SelectItem>
              </SelectContent>
            </Select>

            <Select
              value={selectedLanguage}
              onValueChange={setSelectedLanguage}
              data-testid="language-select"
            >
              <SelectTrigger
                aria-label="Filter by language"
                className="w-full sm:w-44 bg-background"
              >
                <Filter className="w-4 h-4 mr-2" />
                <SelectValue placeholder="Language" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All Languages</SelectItem>
                {availableLanguages.map((language) => (
                  <SelectItem key={language} value={language}>
                    <div className="flex items-center gap-2">
                      <i className={StyleService.getLanguageIcon(language)} />
                      {language}
                    </div>
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>

            <Select
              value={sortBy}
              onValueChange={(value: "date" | "language" | "title") =>
                setSortBy(value)
              }
              data-testid="sort-select"
            >
              <SelectTrigger
                aria-label="Sort entries"
                className="w-full sm:w-32 bg-background"
              >
                <SelectValue />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="date">By Date</SelectItem>
                <SelectItem value="language">By Language</SelectItem>
                <SelectItem value="title">By Title</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>

        <div className="mt-3 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-2 text-sm text-muted-foreground">
          <span>
            Showing {visibleEntries.length} of {filteredAndSortedData.length}{" "}
            entries
          </span>
          {(searchQuery ||
            selectedLanguage !== "all" ||
            selectedType !== "all") && (
            <Button
              variant="ghost"
              size="sm"
              onClick={() => {
                setSearchQuery("");
                setSelectedLanguage("all");
                setSelectedType("all");
              }}
              className="h-auto p-1 text-xs self-start sm:self-auto"
            >
              Clear filters
            </Button>
          )}
        </div>
      </Card>

      <AnimatePresence mode="popLayout">
        {visibleEntries.length === 0 ? (
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: -20 }}
            className="text-center py-8 sm:py-12"
          >
            <div className="text-muted-foreground">
              <Search className="w-8 h-8 sm:w-12 sm:h-12 mx-auto mb-4 opacity-50" />
              <p className="text-base sm:text-lg font-medium">
                No entries found
              </p>
              <p className="text-sm">
                Try adjusting your search or filter criteria
              </p>
            </div>
          </motion.div>
        ) : (
          <div className="space-y-3 sm:space-y-4">
            {Object.entries(
              visibleEntries.reduce(
                (groups, entry) => {
                  const date = entry.date;
                  if (!groups[date]) groups[date] = [];
                  groups[date].push(entry);
                  return groups;
                },
                {} as Record<string, typeof visibleEntries>,
              ),
            ).map(([date, entries]) => (
              <motion.div
                key={date}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                exit={{ opacity: 0, y: -20 }}
                transition={{ duration: 0.3 }}
              >
                <Card className="bg-card border-border hover:border-accent/50 transition-all duration-300 hover:shadow-lg">
                  <CardHeader className="pb-3 p-4 sm:p-6">
                    <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
                      <div className="flex items-center gap-3">
                        <Calendar className="w-4 h-4 text-accent shrink-0" />
                        <div className="min-w-0">
                          <span className="text-sm font-medium text-card-foreground block">
                            {formatDate(date)}
                          </span>
                          <div className="flex items-center gap-2 mt-1">
                            <Clock className="w-3 h-3 text-muted-foreground shrink-0" />
                            <span className="text-xs text-muted-foreground">
                              {formatRelativeDate(date)}
                            </span>
                          </div>
                        </div>
                      </div>
                      <Badge
                        variant="secondary"
                        className="bg-muted/50 text-foreground border-border self-start sm:self-auto"
                      >
                        {entries.length} entr{entries.length > 1 ? "ies" : "y"}
                      </Badge>
                    </div>
                  </CardHeader>

                  <CardContent className="space-y-3 p-4 sm:p-6 pt-0">
                    {entries.map(({ algorithm, index }) => (
                      <motion.div
                        key={`${date}-${index}`}
                        initial={{ opacity: 0, x: -20 }}
                        animate={{ opacity: 1, x: 0 }}
                        transition={{ delay: index * 0.1 }}
                        className="border border-border rounded-lg p-3 sm:p-4 bg-muted/10 hover:bg-muted/20 transition-all duration-200 cursor-pointer group"
                        onClick={() => handleAlgorithmClick(date, index)}
                      >
                        <div className="flex flex-col sm:flex-row sm:items-start sm:justify-between gap-3 sm:gap-4">
                          <div className="flex-1 space-y-3 min-w-0">
                            <div className="flex flex-col sm:flex-row sm:items-center gap-2 sm:gap-3">
                              <h3 className="font-semibold text-card-foreground text-base sm:text-lg group-hover:text-accent transition-colors leading-tight">
                                {algorithm.title}
                              </h3>
                              <div className="flex flex-col sm:flex-row sm:items-center gap-2 sm:gap-4 text-xs text-muted-foreground">
                                <div className="flex items-center gap-1">
                                  {algorithm.type === "algorithm" ? (
                                    <Code className="w-3 h-3 shrink-0" />
                                  ) : (
                                    <Layers className="w-3 h-3 shrink-0" />
                                  )}
                                  <span className="capitalize">
                                    {algorithm.type.replace("-", " ")}
                                  </span>
                                </div>
                              </div>
                              <div className={`language-badge w-fit`}>
                                <i
                                  className={StyleService.getLanguageIcon(
                                    algorithm.language,
                                  )}
                                />
                                {algorithm.language}
                              </div>
                            </div>

                            <Separator />

                            <p className="text-muted-foreground leading-relaxed text-sm break-words">
                              {algorithm.notes}
                            </p>
                          </div>

                          <Button
                            variant="outline"
                            size="sm"
                            asChild
                            className="shrink-0 bg-transparent group-hover:bg-accent/10 group-hover:border-accent/50 transition-all w-full sm:w-auto"
                          >
                            <a
                              href={algorithm.link}
                              target="_blank"
                              rel="noopener noreferrer"
                              className="flex items-center justify-center gap-2"
                              onClick={(e) => e.stopPropagation()}
                            >
                              <ExternalLink className="w-3 h-3" />
                              Code
                            </a>
                          </Button>
                        </div>
                      </motion.div>
                    ))}
                  </CardContent>
                </Card>
              </motion.div>
            ))}
          </div>
        )}
      </AnimatePresence>

      {hasMore && (
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          className="text-center pt-4 sm:pt-6"
        >
          <Button
            variant="outline"
            onClick={() => setItemsToShow((prev) => prev + 10)}
            className="bg-background hover:bg-accent/10 w-full sm:w-auto"
          >
            Load More ({filteredAndSortedData.length - itemsToShow} remaining)
          </Button>
        </motion.div>
      )}

      {/* Algorithm Detail Modal */}
      {selectedDate && commits[selectedDate] && (
        <AlgorithmDetailModal
          isOpen={isModalOpen}
          onClose={handleModalClose}
          date={selectedDate}
          algorithms={commits[selectedDate]}
          initialAlgorithmIndex={selectedAlgorithmIndex}
        />
      )}
    </div>
  );
}
