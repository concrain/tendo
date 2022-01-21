# Build and Run from Command Line
mvn clean spring-boot:run 

# CLI Commands once the application is running
AVAILABLE COMMANDS:

TENDO:> help

Built-In Commands
        clear: Clear the shell screen. \
        exit, quit: Exit the shell. \
        help: Display help about available commands. \
        history: Display or save the history of previously run commands. \
        script: Read and execute commands from a file. \
        stacktrace: Display the full stacktrace of the last error.

Custom Commands 
        create-survey:          Take patient survey. \
        display-report:         Display the report. \
        doctor-list:            Display list of doctors. \
        import-json:            Import data. \
        patient-list:           Display list of patients. \
        report-list:            Display list of reports. \
        update-survey:          Update patient survey.

# Docker Postgres
docker pull postgres \
docker run --name my-ps -e POSTGRES_PASSWORD=mysecret -d -p 5432:5432 postgres \
docker ps 

CONTAINER ID   IMAGE      COMMAND                  CREATED         STATUS         PORTS                                       NAMES \
< container-id >   postgres   "docker-entrypoint.s…"   4 minutes ago   Up 4 minutes   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   my-ps \

docker exec -it < container-id > bash 

psql -h localhost -p 5432 -U postgres \
CREATE USER russel; \
CREATE DATABASE tendo; \
CREATE SCHEMA tendo; \
GRANT ALL ON SCHEMA tendo TO russel; \
ALTER USER russel with PASSWORD 'password'; \
\c tendo russel
