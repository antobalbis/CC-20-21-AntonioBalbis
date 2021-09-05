# Imagen base
FROM azul/zulu-openjdk-alpine:11-jre

#Autor
LABEL maintainer = "antobalbis <antoniobalh@gmail.com>"

# Elegimos la versión de scala y mill
ENV SCALA_VERSION 3.0.1
ENV MILL_VERSION 0.9.9

#establecemos directorio de trabajo
WORKDIR /app/test

#curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /usr/scala/ && \
#  echo >> /usr/scala/.bashrc && \

# Instalación de scala
RUN apk add curl && \
  curl -fLo coursier https://git.io/coursier-cli && chmod +x coursier && ./coursier install scala3-compiler && \
  curl -L https://github.com/com-lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION > /usr/local/bin/mill && \
  chmod +x /usr/local/bin/mill && \
  apk add --no-cache make

#Copiamos código y archivo de configuración de mill
COPY eWarehouse/ ./eWarehouse/
COPY build.sc ./

#ejecución de test con mill
CMD mill eWarehouse[$SCALA_VERSION].test