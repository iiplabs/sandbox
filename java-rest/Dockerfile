FROM amd64/openjdk:12-alpine
RUN addgroup -S ouruser && appuser -S ouruser -G ouruser
USER ouruser:ouruser
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.iiplabs.web.App"]