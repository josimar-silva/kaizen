"use client";

import type React from "react";
import { useEffect, useState } from "react";

import { cn } from "@/lib/utils";

interface ScrollHeaderProps {
  children: React.ReactNode;
  className?: string;
}

export function ScrollHeader({
  children,
  className,
}: Readonly<ScrollHeaderProps>) {
  const [isVisible, setIsVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  useEffect(() => {
    const handleScroll = () => {
      const currentScrollY = window.scrollY;

      // Show header when at top or scrolling up
      if (currentScrollY < 10) {
        setIsVisible(true);
      } else if (currentScrollY < lastScrollY) {
        // Scrolling up
        setIsVisible(true);
      } else if (currentScrollY > lastScrollY && currentScrollY > 100) {
        // Scrolling down and past threshold
        setIsVisible(false);
      }

      setLastScrollY(currentScrollY);
    };

    window.addEventListener("scroll", handleScroll, { passive: true });
    return () => window.removeEventListener("scroll", handleScroll);
  }, [lastScrollY]);

  return (
    <header
      className={cn(
        "border-b border-border bg-card/50 backdrop-blur-sm sticky top-0 z-10 transition-transform duration-300 ease-in-out",
        isVisible ? "translate-y-0" : "-translate-y-full",
        className,
      )}
    >
      {children}
    </header>
  );
}
