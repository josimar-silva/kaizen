import Image from "next/image";
import Link from "next/link";

import { CalendarHeatmap } from "@/components/calendar-heatmap";
import { Footer } from "@/components/footer";
import { ScrollHeader } from "@/components/scroll-header";
import { Timeline } from "@/components/timeline";
import { kaizenData } from "@/lib/db";

export default function HomePage() {
  const data = kaizenData;

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <ScrollHeader>
        <header className="border-b border-border bg-card/50 backdrop-blur-sm sticky top-0 z-10">
          <div className="container mx-auto px-4 sm:px-6 py-4 sm:py-8">
            {/* Logo and Title Row */}
            <div className="flex flex-col items-center space-y-4">
              <div className="flex items-center justify-center gap-3 sm:gap-4">
                <Link
                  href="/"
                  className="flex-shrink-0 hover:opacity-80 transition-opacity"
                >
                  <Image
                    src="/images/kaizen-logo.png"
                    alt="Kaizen Logo"
                    width={40}
                    height={40}
                    className="w-8 h-8 sm:w-10 sm:h-10 bg-white rounded-full p-1 shadow-sm"
                    priority
                  />
                </Link>
                <div className="text-center">
                  <h1
                    data-testid="title"
                    className="text-2xl sm:text-4xl font-bold text-foreground font-mono"
                  >
                    Kaizen
                  </h1>
                </div>
              </div>

              {/* Subtitle and Description */}
              <div className="text-center space-y-2">
                <p
                  data-testid="subtitle"
                  className="text-base sm:text-lg text-muted-foreground font-sans"
                >
                  Continuous Improvement Journal
                </p>
                <p
                  data-testid="description"
                  className="text-xs sm:text-sm text-muted-foreground italic"
                >
                  One algorithm a day, keeps the rust away
                </p>
              </div>
            </div>
          </div>
        </header>
      </ScrollHeader>

      {/* Main Content */}
      <main className="container mx-auto px-4 sm:px-6 py-6 sm:py-8 space-y-8 sm:space-y-12">
        {/* Calendar Heatmap Section */}
        <section className="space-y-4 sm:space-y-6">
          <div className="text-center space-y-2">
            <h2 className="text-xl sm:text-2xl font-semibold text-foreground">
              Activity Overview
            </h2>
            <p className="text-sm sm:text-base text-muted-foreground">
              My daily algorithm practice journey
            </p>
          </div>
          <div className="flex justify-center">
            <CalendarHeatmap data={data} />
          </div>
        </section>

        {/* Timeline Section */}
        <section className="space-y-4 sm:space-y-6">
          <div className="text-center space-y-2">
            <h2 className="text-xl sm:text-2xl font-semibold text-foreground">
              Recent Activity
            </h2>
            <p className="text-sm sm:text-base text-muted-foreground">
              Latest algorithms and notes
            </p>
          </div>
          <Timeline commits={data.commits} />
        </section>
      </main>

      <Footer />
    </div>
  );
}
