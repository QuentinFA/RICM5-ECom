FROM glassfish/server

RUN apk update \
  && apk add jenkins
RUN sed -i -e "s/#HTTP_PORT=8080/HTTP_PORT=8083/g" /etc/default/jenkins
