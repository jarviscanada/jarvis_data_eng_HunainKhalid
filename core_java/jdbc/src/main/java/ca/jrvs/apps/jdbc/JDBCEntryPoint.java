package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCEntryPoint {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "postgres", "password");

    try {
      Connection con = dcm.getConnection();
      CustomerDAO cDAO = new CustomerDAO(con);
      Customer cust = new Customer();

      cust.setFirstName("John");
      cust.setLastName("Adams");
      cust.setEmail("jadams.wh.gov");
      cust.setAddress("1234 Main st");
      cust.setCity("Arlington");
      cust.setState("VA");
      cust.setPhone("(555) 555-9845");
      cust.setZipCode("012345");

      Customer dbCust = cDAO.create(cust);
      System.out.println(dbCust);
      dbCust = cDAO.findById(dbCust.getId());
      System.out.println(dbCust);
      dbCust.setEmail("johnwick@gmail.com");
      dbCust = cDAO.update(dbCust);
      System.out.println(dbCust);
      cDAO.delete(dbCust.getId());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
