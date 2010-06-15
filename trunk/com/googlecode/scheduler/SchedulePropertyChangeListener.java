/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class SchedulePropertyChangeListener implements PropertyChangeListener {
  public static final String RENDERER = "renderer";
  public static final String MONTH = "month";
  public static final String YEAR = "year";
  public static final String DATE = "date";
  public static final String EVENT_ADDED = "eventAdded";
  public static final String EVENT_REMOVED = "eventRemoved";
  public static final String REAL_MONTH = "realMonth";
  public static final String REAL_YEAR = "realYear";
  public static final String REAL_DAY = "realDay";
  public static final String PAST_YEARS = "pastYears";
  public static final String FUTURE_YEARS = "futureYears";

  public void propertyChange(PropertyChangeEvent evt) {
    String p = evt.getPropertyName();
    Scheduler scheduler = (Scheduler) evt.getSource();
    if (p.equals(RENDERER)) {
      scheduler.getTblCalendar().setDefaultRenderer(
          scheduler.getTblCalendar().getColumnClass(0), (TableCellRenderer) evt.getNewValue()); //Apply renderer
      scheduler.refreshCalendar(scheduler.getCurrentMonth(), scheduler.getCurrentYear());
    } else if (p.equals(MONTH) || p.equals(YEAR)|| p.equals(DATE)
        || p.equals(EVENT_ADDED)|| p.equals(EVENT_REMOVED) 
        || p.equals(PAST_YEARS)|| p.equals(FUTURE_YEARS)){
      scheduler.refreshCalendar(scheduler.getCurrentMonth(), scheduler.getCurrentYear());
    }

  }
}


