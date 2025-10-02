"use client";

import { ChevronLeft, ChevronRight } from "lucide-react";
import { useRouter } from "next/navigation";

import { Button } from "@/components/ui/button";

interface SolutionNavigationProps {
  date: string;
  currentIndex: number;
  totalCount: number;
}

export function SolutionNavigation({
  date,
  currentIndex,
  totalCount,
}: Readonly<SolutionNavigationProps>) {
  const router = useRouter();

  const goToPrevious = () => {
    const newIndex = currentIndex > 0 ? currentIndex - 1 : totalCount - 1;
    router.push(`/solution/${date}/${newIndex}`);
  };

  const goToNext = () => {
    const newIndex = currentIndex < totalCount - 1 ? currentIndex + 1 : 0;
    router.push(`/solution/${date}/${newIndex}`);
  };

  return (
    <div className="flex items-center gap-2">
      <Button
        variant="outline"
        size="sm"
        onClick={goToPrevious}
        className="h-9 w-9 p-0 bg-transparent"
        aria-label="previous solution"
      >
        <ChevronLeft className="w-4 h-4" />
      </Button>
      <span className="text-sm text-muted-foreground px-3 whitespace-nowrap">
        {currentIndex + 1} of {totalCount}
      </span>
      <Button
        variant="outline"
        size="sm"
        onClick={goToNext}
        className="h-9 w-9 p-0 bg-transparent"
        aria-label="next solution"
      >
        <ChevronRight className="w-4 h-4" />
      </Button>
    </div>
  );
}
