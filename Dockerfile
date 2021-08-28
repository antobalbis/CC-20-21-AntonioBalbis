# Imagen base
FROM azul/zulu-openjdk-alpine:11-jre

#Autor
LABEL maintainer = "antobalbis <antoniobalh@gmail.com>"

# Elegimos la versión de scala y mill
ENV SCALA_VERSION 2.13.2
ENV MILL_VERSION 0.9.5

#establecemos directorio de trabajo
WORKDIR /app/test

#añadimos curl
RUN apk add --no-cache curl

# Instalación de scala
RUN curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /root/.bashrc

# Instalación de mill
RUN curl -L https://github.com/com-lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION > /usr/local/bin/mill && chmod +x /usr/local/bin/mill

#eliminamos curl
RUN apk del curl

#Copiamos código y archivo de configuración de mill
COPY ./eWarehouse /app/test/
COPY ./build.sc /app/test/

#ejecución de test con mill
CMD mill eWarehouse.test