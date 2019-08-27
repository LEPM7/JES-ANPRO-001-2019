package api;

import static spark.Spark.get;

public class API {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            FakeORM orm = new FakeORM();
            try {
            orm.insert("Fiscalia Nombre", "Fiscalia Descripcion", "12345678", "mz i etc etc", 0.0, 0.0);
            }catch(Exception e){
                e.printStackTrace();
            }
            return "hello from sparkjava.com";
        });
    }

}