package api.util;

import spark.Spark;

public class Configuration {

  public Configuration() {
    Spark.port(5503);

    Spark.options("/*", (request, response) -> {
      String accessControlRequestHeaders =
          request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
      }

      String accessControlRequestMethod =
          request.headers("Access-Control-Request-Method");
      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
      }

      return "OK";
    });

    Spark.before(
        (request,
         response) -> response.header("Access-Control-Allow-Origin", "*"));
  }
}
