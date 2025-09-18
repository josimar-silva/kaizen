FROM node:22.19.0-trixie-slim@sha256:ba533b824f504e19137d3c961f8f6ef5278b02d8bfa7010eabb1b836d3125ce4 AS deps
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

FROM nginxinc/nginx-unprivileged:alpine@sha256:5b0effa0dadea18909b023518bce97be3d9d41ea7b6d0f669985f678f4fdc203 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html