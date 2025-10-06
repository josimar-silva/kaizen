import { Algorithm } from "@/interfaces/algorithm";

export interface Stats {
  totalAlgorithms: number;
  totalDays: number;
  currentStreak: number;
  longestStreak: number;
  languageDistribution: Record<string, number>;
  monthlyActivity: Record<string, number>;
  sourceDistribution: Record<string, number>;
  mostActiveDay: string;
  typeDistribution: Record<string, number>;
  avgSolutionsPerWeek: number;
  daysSinceFirstActivity: number;
}

export type Commits = Record<string, Algorithm[]>;

export interface KaizenData {
  stats: Stats;
  commits: Commits;
}
