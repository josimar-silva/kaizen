import { KaizenData } from "@/interfaces/kaizen-data";

import DataService from "./data-service";

export interface KaizenStats {
  totalAlgorithms: number;
  totalDays: number;
  currentStreak: number;
  longestStreak: number;
  languageDistribution: Record<string, number>;
  monthlyActivity: Record<string, number>;
}

export async function getKaizenData(): Promise<KaizenData> {
  return DataService.data;
}

export function calculateStats(data: KaizenData): KaizenStats {
  const entries = Object.entries(data);
  const totalDays = entries.length;
  const totalAlgorithms = entries.reduce(
    (sum, [, algorithms]) => sum + algorithms.length,
    0,
  );

  // Language distribution
  const languageDistribution: Record<string, number> = {};
  entries.forEach(([, algorithms]) => {
    algorithms.forEach((algorithm) => {
      languageDistribution[algorithm.language] =
        (languageDistribution[algorithm.language] || 0) + 1;
    });
  });

  // Monthly activity
  const monthlyActivity: Record<string, number> = {};
  entries.forEach(([date, algorithms]) => {
    const month = date.substring(0, 7); // YYYY-MM format
    monthlyActivity[month] = (monthlyActivity[month] || 0) + algorithms.length;
  });

  // Calculate streaks
  const currentStreak = calculateCurrentStreak(data);
  const longestStreak = calculateLongestStreak(data);

  return {
    totalAlgorithms,
    totalDays,
    currentStreak,
    longestStreak,
    languageDistribution,
    monthlyActivity,
  };
}

// Calculate current streak
function calculateCurrentStreak(data: KaizenData): number {
  let streak = 0;
  const today = new Date();

  for (let i = 0; i < 365; i++) {
    const date = new Date(today);
    date.setDate(date.getDate() - i);
    const dateString = date.toISOString().split("T")[0];

    if (data[dateString] && data[dateString].length > 0) {
      streak++;
    } else {
      break;
    }
  }

  return streak;
}

// Calculate longest streak
function calculateLongestStreak(data: KaizenData): number {
  const dates = Object.keys(data).sort();
  let maxStreak = 0;
  let currentStreak = 0;
  let lastDate: Date | null = null;

  dates.forEach((dateString) => {
    const date = new Date(dateString);
    const algorithms = data[dateString];

    if (algorithms && algorithms.length > 0) {
      if (lastDate && isConsecutiveDay(lastDate, date)) {
        currentStreak++;
      } else {
        currentStreak = 1;
      }
      maxStreak = Math.max(maxStreak, currentStreak);
      lastDate = date;
    } else {
      currentStreak = 0;
      lastDate = null;
    }
  });

  return maxStreak;
}

// Check if two dates are consecutive
function isConsecutiveDay(date1: Date, date2: Date): boolean {
  const diffTime = date2.getTime() - date1.getTime();
  const diffDays = diffTime / (1000 * 60 * 60 * 24);
  return diffDays === 1;
}
