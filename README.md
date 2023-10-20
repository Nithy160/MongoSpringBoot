# MongoSpringBoot

Spring boot version used: 3.1.5
Java runtime: 21

This application uses a docker file to generate image springbootdocker. It connects to a mongodb running on a docker container.

**Step 1:** Run a docker container for mongodb with the below command.
docker run -d -p 27017:27017 --name mongodocker mongo:latest

**Step 2:** Create the application jar and then docker image with below commands
mvn clean install
docker build -d -p 8091:8091 --name springbootdocker:1.0 .

**Step 3.1:** Execute the docker compose file with the below command.
docker-compose up
**Step 3.2:** Alternatively, below command can be used to link the applications and run together.
docker run -d -p 8091:8091 --link mongodocker:mongo springbootdocker:1.0

**Step 4:** Access the below endpoint to get results:
http://localhost:8091/users/findWith?name=nithy&teamName=IT
**Sample response:**
[
    {
        "id": 1,
        "name": "nithy",
        "teamName": "IT",
        "salary": 100000
    }
]





