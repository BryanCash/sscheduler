/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Scheduler.java
 *
 * Created on 7 Ιουν 2010, 11:58:19 πμ
 */
package com.googlecode.scheduler;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.stream.util.EventReaderDelegate;

/**
 *
 * @author ssoldatos
 */
public class Scheduler extends javax.swing.JPanel {

  private static final long serialVersionUID = 6456432636L;
  private int realDay;
  private int realMonth;
  private int realYear;
  private int currentMonth;
  private int currentYear;
  private int rowHeight = 42;
  private int pastYears = 2;
  private int futureYears = 2;
  private DefaultTableModel mtblCalendar = new SchedulerTableModel();
  String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  private Database db;
  public static final String DEFAULT_DATABASE = "./schedule.db";
  private String database;
  private int eventDay;
  SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
  private Object ScheduleDayClass;
  private TableCellRenderer defaultRenderer = new SchedulerCellRenderer();
  private Color textColor;

  /** Creates new form Scheduler */
  public Scheduler() {
    this(DEFAULT_DATABASE);
  }

  public Scheduler(String db) {
    initComponents();
    database = db;
    prepareTable();
    getRealDates();
    populateCombo();
    refreshCalendar(realMonth, realYear); //Refresh calendar
    stblCalendar.getViewport().setOpaque(false);
    tblCalendar.addMouseListener(new SchedulerMouseListener(this));
    addPropertyChangeListener(new SchedulePropertyChangeListener());
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    popup = new javax.swing.JPopupMenu();
    addEvent = new javax.swing.JMenuItem();
    removeEvent = new javax.swing.JMenuItem();
    btnPrev = new javax.swing.JButton();
    lblMonth = new javax.swing.JLabel();
    btnNext = new javax.swing.JButton();
    lblYear = new javax.swing.JLabel();
    cmbYear = new javax.swing.JComboBox();
    stblCalendar = new javax.swing.JScrollPane();
    tblCalendar = new javax.swing.JTable();
    btnNow = new javax.swing.JButton();

    popup.setInvoker(tblCalendar);

    addEvent.setText("Add Event");
    addEvent.setToolTipText("Add a new event");
    addEvent.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addEventActionPerformed(evt);
      }
    });
    popup.add(addEvent);

    removeEvent.setText("Remove Event");
    removeEvent.setToolTipText("Remove Event");
    removeEvent.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        removeEventActionPerformed(evt);
      }
    });
    popup.add(removeEvent);

    popup.getAccessibleContext().setAccessibleParent(tblCalendar);

    setOpaque(false);
    addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentResized(java.awt.event.ComponentEvent evt) {
        formComponentResized(evt);
      }
    });

    btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/previous.png"))); // NOI18N
    btnPrev.setToolTipText("Previous Month");
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnPrevActionPerformed(evt);
      }
    });

    lblMonth.setFont(lblMonth.getFont().deriveFont(lblMonth.getFont().getStyle() | java.awt.Font.BOLD));
    lblMonth.setForeground(textColor);
    lblMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblMonth.setText("January");

    btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/next.png"))); // NOI18N
    btnNext.setToolTipText("Next Month");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextActionPerformed(evt);
      }
    });

    lblYear.setFont(lblYear.getFont().deriveFont(lblYear.getFont().getStyle() | java.awt.Font.BOLD));
    lblYear.setForeground(textColor);
    lblYear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblYear.setText("Year : ");

    cmbYear.setToolTipText("Select Year");
    cmbYear.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmbYearActionPerformed(evt);
      }
    });

    stblCalendar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    stblCalendar.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    stblCalendar.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    stblCalendar.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    stblCalendar.setOpaque(false);

    tblCalendar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    tblCalendar.setModel(mtblCalendar);
    tblCalendar.setOpaque(false);
    stblCalendar.setViewportView(tblCalendar);

    btnNow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/googlecode/scheduler/images/today.png"))); // NOI18N
    btnNow.setToolTipText("Today");
    btnNow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNowActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(stblCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnNow, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(69, 69, 69)
            .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnNext, btnNow, btnPrev});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(12, 12, 12)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addComponent(btnPrev)
          .addComponent(lblMonth)
          .addComponent(btnNext)
          .addComponent(btnNow, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblYear)
          .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(stblCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnNext, btnNow, btnPrev});

  }// </editor-fold>//GEN-END:initComponents

  private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
    if (currentMonth == 0) { //Back one year
      setCurrentMonth(11);
      setCurrentYear(getCurrentYear()-1);
    } else { //Back one month
      setCurrentMonth(getCurrentMonth() -1);
    }
  }//GEN-LAST:event_btnPrevActionPerformed

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
    if (currentMonth == 11) { //Foward one year
      setCurrentMonth(0);
      setCurrentYear(getCurrentYear() + 1);
    } else { //Foward one month
      setCurrentMonth(getCurrentMonth() + 1);
    }
  }//GEN-LAST:event_btnNextActionPerformed

  private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
    if (cmbYear.getSelectedItem() != null) {
      String b = cmbYear.getSelectedItem().toString();
      setCurrentYear(Integer.parseInt(b));
    }
  }//GEN-LAST:event_cmbYearActionPerformed

  private void btnNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNowActionPerformed
    goToToday();

  }//GEN-LAST:event_btnNowActionPerformed

  public void goToToday(){
    try {
      Date oldValue = sdf.parse(currentYear + "/" + (currentMonth + 1) + "/" + eventDay);
      Date newValue = sdf.parse(realYear + "/" + (realMonth + 1) + "/" + realDay);
      currentMonth = realMonth;
      currentYear = realYear;
      firePropertyChange("date", oldValue, newValue);
    } catch (ParseException ex) {
    }
  }


  private void addEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventActionPerformed
    Frame frame = (Frame) SwingUtilities.getRoot(this);
    EventForm event = new EventForm(frame, true, database, eventDay, (currentMonth + 1), currentYear);
    if (event.getEvent() != null) {
      firePropertyChange("eventAdded", null, event.getEvent());
    }
  }//GEN-LAST:event_addEventActionPerformed

  private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    int height = getHeight();
    tblCalendar.setRowHeight((height - 50) / 7);
  }//GEN-LAST:event_formComponentResized

  private void removeEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeEventActionPerformed
    Date date = null;
    EventRecord event = null;
    try {
      date = sdf.parse(currentYear + "/" + (currentMonth + 1) + "/" + eventDay);
    } catch (ParseException ex) {
      Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
    }
    ScheduleDay day = new ScheduleDay(date, database);
    ArrayList<EventRecord> events = day.events;
    if (events.size() == 0) {
    } else if (events.size() == 1) {
      int an = JOptionPane.showConfirmDialog(null, "Do you really want to remove the event?", "Remove event?", JOptionPane.YES_NO_OPTION);
      if (an == JOptionPane.YES_OPTION) {
        event = new EventRecord(database, events.get(0).getId());
        event.delete();
      }
    } else {
      event = (EventRecord) JOptionPane.showInputDialog(null, "Select the event to delete", "Delete event", JOptionPane.YES_NO_OPTION, null, events.toArray(), -1);
      if (event != null) {
        event.delete();
      }
    }
    if (event != null) {
      firePropertyChange("eventRemoved", event, null);
    }
  }//GEN-LAST:event_removeEventActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem addEvent;
  private javax.swing.JButton btnNext;
  private javax.swing.JButton btnNow;
  private javax.swing.JButton btnPrev;
  private javax.swing.JComboBox cmbYear;
  private javax.swing.JLabel lblMonth;
  private javax.swing.JLabel lblYear;
  private javax.swing.JPopupMenu popup;
  private javax.swing.JMenuItem removeEvent;
  private javax.swing.JScrollPane stblCalendar;
  private javax.swing.JTable tblCalendar;
  // End of variables declaration//GEN-END:variables

  private void prepareTable() {
    getTblCalendar().getParent().setBackground(getTblCalendar().getBackground()); //Set background
    //No resize/reorder
    getTblCalendar().getTableHeader().setResizingAllowed(false);
    getTblCalendar().getTableHeader().setReorderingAllowed(false);
    //Single cell selection
    getTblCalendar().setColumnSelectionAllowed(true);
    getTblCalendar().setRowSelectionAllowed(true);
    getTblCalendar().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //Add headers
    String[] headers = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"}; //All headers
    for (int i = 0; i < 7; i++) {
      mtblCalendar.addColumn(headers[i]);
    }
    //Set row/column count
    getTblCalendar().setRowHeight(rowHeight);
    mtblCalendar.setColumnCount(7);
    mtblCalendar.setRowCount(6);
    getTblCalendar().setDefaultRenderer(getTblCalendar().getColumnClass(0), getDefaultRenderer()); //Apply renderer
  }

  private void getRealDates() {
    //Get real month/year
    GregorianCalendar cal = new GregorianCalendar(); //Create calendar
    setRealDay(cal.get(GregorianCalendar.DAY_OF_MONTH)); //Get day
    setRealMonth(cal.get(GregorianCalendar.MONTH)); //Get month
    setRealYear(cal.get(GregorianCalendar.YEAR)); //Get year
    setCurrentMonth(getRealMonth()); //Match month and year
    setCurrentYear(getRealYear());
  }

  private void populateCombo() {
    //Populate combo box
    cmbYear.removeAllItems();
    for (int i = getRealYear() - getPastYears(); i <= getRealYear() + getFutureYears(); i++) {
      cmbYear.addItem(String.valueOf(i));
    }
  }

  public void refreshCalendar(int month, int year) {
    int nod, som; //Number Of Days, Start Of Month

    btnPrev.setEnabled(true); //Enable buttons at first
    btnNext.setEnabled(true);
    if (month == 0 && year <= getRealYear() - getPastYears()) {
      btnPrev.setEnabled(false);
    } //Too early
    if (month == 11 && year >= getRealYear() + getFutureYears()) {
      btnNext.setEnabled(false);
    } //Too late
    cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
    lblMonth.setText(months[month]); //Refresh the month label (at the top)
    //Get first day of month and number of days
    GregorianCalendar cal = new GregorianCalendar(year, month, 1);
    nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    som = cal.get(GregorianCalendar.DAY_OF_WEEK);
    //Clear table
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        mtblCalendar.setValueAt(null, i, j);
      }
    }
    if (som == GregorianCalendar.SUNDAY) {
      som = 7;
    } else {
      som--;
    } // if startOfMonth
    //Draw calendar
    for (int i = 1; i <= nod; i++) {
      int row = new Integer((i + som - 2) / 7);
      int column = (i + som - 2) % 7;
      Date date = null;
      try {
        date = sdf.parse(currentYear + "/" + (currentMonth + 1) + "/" + i);
      } catch (ParseException ex) {
      }
      ScheduleDay s = new ScheduleDay(date, getDatabase());
      mtblCalendar.setValueAt(s, row, column);
    }
  }

  /**
   * @return the realDay
   */
  public int getRealDay() {
    return realDay;
  }

  /**
   * @param realDay the realDay to set
   */
  private void setRealDay(int realDay) {
    int oldValue = getRealDay();
    this.realDay = realDay;
    firePropertyChange("realDay", oldValue, realDay);
  }

  /**
   * @return the realMonth
   */
  public int getRealMonth() {
    return realMonth;
  }

  /**
   * @param realMonth the realMonth to set
   */
  private void setRealMonth(int realMonth) {
    int oldValue = getRealMonth();
    this.realMonth = realMonth;
    firePropertyChange("realMonth", oldValue, realMonth);
  }

  /**
   * @return the realYear
   */
  public int getRealYear() {
    return realYear;
  }

  /**
   * @param realYear the realYear to set
   */
  private void setRealYear(int realYear) {
    int oldValue = getRealYear();
    this.realYear = realYear;
    firePropertyChange("realYear", oldValue, realYear);
  }

  /**
   * @return the currentMonth
   */
  public int getCurrentMonth() {
    return currentMonth;
  }

  /**
   * @param currentMonth the currentMonth to set
   */
  public void setCurrentMonth(int currentMonth) {
    int oldValue = getCurrentMonth();
    this.currentMonth = currentMonth;
    firePropertyChange("month", oldValue, currentMonth);
  }

  /**
   * @return the currentYear
   */
  public int getCurrentYear() {
    return currentYear;
  }

  /**
   * @param currentYear the currentYear to set
   */
  public void setCurrentYear(int currentYear) {
    int oldValue = getCurrentYear();
    this.currentYear = currentYear;
    firePropertyChange("year", oldValue, currentYear);
  }

  /**
   * @return the rowHeight
   */
  public int getRowHeight() {
    return rowHeight;
  }

  /**
   * @param rowHeight the rowHeight to set
   */
  public void setRowHeight(int rowHeight) {
    this.rowHeight = rowHeight;
  }

  /**
   * @return the pastYears
   */
  public int getPastYears() {
    return pastYears;
  }

  /**
   * @param pastYears the pastYears to set
   */
  public void setPastYears(int pastYears) {
    int oldValue = getPastYears();
    this.pastYears = pastYears;
    populateCombo();
    firePropertyChange("pastYears", oldValue, pastYears);

  }

  /**
   * @return the futureYears
   */
  public int getFutureYears() {
    return futureYears;
  }

  /**
   * @param futureYears the futureYears to set
   */
  public void setFutureYears(int futureYears) {
    int oldValue = getFutureYears();
    this.futureYears = futureYears;
    populateCombo();
    firePropertyChange("futureYears", oldValue, futureYears);
  }

  void showPopup(MouseEvent evt, int day) {
    if (getDatabase().equals(DEFAULT_DATABASE)) {
      eventDay = day;
      Date date = null;
      try {
        date = sdf.parse(currentYear + "/" + (currentMonth + 1) + "/" + eventDay);
      } catch (ParseException ex) {
        Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
      }
      ScheduleDay sday = new ScheduleDay(date, database);
      addEvent.setText("Add event to " + eventDay + "/" + (currentMonth + 1) + "/" + currentYear);
      if (sday.events.size() > 0) {
        removeEvent.setEnabled(true);
        removeEvent.setText("Remove event from " + eventDay + "/" + (currentMonth + 1) + "/" + currentYear);
      } else {
        removeEvent.setEnabled(false);
      }
      popup.show(evt.getComponent(), evt.getX(), evt.getY());
    }
  }

  /**
   * @return the tblCalendar
   */
  public JTable getTblCalendar() {
    return tblCalendar;
  }

  /**
   * @return the ScheduleDayClass
   */
  public Object getScheduleDay() {
    return ScheduleDayClass;
  }

  /**
   * @param ScheduleDayClass the ScheduleDayClass to set
   */
  public void setScheduleDay(Object ScheduleDay) {
    this.ScheduleDayClass = ScheduleDay;
  }

  /**
   * @return the database
   */
  public String getDatabase() {
    return database;
  }

  /**
   * @param database the database to set
   */
  public void setDatabase(String database) {
    String oldValue = getDatabase();
    this.database = database;
    firePropertyChange("database", oldValue, database);
  }

  /**
   * @return the renderer
   */
  public TableCellRenderer getDefaultRenderer() {
    return defaultRenderer;
  }

  /**
   * @param renderer the renderer to set
   */
  public void setDefaultRenderer(TableCellRenderer renderer) {
    TableCellRenderer oldValue = getDefaultRenderer();
    this.defaultRenderer = renderer;
    firePropertyChange("renderer", oldValue, renderer);
  }

  /**
   * @return the textColor
   */
  public Color getTextColor() {
    return textColor;
  }

  /**
   * @param textColor the textColor to set
   */
  public void setTextColor(Color textColor) {
    this.textColor = textColor;
    lblMonth.setForeground(textColor);
    lblYear.setForeground(textColor);
    revalidate();
    repaint();
  }
}

