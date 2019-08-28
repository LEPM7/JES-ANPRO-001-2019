package api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// TODO: usar un ORM real
public class FakeORM {

  String userName = "sa";
  String password = "Qwerty1234";
  String url = "jdbc:sqlserver://sql-server-db:1433;database=master";

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.userName, this.password);
  }

  // TODO: manejar transaccionalidad
  public boolean insert(String nombre, String descripcion, String telefono, String direccion, Double latitud,
      Double longitud) throws SQLException {
    boolean result;
    CallableStatement pstmt = this.getConnection().prepareCall("{ call dbo.FiscaliaInsertar(?,?,?,?,?,?)}");
    pstmt.setString(1, nombre);
    pstmt.setString(2, descripcion);
    pstmt.setString(3, telefono);
    pstmt.setString(4, direccion);
    pstmt.setDouble(5, latitud);
    pstmt.setDouble(6, longitud);
    result = pstmt.execute();
    return result;
  }

  // https://programmaticponderings.com/2012/08/24/calling-sql-server-stored-procedures-with-java-using-jdbc/
  // http://sparkjava.com/tutorials/reducing-java-boilerplate
  public List obtenerFiscaliasActivas() throws SQLException {
    CallableStatement pstmt = this.getConnection().prepareCall("{ call dbo.FiscaliaObtenerActivas()}");
    pstmt.execute();
    ResultSet rs = pstmt.getResultSet();
    Map<Integer,Fiscalia> fiscalias = new HashMap<Integer,Fiscalia>();
    if (rs != null) {
      while (rs.next()) {
        Fiscalia f = new Fiscalia(
          rs.getInt("fiscaliaId"), 
          rs.getString("nombre"), 
          rs.getString("descripcion"), 
          rs.getString("telefono"), 
          rs.getString("direccion"), 
          rs.getDouble("latitud"), 
          rs.getDouble("longitud"), 
          rs.getBoolean("activa"));
          fiscalias.put(f.id, f);
      }
    }
    return fiscalias.keySet().stream().sorted().map((id) -> fiscalias.get(id)).collect(Collectors.toList());
  }

  public boolean delete(Integer id) throws SQLException {
    boolean result;
    CallableStatement pstmt = this.getConnection().prepareCall("{ call dbo.FiscaliaBorrar(?)}");
    pstmt.setInt(1, id);
    result = pstmt.execute();
    return result;
  }

}