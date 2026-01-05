FROM node:24.12.0-trixie-slim@sha256:b05474903f463ce4064c09986525e6588c3e66c51b69be9c93a39fb359f883ce AS deps
WORKDIR /app

COPY package.json package-lock.json ./

# Install dependencies
RUN npm ci --ignore-scripts

# Rebuild the source code only when needed
FROM deps AS builder
WORKDIR /app
COPY . .

RUN npm run build

FROM nginxinc/nginx-unprivileged:alpine@sha256:c2351965a68a252c32e0c03a5a7b2144087df078de199a3a67d35d41a41f4760 AS runner
USER root
RUN apk --no-cache upgrade

# Image should be created with a non-root user. More details:
# https://hub.docker.com/r/nginxinc/nginx-unprivileged
USER nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/website /usr/share/nginx/html
COPY --from=builder /app/public/solution /usr/share/nginx/html/solution
