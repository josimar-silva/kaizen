"use client";

import {
  Cell,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
  TooltipProps,
} from "recharts";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";

interface ChartData {
  name: string;
  displayName: string;
  value: number;
}

interface StatPieChartProps {
  title: string;
  data: ChartData[];
  colors: string[];
}

const StatTooltip = ({ active, payload }: TooltipProps<number, string>) => {
  if (active && payload?.length) {
    return (
      <div className="rounded-md border bg-popover p-2 text-popover-foreground shadow-md animate-in fade-in-0 zoom-in-95">
        <p className="font-medium">{payload[0].payload.name}</p>
        <p className="text-sm text-muted-foreground">
          {payload[0].value} solutions
        </p>
      </div>
    );
  }
  return null;
};

export function StatPieChart({
  title,
  data,
  colors,
}: Readonly<StatPieChartProps>) {
  const palette = colors.length > 0 ? colors : ["#808080"];

  return (
    <Card className="border-border/50">
      <CardHeader className="pb-3">
        <CardTitle
          className="text-sm font-medium text-muted-foreground"
          data-testid={`chart-${title.toLowerCase()}`}
        >
          {title}
        </CardTitle>
      </CardHeader>
      <CardContent className="pt-0">
        <div className="h-32">
          <ResponsiveContainer width="100%" height="100%">
            <PieChart>
              <Pie
                data={data}
                cx="50%"
                cy="50%"
                innerRadius={20}
                outerRadius={50}
                paddingAngle={1}
                dataKey="value"
                stroke="none"
              >
                {data.map((entry, index) => (
                  <Cell
                    key={`cell-${entry.name}`}
                    fill={palette[index % palette.length]}
                  />
                ))}
              </Pie>
              <Tooltip content={<StatTooltip />} />
            </PieChart>
          </ResponsiveContainer>
        </div>
        <div className="mt-2 flex flex-wrap justify-center gap-2">
          {data.map((item, index) => (
            <div key={item.name} className="flex items-center gap-1 text-xs">
              <div
                className="h-2 w-2 rounded-full"
                style={{ backgroundColor: palette[index % palette.length] }}
              />
              <span className="text-muted-foreground">{item.displayName}</span>
            </div>
          ))}
        </div>
      </CardContent>
    </Card>
  );
}
