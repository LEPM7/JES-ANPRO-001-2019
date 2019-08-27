package api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collector;
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
  // public void selection() {
  // try {
  // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  // Statement s1 = con.createStatement();
  // ResultSet rs = s1.executeQuery("select *from FISCALIA;");
  // if (rs != null) {
  // while (rs.next()) {
  // System.out.println("ID: " + rs.getInt("id"));
  // System.out.println("Nombre: " + rs.getString("nombre"));
  // System.out.println("Descripcion:" + rs.getString("descripcion"));
  // System.out.println("Fecha Creacion:" + rs.getDate("fecha_creacion") + " " +
  // rs.getTime("fecha_creacion"));
  // }
  // }
  // // String result = new result[20];
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // }

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

}