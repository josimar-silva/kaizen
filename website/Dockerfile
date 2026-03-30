FROM node:24.14.0-trixie-slim@sha256:8c8f12cedb96c3b59642cf30d713943c2b223990c9919b96a141681f62e6e292 AS deps
WORKDIR /app

COPY package.json package-lock.json ./

# Install dependencies
RUN npm ci --ignore-scripts

# Rebuild the source code only when needed
FROM deps AS builder
WORKDIR /app
COPY . .

RUN npm run build

FROM nginxinc/nginx-unprivileged:alpine@sha256:f99cc61bf1719f30230602036314ff6ba5dcede8965c5ed3ded71b8bbced3723 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html
COPY --from=builder /app/public/solution /usr/share/nginx/html/solution
