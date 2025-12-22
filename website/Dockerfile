FROM node:24.12.0-trixie-slim@sha256:9ad7e7db423b2ca7ddcc01568da872701ef6171505bd823978736247885c7eb4 AS deps
WORKDIR /app

COPY package.json package-lock.json ./

# Install dependencies
RUN npm ci --ignore-scripts

# Rebuild the source code only when needed
FROM deps AS builder
WORKDIR /app
COPY . .

RUN npm run build

FROM nginxinc/nginx-unprivileged:alpine@sha256:c3b9dd893f9ecd7046d3ac37b0dc218ff3c65fe53acd5cb5e1f97f3988272760 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html
COPY --from=builder /app/public/solution /usr/share/nginx/html/solution
