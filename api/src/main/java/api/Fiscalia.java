package api;

import lombok.Data;

@Data
class Fiscalia {
    public Integer id;
    public String nombre;
    public String descripcion;
    public String telefono;
    public String direccion;
    public Double latitud;
    public Double longitud;
    public Boolean active;

    public Fiscalia(Integer id, String nombre, String descripcion, String telefono, String direccion, Double latitud,
            Double longitud, Boolean active) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.active = active;
    }
}
