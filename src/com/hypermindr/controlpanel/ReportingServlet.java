package com.hypermindr.controlpanel;

import com.google.common.collect.Lists;
import com.google.visualization.datasource.Capabilities;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableCell;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class ReportingServlet extends DataSourceServlet {


  
private static final long serialVersionUID = -3446280415695178678L;


private static Map<String, BigDecimal> dataMap = new HashMap<String, BigDecimal>();
 

  private static final String TIMECONSUMED = "Tempo consumido";

  private static final ColumnDescription[] TABLE_COLUMNS =
      new ColumnDescription[] {
        new ColumnDescription(TIMECONSUMED, ValueType.NUMBER, "Tempo consumido (milliseconds)")
  };


  @Override
  public Capabilities getCapabilities() {
    return Capabilities.SELECT;
  }

 
  @Override
  protected boolean isRestrictedAccessMode() {
    return false;
  }

  
  @Override
  public DataTable generateDataTable(Query query, HttpServletRequest req)
      throws TypeMismatchException {
 
    return generatePlot(query);
  }

  
  private DataTable generatePlot(Query query) throws TypeMismatchException {
    DataTable data = new DataTable();
    List<ColumnDescription> requiredColumns = getRequiredColumns(TABLE_COLUMNS);
    data.addColumns(requiredColumns);
    int i = 0; 

    if (dataMap.size() == 0) {
    //	dataMap = MongoDatasource.getInstance().executeQuery();
    }
    for (String key : dataMap.keySet()) {
    	
      TableRow row = new TableRow();
      for (ColumnDescription selectionColumn : requiredColumns) {
        String columnName = selectionColumn.getId();
        if (columnName.equals(TIMECONSUMED)) {
            row.addCell(new TableCell(dataMap.get(key).doubleValue()));
        }
      }
      data.addRow(row);
      i++;
      
    }
    return data;
  }

 
  /**
   * Returns a list of required columns based on the query and the actual
   * columns.
   *
   * @param query The user selection query.
   * @param availableColumns The list of possible columns.
   *
   * @return A List of required columns for the requested data table.
   */
  private List<ColumnDescription> getRequiredColumns(
      ColumnDescription[] availableColumns) {
    List<ColumnDescription> requiredColumns = Lists.newArrayList();
    for (ColumnDescription column : availableColumns) {
        requiredColumns.add(column);
    }
    return requiredColumns;
  }
}