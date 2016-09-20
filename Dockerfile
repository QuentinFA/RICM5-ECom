FROM glassfish/server

RUN apt-get update \
  && apt-get -y --force-yes install jenkins
RUN sed -i -e "s/#HTTP_PORT=8080/HTTP_PORT=8083/g" /etc/default/jenkins
