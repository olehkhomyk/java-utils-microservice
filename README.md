# Utils Service [POST_HUB]



### Build .jar

mvn install -DskipTests-true

### Build docker image:

docker build -f docker/Dockerfile -t utils_service_post_hub .

### Run Image:

docker run -d --restart unless-stopped --name utils_service_post_hub -p 8185:8185 -e PROFILE=local-idea utils_service_post_hub

### Clean all cache/images/containers which are not in use.

docker system prune -a
