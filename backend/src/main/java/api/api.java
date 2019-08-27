package api;

import static spark.Spark.get;

public class API {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            return "hello from sparkjava.com";
        });
    }

}