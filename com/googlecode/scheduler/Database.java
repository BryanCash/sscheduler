/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.CORBA.Util;

/**
 *
 * @author ssoldatos
 */
public class Database {

  private Connection conn;
  private Statement stmt;

  /**
   * @return the conn
   */
  public Connection getConn() {
    return conn;
  }

  public Database() throws ClassNotFoundException, SQLException {
    File db = new File("./schedule.db");
    if (db.isFile()) {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:./schedule.db");
      stmt = conn.createStatement();
    } else {
      createDb();
    }

  }

  private void createDb() {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:./schedule.db");
      stmt = getConn().createStatement();
      String sql = "CREATE  TABLE  IF NOT EXISTS `main`.`events` (`id` "
          + "INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "
          + "`date` DATE, "
          + "`title` VARCHAR, "
          + "`info` VARCHAR)";
      getStmt().execute(sql);
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * @return the stmt
   */
  public Statement getStmt() {
    return stmt;
  }

  public static String escape(String text) {
    return text.replaceAll("'", "''");
  }
}
