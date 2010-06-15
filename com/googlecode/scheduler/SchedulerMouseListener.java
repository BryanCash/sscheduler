/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ssoldatos
 */
public class SchedulerMouseListener extends MouseAdapter {

  private final Scheduler scheduler;

  SchedulerMouseListener(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    Point point = e.getPoint();
    int row = scheduler.getTblCalendar().rowAtPoint(point);
    int col = scheduler.getTblCalendar().columnAtPoint(point);
    if (e.getButton() == MouseEvent.BUTTON3) {
      int val = Integer.parseInt(String.valueOf(scheduler.getTblCalendar().getValueAt(row, col)));
      scheduler.showPopup(e, val);
      super.mouseClicked(e);
    }

  }
}
