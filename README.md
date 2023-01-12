# Football League API
## Introduction
### The API uses the following:
- Tests inspired by this [O'Reilly tutorial](https://learning.oreilly.com/videos/spring-and-spring/0636920620938/)
- REST Endpoints [Simple example](https://spring.io/guides/tutorials/rest/)
- Input Validation [Spring Starter Validation](https://www.baeldung.com/spring-boot-bean-validation)
- Data Storage with [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- Fault Tolerance with [Resilience4j](https://resilience4j.readme.io)
- Observability with [Prometheus + more](https://spring.io/blog/2022/10/12/observability-with-spring-boot-3)
<!--End of list-->
### The API contains endpoints to:
- Create and Read a Team
- Add a match result to update the League Table of Teams in a League
- Retrieve performance data on a Team in a League
- Retrieve the list of Teams in a League sorted by position
<!--End of list-->
### The application is preloaded with the following data:
- Premier League (League object)
- 20 Premier League Teams (Team object) from 2022-23 season
- Premier League table data (LeaguePosition object) as of 12/01/23
<!--End of list-->
