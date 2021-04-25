FROM openjdk:16-alpine

WORKDIR /deployments
COPY jetty-servlet-demo.jar .

COPY scripts/* /deployments/
RUN sed -e 's/\r$//' -i /deployments/*.sh && chmod +x /deployments/*.sh

EXPOSE 8081
ENTRYPOINT ["./run.sh"]