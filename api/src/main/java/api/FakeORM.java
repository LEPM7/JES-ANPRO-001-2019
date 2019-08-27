package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// TODO: usar un ORM real
public class FakeORM {

  // TODO: manejar transaccionalidad
  public void selection(){
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String userName = "sa";
      String password = "Qwerty1234";
      String url = "jdbc:sqlserver://sql-server-db:1433;database=master";
      Connection con = DriverManager.getConnection(url, userName, password);
      Statement s1 = con.createStatement();
      ResultSet rs = s1.executeQuery("select *from FISCALIA;");
      if (rs != null) {
        while (rs.next()) {
          System.out.println("ID: "+rs.getInt("id"));
          System.out.println("Nombre: "+rs.getString("nombre"));
          System.out.println("Descripcion:" + rs.getString("descripcion"));
          System.out.println("Fecha Creacion:" + rs.getDate("fecha_creacion") + " " + rs.getTime("fecha_creacion"));
        }
      }
      // String result = new result[20];
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}