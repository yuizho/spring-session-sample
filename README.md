# spring-session-sandbox
This project works on docker-compose.

This branch's codes has the issue related to session, because the session is stored in each App server.
like this.

![](server_config.svg)

The issue is resolved on `manage-session-with-redis` branch.
When you want to check a solution of the issue, please check `manage-session-with-redis` branch.

## Getting Started
```
$ ./mvnw clean install && docker-compose up --build
```

Now the server is running, visit http://localhost:8080/session-attr with your borwser.

## Endponts
- The session sample implemented by @SessionAttributes
  - http://localhost:8080/session-attr
- The PRG pattern sample by frash scope (this scope uses temporary session)
  - http://localhost:8080/flash-scope
