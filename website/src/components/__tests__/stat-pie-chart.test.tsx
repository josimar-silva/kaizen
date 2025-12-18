import { render, screen } from "@testing-library/react";

import { StatPieChart } from "../stat-pie-chart";

jest.mock("recharts", () => ({
  ResponsiveContainer: ({ children }: any) => <div>{children}</div>,
  PieChart: ({ children }: any) => <div>{children}</div>,
  Pie: ({ children }: any) => <div>{children}</div>,
  Cell: ({ children }: any) => <div>{children}</div>,
  Tooltip: ({ children }: any) => <div>{children}</div>,
}));

describe("StatPieChart", () => {
  const mockData = [
    {
      name: "Algorithm",
      displayName: "Algorithm",
      value: 19,
    },
  ];

  it("should render the title", () => {
    render(
      <StatPieChart title="Test Title" data={mockData} colors={["#000000"]} />,
    );
    expect(screen.getByText("Test Title")).toBeInTheDocument();
  });
});
