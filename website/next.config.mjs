/** @type {import('next').NextConfig} */
const nextConfig = {
  output: "export",
  distDir: "dist/website",
  trailingSlash: true,
  eslint: {
    ignoreDuringBuilds: true,
  },
  typescript: {
    ignoreBuildErrors: true,
  },
  images: {
    unoptimized: true,
  },
  env: {
    APP_VERSION: "0.3.0",
  },
};

export default nextConfig;
