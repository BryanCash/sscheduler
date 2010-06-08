/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ssoldatos
 */
public class SchedulerCellRenderer extends DefaultTableCellRenderer {

  private static final long serialVersionUID = 45647576L;

  public SchedulerCellRenderer() {
    setVerticalAlignment(SwingConstants.TOP);
    setHorizontalTextPosition(SwingConstants.LEFT);
    setIconTextGap(10);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    Scheduler sc = (Scheduler) table.getParent().getParent().getParent();
    if (column == 5 || column == 6) { //Week-end
      setForeground(Color.RED);
      setFont(getFont().deriveFont(Font.BOLD));
    } else {
      setForeground(Color.black);
    }
    Image im;
    if (value != null && value instanceof ScheduleDay) {
      MyScheduleDay day = new MyScheduleDay((ScheduleDay) value);
      if (day.getDay() == sc.getRealDay()
          && sc.getCurrentMonth() == sc.getRealMonth()
          && sc.getCurrentYear() == sc.getRealYear()) { //Today
        setBackground(Color.lightGray);
        setForeground(Color.blue);
        setFont(getFont().deriveFont(Font.BOLD));
      } else {
        setBackground(Color.white);

      }
      if (getEventsList(day) != null) {
        ImageIcon ic = new ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/star.png"));
        setIcon(ic);
        setToolTipText(getEventsList(day));
        revalidate();
        repaint();
      } else {
        setIcon(null);
        setToolTipText("");
        revalidate();
        repaint();
      }
    } else {
      setIcon(null);
      setToolTipText("");
    }

    return this;
  }

  protected String getEventsList(MyScheduleDay day){
    return day.getEventsList();
  }


  public class MyScheduleDay extends ScheduleDay {

    public MyScheduleDay(ScheduleDay value) {
      date = value.getDate();
      this.cal = value.getCal();
      this.events = value.getEvents();
    }

   
  }
}


