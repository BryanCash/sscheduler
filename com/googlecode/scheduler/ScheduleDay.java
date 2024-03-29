/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssoldatos
 */
public class ScheduleDay {

  protected Date date;
  protected Calendar cal;
  protected ArrayList<EventRecord> events = new ArrayList<EventRecord>();
  private String database;

  public ScheduleDay() {
    this(Calendar.getInstance().getTime(), Scheduler.DEFAULT_DATABASE);
  }

  public ScheduleDay(Date date, String database) {
    this.date = date;
    this.database = database;
    cal = Calendar.getInstance();
    cal.setTime(date);
    events = getDateEvents();
  }

  public int getDay() {
    return getCal().get(Calendar.DATE);
  }

  public int getMonth() {
    return getCal().get(Calendar.MONTH)+1;
  }

  public int getYear() {
    return getCal().get(Calendar.YEAR);
  }

  @Override
  public String toString() {
    return String.valueOf(getCal().get(Calendar.DATE));
  }

  /**
   * @return the cal
   */
  public Calendar getCal() {
    return cal;
  }

  /**
   * @return the events
   */
  public ArrayList<EventRecord> getEvents() {
    return events;
  }

  /**
   *
   * @return
   */
  protected ArrayList<EventRecord> getDateEvents() {
    if(!database.equals(Scheduler.DEFAULT_DATABASE)){
      return events;
    }
    events.clear();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
    String d = sdf.format(date);
    String sql = "SELECT * FROM events WHERE date = '" + d + "'";
    Database db = null;
    try {
      db = new Database(getDatabase());
      ResultSet rs = db.getStmt().executeQuery(sql);
      while (rs.next()) {
        EventRecord ev = new EventRecord(getDatabase());
        ev.setId(rs.getInt("id"));
        ev.setTitle(rs.getString("title"));
        ev.setInfo(rs.getString("info"));
        ev.setDate(d);
        events.add(ev);
      }
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ScheduleDay.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(EventRecord.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        db.getConn().close();
      } catch (SQLException ex) {
        Logger.getLogger(ScheduleDay.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return events;
  }

  protected String getEventsList() {
    if (events.size() == 0) {
      return null;
    } else {
      String tip = "<html><table width='100px'>";
      for (Iterator<EventRecord> it = events.iterator(); it.hasNext();) {
        EventRecord event = it.next();
        tip += "<tr><th>" + event.getTitle() + "</th></tr>";
        tip += "<tr><th><hr></th></tr>";
        tip += "<tr><td>" + event.getInfo() + "</td></tr>";
        
      }
      tip += "</table></html>";
      return tip;
    }
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the database
   */
  public String getDatabase() {
    return database;
  }
}


