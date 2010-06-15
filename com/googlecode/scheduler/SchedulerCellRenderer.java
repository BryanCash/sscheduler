/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.scheduler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
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
  private ImageIcon image = new ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/star.png"));

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
      ScheduleDay day = (ScheduleDay) value;
      setText("  "+String.valueOf(day.getDay()));
      /// CURRENT DAY
      if (day.getDay() == sc.getRealDay()
          && sc.getCurrentMonth() == sc.getRealMonth()
          && sc.getCurrentYear() == sc.getRealYear()) { //Today
        setBackground(Color.lightGray);
        setForeground(Color.blue);
        setFont(getFont().deriveFont(Font.BOLD));
      } else {
        setBackground(Color.white);
      }
      
      //SELECTED DAY
      if (isSelected) {
        setBorder(BorderFactory.createLineBorder(Color.RED,2));
        setBackground(sc.getBackground().brighter());
      } else {
        setBorder(BorderFactory.createEmptyBorder());
         setBackground(getBackground());
      }
      if (getEventsList(day) != null) {
        ImageIcon ic = getImage();
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
      setBackground(Color.WHITE);
      setBorder(BorderFactory.createEmptyBorder());
    }

    return this;
  }

  public String getEventsList(ScheduleDay day) {
    return day.getEventsList();
  }

  /**
   * @return the image
   */
  public ImageIcon getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(ImageIcon image) {
    this.image = image;
  }

  public ImageIcon getDefaultImage() {
    return new ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/star.png"));
  }
}


