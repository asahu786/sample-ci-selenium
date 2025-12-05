services:
  springboot-app:
    build: ./app
    container_name: springboot-app
    ports:
      - "8080:8080"   # main app port
      - "8082:8081"   # remap internal 8081 to host 8082 (avoids conflict)
    depends_on:
      - selenium-hub

  selenium-hub:
    image: selenium/hub:4.24.0
    container_name: selenium-hub
    ports:
      - "4444:4444"

  chrome-node:
    image: selenium/node-chrome:4.24.0
    container_name: chrome-node
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
