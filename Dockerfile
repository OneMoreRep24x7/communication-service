FROM openjdk:17
EXPOSE 8083
ADD target/communicationservice-onemorerep.jar communicationservice-onemorerep.jar
ENTRYPOINT ["java","-jar","/communicationservice-onemorerep.jar"]