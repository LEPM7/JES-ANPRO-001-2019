package api;
import lombok.Data;

@Data
class Fiscalia {
    public String nombre;
    public String descripcion;
    public String telefono;
    public String direccion;
    public Double latitud;
    public Double longitud;
    public Boolean active; 
}
