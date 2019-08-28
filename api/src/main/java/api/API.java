package api;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.options;
import static spark.Spark.before;
import static spark.Spark.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.LinkedList;

public class API {
    
    private static final int HTTP_BAD_REQUEST = 400;

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {
    
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
    
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
    
            return "OK";
        });
    
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

    @Data
    static class FiscaliaPayload {
        private String nombre;
        private String descripcion;
        private String telefono;
        private String direccion;
        private Double latitud;
        private Double longitud;
    }

    //from spark docs
    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public static void main(String[] args) {
        API.enableCORS("*", "*", "*");
        get("/fiscalias", (request, response) -> {
            System.out.println("GET /fiscalias");
            try {
                response.status(200);
                response.type("application/json");
                return dataToJson(new FakeORM().obtenerFiscaliasActivas());
            } catch (SQLException e) {
                response.status(HTTP_BAD_REQUEST);
                response.type("application/json");
                e.printStackTrace();
                return dataToJson(new LinkedList<>());
            }
        });

        //insertar nueva fiscalia
        post("/fiscalias", (request, response) -> {
            System.out.println("POST /fiscalias");
            try {
                ObjectMapper mapper = new ObjectMapper();
                FiscaliaPayload creation = mapper.readValue(request.body(), FiscaliaPayload.class);
                new FakeORM().insert(creation.nombre, creation.descripcion, creation.telefono, 
                creation.direccion, creation.latitud, creation.longitud);
                response.status(200);
                response.type("application/json");
                return true;
            } catch (IOException | SQLException e) {
                response.status(HTTP_BAD_REQUEST);
                response.type("application/json");
                e.printStackTrace();
                return false;
            }
        });

        delete("/fiscalias/:id", (request, response) -> {
            System.out.println("DELETE /fiscalias/:id");
            try {
                new FakeORM().delete(Integer.parseInt(request.params("id")));
                response.status(200);
                response.type("application/json");
                return true;
            } catch (SQLException e) {
                response.status(HTTP_BAD_REQUEST);
                response.type("application/json");
                e.printStackTrace();
                return false;
            }
        });
    }

}