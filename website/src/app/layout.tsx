import "./globals.css";

import { GeistMono } from "geist/font/mono";
import { GeistSans } from "geist/font/sans";
import type { Metadata, Viewport } from "next";
import type React from "react";
import { Suspense } from "react";

import { GithubIcon } from "@/components/icons/GithubIcon";

export const viewport: Viewport = {
  themeColor: [{ media: "(prefers-color-scheme: dark)", color: "#000000" }],
  width: "device-width",
  initialScale: 1,
};

export const metadata: Metadata = {
  title: "Kaizen – Continuous Improvement Journal",
  description: "One algorithm a day, keeps the rust away",
  keywords: ["kaizen", "algorithms", "system-design"],
  authors: [{ name: "Josimar Silva" }],
  icons: {
    icon: "/icons/icon.svg",
    apple: "/icons/apple-touch-icon.png",
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
          href="https://cdn.jsdelivr.net/gh/devicons/devicon@2.16.0/devicon.min.css"
          integrity="sha384-DjehTlU5SubGD1zUGe78SkwLgw+q2y9DXnmy3EaDbXA9nvFMPW3LP5verNrWr9rW"
        />
      </head>
      <body className={`font-sans ${GeistSans.variable} ${GeistMono.variable}`}>
        <Suspense fallback={null}>{children}</Suspense>
        <div className="fixed bottom-4 right-4 z-50">
          <a
            href="https://github.com/josimar-silva/kaizen"
            target="_blank"
            rel="noopener noreferrer"
            className="bg-background text-white p-3 rounded-full shadow-lg hover:bg-gray-700 transition-colors duration-200 flex items-center justify-center"
            aria-label="GitHub Repository"
          >
            <GithubIcon className="hover:scale-110" />
          </a>
        </div>
      </body>
    </html>
  );
}
