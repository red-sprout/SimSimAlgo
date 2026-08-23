FROM node:22-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
COPY tsconfig.json ./
COPY src ./src
RUN npm run build

FROM node:22-alpine
RUN apk add --no-cache git openssh-client python3 py3-pip \
    && python3 -m venv /opt/oj \
    && /opt/oj/bin/pip install --no-cache-dir setuptools online-judge-tools
WORKDIR /app
ENV NODE_ENV=production
ENV PATH="/opt/oj/bin:${PATH}"
COPY package.json package-lock.json ./
RUN npm ci --omit=dev
COPY --from=build /app/dist ./dist
EXPOSE 3000
CMD ["node", "dist/src/server.js"]
