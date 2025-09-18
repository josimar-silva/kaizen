"use client";

import { useState } from "react";

import { AlgorithmDetailModal } from "@/components/algorithm-detail-modal";
import { CalendarHeatmap } from "@/components/calendar-heatmap";
import { Timeline } from "@/components/timeline";
import { KaizenData } from "@/interfaces/kaizen-data";

interface KaizenClientWrapperProps {
  data: KaizenData;
  showTimeline?: boolean;
}

export function KaizenClientWrapper({
  data,
  showTimeline = false,
}: Readonly<KaizenClientWrapperProps>) {
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

  return (
    <>
      {showTimeline ? (
        <Timeline data={data} onAlgorithmClick={handleAlgorithmClick} />
      ) : (
        <CalendarHeatmap data={data} />
      )}

      {/* Algorithm Detail Modal */}
      {selectedDate && data[selectedDate] && (
        <AlgorithmDetailModal
          isOpen={isModalOpen}
          onClose={handleModalClose}
          date={selectedDate}
          algorithms={data[selectedDate]}
          initialAlgorithmIndex={selectedAlgorithmIndex}
        />
      )}
    </>
  );
}
