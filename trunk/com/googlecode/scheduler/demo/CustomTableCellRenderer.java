/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.scheduler.demo;

import com.googlecode.scheduler.SchedulerCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class CustomTableCellRenderer extends SchedulerCellRenderer {

  
  @Override
  protected String getEventsList(MyScheduleDay sDay) {
    int  day = sDay.getDay();
    int month = sDay.getMonth();
    int year = sDay.getYear();
    if(day==15 && month==6 & year==2010){
      return "aaa";
    }
    return super.getEventsList(sDay);
  }



}
