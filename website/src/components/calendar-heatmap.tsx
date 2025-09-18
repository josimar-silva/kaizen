"use client";

import { motion } from "framer-motion";

import { Card } from "@/components/ui/card";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";
import { KaizenData } from "@/interfaces/kaizen-data";
import { calculateStats } from "@/lib/stats-service";

interface CalendarHeatmapProps {
  data: KaizenData;
}

export function CalendarHeatmap({ data }: Readonly<CalendarHeatmapProps>) {
  // Generate the last 365 days
  const generateDates = () => {
    const dates = [];
    const today = new Date();
    for (let i = 364; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(date.getDate() - i);
      dates.push(date.toISOString().split("T")[0]);
    }
    return dates;
  };

  const dates = generateDates();

  const getActivityLevel = (date: string): number => {
    const algorithms = data[date];
    if (!algorithms) return 0;
    if (algorithms.length === 1) return 1;
    if (algorithms.length === 2) return 2;
    if (algorithms.length === 3) return 3;
    return 4;
  };

  const getWeeksArray = () => {
    const weeks: string[][] = [];
    let currentWeek: string[] = [];

    dates.forEach((date, index) => {
      const dayOfWeek = new Date(date).getDay();

      // If it's the first date, pad the beginning of the week
      if (index === 0) {
        for (let i = 0; i < dayOfWeek; i++) {
          currentWeek.push("");
        }
      }

      currentWeek.push(date);

      // If it's Sunday or the last date, complete the week
      if (dayOfWeek === 6 || index === dates.length - 1) {
        // Pad the end of the week if necessary
        while (currentWeek.length < 7) {
          currentWeek.push("");
        }
        weeks.push([...currentWeek]);
        currentWeek = [];
      }
    });

    return weeks;
  };

  const weeks = getWeeksArray();
  const monthLabels = [
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
  ];
  const dayLabels = ["S", "M", "T", "W", "T", "F", "S"];

  const getMonthLabelsForYear = () => {
    const labels: Array<{ month: string; position: number }> = [];
    const startDate = new Date(dates[0]);
    const endDate = new Date(dates[dates.length - 1]);

    // Start from the first month in our date range
    const currentDate = new Date(
      startDate.getFullYear(),
      startDate.getMonth(),
      1,
    );

    while (currentDate <= endDate) {
      // Calculate which week this month starts in
      const weekIndex = dates.findIndex((date) => {
        const dateObj = new Date(date);
        return (
          dateObj.getFullYear() === currentDate.getFullYear() &&
          dateObj.getMonth() === currentDate.getMonth()
        );
      });

      if (weekIndex >= 0) {
        const weeksFromStart = Math.floor(weekIndex / 7);
        labels.push({
          month: monthLabels[currentDate.getMonth()],
          position: weeksFromStart,
        });
      }

      // Move to next month
      currentDate.setMonth(currentDate.getMonth() + 1);
    }

    return labels;
  };

  const stats = calculateStats(data);
  const yearMonthLabels = getMonthLabelsForYear();

  return (
    <Card className="p-3 sm:p-6 bg-card border-border max-w-5xl mx-auto">
      <div className="space-y-4 sm:space-y-6">
        <div className="grid grid-cols-2 lg:grid-cols-4 gap-2 sm:gap-4 text-center">
          <div className="space-y-1">
            <div className="text-lg sm:text-2xl font-bold text-accent">
              {stats.totalAlgorithms}
            </div>
            <div className="text-xs text-muted-foreground">
              Total Algorithms
            </div>
          </div>
          <div className="space-y-1">
            <div className="text-lg sm:text-2xl font-bold text-accent">
              {stats.totalDays}
            </div>
            <div className="text-xs text-muted-foreground">Active Days</div>
          </div>
          <div className="space-y-1">
            <div className="text-lg sm:text-2xl font-bold text-accent">
              {stats.currentStreak}
            </div>
            <div className="text-xs text-muted-foreground">Current Streak</div>
          </div>
          <div className="space-y-1">
            <div className="text-lg sm:text-2xl font-bold text-accent">
              {stats.longestStreak}
            </div>
            <div className="text-xs text-muted-foreground">Longest Streak</div>
          </div>
        </div>

        <div className="relative">
          <div className="overflow-x-auto pb-2 pr-10">
            <TooltipProvider>
              <div className="w-full">
                <div className="hidden sm:flex gap-1 ml-6 mb-4 relative overflow-hidden">
                  {yearMonthLabels.map((label, index) => (
                    <div
                      key={`${label.month}-${index}`}
                      className="text-xs text-muted-foreground text-center absolute"
                      style={{
                        left: `calc(${label.position * (100 / weeks.length)}% + 1.5rem)`,
                        width: "48px",
                      }}
                    >
                      {label.month}
                    </div>
                  ))}
                </div>

                <div className="flex gap-1 w-full">
                  <div className="flex flex-col gap-1 w-4 sm:w-6 flex-shrink-0">
                    {dayLabels.map((day, index) => (
                      <div
                        key={day}
                        className="text-xs text-muted-foreground heatmap-day-label flex items-center justify-end pr-1"
                      >
                        {index % 2 === 1 ? day : ""}
                      </div>
                    ))}
                  </div>

                  <div className="flex-1 min-w-0">
                    <div
                      className="heatmap-grid"
                      style={{
                        display: "grid",
                        gridTemplateColumns: `repeat(${weeks.length}, 1fr)`,
                        gap: "clamp(1px, 0.2vw, 4px)",
                        width: "100%",
                      }}
                    >
                      {weeks.map((week, weekIndex) => (
                        <div
                          key={weekIndex}
                          className="heatmap-week"
                          style={{
                            display: "grid",
                            gridTemplateRows: "repeat(7, 1fr)",
                            gap: "clamp(1px, 0.2vw, 4px)",
                          }}
                        >
                          {week.map((date, dayIndex) => {
                            if (!date) {
                              return (
                                <div
                                  key={`empty-${dayIndex}`}
                                  className="heatmap-cell-empty"
                                />
                              );
                            }

                            const level = getActivityLevel(date);
                            const algorithms = data[date] || [];
                            const isToday =
                              date === new Date().toISOString().split("T")[0];

                            return (
                              <Tooltip key={date}>
                                <TooltipTrigger asChild>
                                  <motion.div
                                    className={`heatmap-cell heatmap-level-${level} cursor-pointer relative touch-manipulation ${
                                      isToday
                                        ? "ring-1 ring-accent ring-offset-1 ring-offset-background"
                                        : ""
                                    }`}
                                    whileHover={{ scale: 1.1 }}
                                    whileTap={{ scale: 0.95 }}
                                    transition={{
                                      type: "spring",
                                      stiffness: 400,
                                      damping: 17,
                                    }}
                                  />
                                </TooltipTrigger>
                                <TooltipContent side="top" className="max-w-xs">
                                  <div className="text-sm space-y-2">
                                    <div className="font-medium">
                                      {new Date(date).toLocaleDateString(
                                        "en-US",
                                        {
                                          weekday: "short",
                                          month: "short",
                                          day: "numeric",
                                          year: "numeric",
                                        },
                                      )}
                                    </div>
                                    <div className="text-muted-foreground">
                                      {algorithms.length === 0
                                        ? "No algorithms"
                                        : `${algorithms.length} algorithm${
                                            algorithms.length > 1 ? "s" : ""
                                          }`}
                                    </div>
                                    {algorithms.length > 0 && (
                                      <div className="space-y-1 border-t border-border pt-2">
                                        {algorithms
                                          .slice(0, 3)
                                          .map((algo, index) => (
                                            <div
                                              key={index}
                                              className="text-xs flex items-center gap-2"
                                            >
                                              <span className="font-mono text-accent">
                                                {algo.language}
                                              </span>
                                              <span className="truncate">
                                                {algo.title}
                                              </span>
                                            </div>
                                          ))}
                                        {algorithms.length > 3 && (
                                          <div className="text-xs text-muted-foreground">
                                            +{algorithms.length - 3} more...
                                          </div>
                                        )}
                                      </div>
                                    )}
                                  </div>
                                </TooltipContent>
                              </Tooltip>
                            );
                          })}
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            </TooltipProvider>
          </div>
          <div className="absolute inset-y-0 right-0 w-8 bg-gradient-to-l from-card to-transparent pointer-events-none" />
        </div>

        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-2">
          <div className="flex items-right gap-2 text-xs text-muted-foreground">
            <span className="hidden sm:inline">Less</span>
            <div className="flex gap-1">
              {[0, 1, 2, 3, 4].map((level) => (
                <div
                  key={level}
                  className={`heatmap-cell heatmap-level-${level}`}
                />
              ))}
            </div>
            <span className="hidden sm:inline">More</span>
          </div>
        </div>
      </div>
    </Card>
  );
}
