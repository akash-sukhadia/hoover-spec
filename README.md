# pre-requisite to run this service

- Git
- Docker


# Follow below steps to run this service

- Take git clone of this repository in your machine.
    e.g : git clone https://github.com/akash-sukhadia/hoover-spec.git

- chnage your current directory to hoover-spec.
    e.g : cd hoover-spec

- Run docker compose up command to run micro-service and postgres docker container in your machine. (Make sure Docker Daemon is up)
    e.g : docker compose up

- Try to access http://localhost:8080/swagger-ui/index.html in your browser. This should give you swagger documentation for micro-service.

- you can use below curl to execute hoover functionality through POST API.
    e.g : curl -X 'POST' \
  'http://localhost:8080/hoover' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "roomSize": [5, 5],
  "coords": [
    0, 0
  ],
  "patches": [
    [
      0, 1
    ]
  ],
  "instructions": "NW"
}'

- you can get previous executed hoovers using GET API by id.
   e.g : curl -X 'GET' \
  'http://localhost:8080/hoover/3' \
  -H 'accept: */*'

- you can get all previous executed hoovers using GET API.
   e.g : curl -X 'GET' \
  'http://localhost:8080/hoover' \
  -H 'accept: */*'
