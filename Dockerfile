FROM bellsoft/liberica-runtime-container:jre-17-stream-musl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "/dockerimage.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]