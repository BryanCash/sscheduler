/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.scheduler.demo;

import com.googlecode.scheduler.Database;
import com.googlecode.scheduler.ScheduleDay;
import com.googlecode.scheduler.SchedulerCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    String tip = "<html><table>";
    boolean results = false;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(sDay.getDate());
    String sql = "SELECT episodes.title AS title , series.title AS series FROM " +
        "episodes JOIN series on episodes.series_ID = series.series_ID WHERE " +
        "aired = '"+ date  + "'";
    try {
      Database db = new Database(sDay.getDatabase());
      ResultSet rs = db.getStmt().executeQuery(sql);
      while(rs.next()){
        results = true;
        tip += "<tr><th>"+rs.getString("series")+"</th><td>"+rs.getString("title")+"</td></tr>";
      }
      tip +="</table></html>";
      return results ? tip : null;
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(CustomTableCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(CustomTableCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }



}
