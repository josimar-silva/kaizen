/** @type {import('next').NextConfig} */
import withPWA from "next-pwa";

const pwaConfig = withPWA({
  dest: "public",
  disable: process.env.NODE_ENV === "development",
  register: true,
  scope: "/app",
});

const nextConfig = {
  output: "export",
  distDir: "dist/website",
  trailingSlash: true,
  reactStrictMode: true,
  eslint: {
    ignoreDuringBuilds: true,
  },
  typescript: {
    ignoreBuildErrors: true,
  },
  images: {
    unoptimized: false,
    loader: "custom",
    loaderFile: "./image-loader.ts",
  },
  env: {
    APP_VERSION: "0.5.0",
  },
};

export default pwaConfig(nextConfig);
