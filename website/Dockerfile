FROM node:22.20.0-trixie-slim@sha256:c7a6d80f9d76726291228f8878cd844cb15fcbdd2a4d54591e7d2b1903efb4e1 AS deps
WORKDIR /app

# Install pnpm globally
RUN npm install -g pnpm

COPY package.json pnpm-lock.yaml ./

# Install dependencies
RUN pnpm install --frozen-lockfile --strict-peer-dependencies --ignore-scripts

# Rebuild the source code only when needed
FROM deps AS builder
WORKDIR /app
COPY . .

RUN pnpm build

FROM nginxinc/nginx-unprivileged:alpine@sha256:9fda08cc7f7580567e9d8c477420d7beadb9387d4004074c89f41f9d90ecf300 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html