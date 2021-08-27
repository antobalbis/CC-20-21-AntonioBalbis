# Imagen base
FROM azul/zulu-openjdk-alpine:11-jre

ENV SCALA_VERSION 2.13.2
ENV MILL_VERSION 0.9.5

LABEL org.opencontainers.image.source https://github.com/antobalbis/easywarehouse

WORKDIR /app/test

RUN apk update && apk upgrade
RUN apk add curl

# Instalación de scala
RUN curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /root/.bashrc

# Instalación de mill
RUN \
  curl -L -o /usr/local/bin/mill https://github.com/lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION && \
  chmod +x /usr/local/bin/mill && \
  touch build.sc && \
  mill -i resolve _ && \
  rm build.sc
