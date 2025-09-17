import "./globals.css";

import { GeistMono } from "geist/font/mono";
import { GeistSans } from "geist/font/sans";
import type { Metadata, Viewport } from "next";
import type React from "react";
import { Suspense } from "react";

export const viewport: Viewport = {
  themeColor: "purple",
  width: "device-width",
  initialScale: 1,
};

export const metadata: Metadata = {
  title: "Kaizen – Continuous Improvement Journal",
  description: "One algorithm a day, keeps the rust away",
  keywords: ["kaizen", "algorithms", "system-design"],
  authors: [{ name: "Josimar Silva" }],
  icons: {
    icon: "/favicon/favicon.ico",
    apple: "/favicon/apple-touch-icon.png",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className="dark">
      <head>
        <title>Kaizen – Continuous Improvement Journal</title>
        <link
          rel="stylesheet"
          type="text/css"
          href="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/devicon.min.css"
        />
      </head>
      <body className={`font-sans ${GeistSans.variable} ${GeistMono.variable}`}>
        <Suspense fallback={null}>{children}</Suspense>
      </body>
    </html>
  );
}
