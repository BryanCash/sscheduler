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
class ScheduleDay {

  protected Date date;
  protected Calendar cal;
  protected ArrayList<EventRecord> events = new ArrayList<EventRecord>();

  public ScheduleDay() {
    this(Calendar.getInstance().getTime());
  }

  public ScheduleDay(Date date) {
    this.date = date;
    cal = Calendar.getInstance();
    cal.setTime(date);
    events = getDateEvents(date);
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

  protected static ArrayList<EventRecord> getDateEvents(Date date) {
    ArrayList<EventRecord> events = new ArrayList<EventRecord>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
    String d = sdf.format(date);
    String sql = "SELECT * FROM events WHERE date = '" + d + "'";
    Database db = null;
    try {
      db = new Database();
      ResultSet rs = db.getStmt().executeQuery(sql);
      while (rs.next()) {
        EventRecord ev = new EventRecord();
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
      String tip = "<html><table>";
      for (Iterator<EventRecord> it = events.iterator(); it.hasNext();) {
        EventRecord event = it.next();
        tip += "<tr><th>" + event.getTitle() + "</th>";
        tip += "<td>" + event.getInfo() + "</td></tr>";
        tip += "<tr><td colspan='2'><hr></td></tr>";
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
}


