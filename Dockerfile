# Imagen base
FROM azul/zulu-openjdk-alpine:11-jre

#Autor
LABEL maintainer = "antobalbis <antoniobalh@gmail.com>"

# Elegimos la versión de scala y mill
ENV SCALA_VERSION 3.0.1
ENV MILL_VERSION 0.9.9

#establecemos directorio de trabajo
WORKDIR /app/test

# Instalación de scala
RUN apk add curl && \
  mkdir /usr/scala && \
  curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /usr/scala/ && \
  echo >> /usr/scala/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /usr/scala/.bashrc && \
  curl -L https://github.com/com-lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION > /usr/local/bin/mill && \
  chmod +x /usr/local/bin/mill

#Copiamos código y archivo de configuración de mill
COPY eWarehouse/ ./eWarehouse/
COPY build.sc ./

#ejecución de test con mill
CMD mill eWarehouse[$SCALA_VERSION].test