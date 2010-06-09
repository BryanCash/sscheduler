/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class EventRecord {

  private int id;
  private String date;
  private String title;
  private String info;
  private Statement stmt;

  /**
   * 
   * @param database
   */
  public EventRecord(String database) {
    try {
      Database db = new Database(database);
      stmt = db.getStmt();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(EventRecord.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(EventRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public boolean save() {
    String sql = "";
    if (getId() == 0) {
      sql = "INSERT INTO events (date, title, info) VALUES "
          + "('" + getDate() + "','" + getTitle() + "','" + getInfo() + "')";
    } else {
      sql = "UPDATE event SET date = '" + getDate() + "', title ='" + getTitle() + "', "
          + "info ='" + getInfo() + "'";
    }
    try {
      stmt.executeUpdate(sql);
      return true;
    } catch (SQLException ex) {
      Logger.getLogger(EventRecord.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }

  }


  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the info
   */
  public String getInfo() {
    return info;
  }

  /**
   * @param info the info to set
   */
  public void setInfo(String info) {
    this.info = info;
  }
}
