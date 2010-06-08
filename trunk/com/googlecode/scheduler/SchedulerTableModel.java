/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.scheduler;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ssoldatos
 */
class SchedulerTableModel extends DefaultTableModel {
  private static final long serialVersionUID = 346364326L;

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }


}
