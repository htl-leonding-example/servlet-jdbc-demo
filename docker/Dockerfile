FROM adoptopenjdk/openjdk16

WORKDIR /deployments
COPY jetty-servlet-demo.jar .
RUN ls

COPY scripts/* /deployments/
RUN ls

RUN sed -e 's/\r$//' -i /deployments/*.sh && chmod +x /deployments/*.sh

EXPOSE 8081
ENTRYPOINT ["./run.sh"]