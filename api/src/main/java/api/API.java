package api;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class API {
    
    private static final int HTTP_BAD_REQUEST = 400;

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
        get("/", (req, res) -> {
            
            try {
           
            }catch(Exception e){
                e.printStackTrace();
            }
            return "hello from sparkjava.com";
        });

        //insertar nueva fiscalia
        post("/fiscalias", (request, response) -> {
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
    }

}