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

FROM nginxinc/nginx-unprivileged:alpine@sha256:ccbac1a4c20a8b41c5dd1691bd91d63eda3b7989d643a33fd47841838519bfb9 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html
COPY --from=builder /app/public/solution /usr/share/nginx/html/solution
