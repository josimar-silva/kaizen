import { StatPieChart } from "@/components/stat-pie-chart";
import { Stats } from "@/interfaces/kaizen-data";
import StyleService from "@/lib/style-service";

interface KaizenStatsProps {
  stats: Stats;
}

export function KaizenStats({ stats }: Readonly<KaizenStatsProps>) {
  const languageChartData = Object.entries(stats.languageDistribution).map(
    ([language, count]) => ({
      name: language,
      displayName: language,
      value: count,
    }),
  );

  const typeChartData = Object.entries(stats.typeDistribution).map(
    ([type, count]) => ({
      name: type,
      displayName: type === "algorithm" ? "Algorithm" : "System Design",
      value: count,
    }),
  );

  const sourceChartData = Object.entries(stats.sourceDistribution).map(
    ([source, count]) => ({
      name: source,
      displayName: StyleService.getHumanReadableSourceName(source),
      value: count,
    }),
  );

  return (
    <section className="space-y-4 sm:space-y-6 max-w-5xl mx-auto">
      <div className="text-center space-y-2">
        <h2 className="text-xl sm:text-2xl font-semibold text-foreground">
          Progress Overview
        </h2>
        <p className="text-sm sm:text-base text-muted-foreground">
          Key metrics and insights
        </p>
      </div>

      <div className="grid grid-cols-2 gap-3 md:grid-cols-4">
        <div className="rounded-lg border border-border bg-card/30 p-4 text-center">
          <div className="text-lg sm:text-2xl font-bold text-accent">
            {stats.avgSolutionsPerWeek}
          </div>
          <p className="text-xs text-muted-foreground">Avg/Week</p>
        </div>

        <div className="rounded-lg border border-border bg-card/30 p-4 text-center">
          <div className="text-lg sm:text-2xl font-bold text-accent">
            {stats.longestStreak} days
          </div>
          <p className="text-xs text-muted-foreground">Best Streak</p>
        </div>

        <div className="rounded-lg border border-border bg-card/30 p-4 text-center">
          <div className="text-lg sm:text-2xl font-bold text-accent">
            {stats.daysSinceLastActivity} days
          </div>
          <p className="text-xs text-muted-foreground">Since First Commit</p>
        </div>

        <div className="rounded-lg border border-border bg-card/30 p-4 text-center">
          <div className="text-lg sm:text-2xl font-bold text-accent">
            {stats.mostActiveDay}
          </div>
          <p className="text-xs text-muted-foreground">Most Active Day</p>
        </div>
      </div>

      <div className="grid grid-cols-1 gap-4 lg:grid-cols-3">
        <StatPieChart
          title="Types"
          data={typeChartData}
          colors={typeChartData.map((entry) =>
            StyleService.getSolutionTypeColor(entry.name.toLowerCase()),
          )}
        />
        <StatPieChart
          title="Sources"
          data={sourceChartData}
          colors={sourceChartData.map((entry) =>
            StyleService.getSourceColor(entry.name),
          )}
        />
        <StatPieChart
          title="Languages"
          data={languageChartData}
          colors={languageChartData.map((entry) =>
            StyleService.getLanguageColor(entry.name),
          )}
        />
      </div>
    </section>
  );
}
