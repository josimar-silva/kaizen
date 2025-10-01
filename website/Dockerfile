FROM node:22.20.0-trixie-slim@sha256:5734b5fa015fff2e519adfe14e6fffa177a703d73b8b4a9c48cdce538e34b0fc AS deps
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

FROM nginxinc/nginx-unprivileged:alpine@sha256:2bae49de8b7394f1ef4b6ddc5d8a6d07467d82883d20fb54556ce90b445870f9 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html