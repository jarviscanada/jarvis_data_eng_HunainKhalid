package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

  private final String url;
  private final Properties properties;

  public DatabaseConnectionManager(String host, String databaseName, String userName,
      String passWord) {
    this.url = "jdbc:postgresql://" + host + "/" + databaseName;
    this.properties = new Properties();
    this.properties.setProperty("user", userName);
    this.properties.setProperty("password", passWord);
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.properties);
  }
}
