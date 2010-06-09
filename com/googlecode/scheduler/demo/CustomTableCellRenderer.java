/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.scheduler.demo;

import com.googlecode.scheduler.ScheduleDay;
import com.googlecode.scheduler.SchedulerCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class CustomTableCellRenderer extends SchedulerCellRenderer {

  
  @Override
  public String getEventsList(ScheduleDay sDay) {
    int  day = sDay.getDay();
    int month = sDay.getMonth();
    int year = sDay.getYear();
    if(day==8 && month == 6 && year == 2010){
      return "ddd";
    }
    return null;
  }



}
