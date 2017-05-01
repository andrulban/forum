/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.primefaces.component.datagrid.DataGrid;

/**
 *
 * @author andrusha
 */
public class DataGridDescribedObj {
    DataGrid dataGrid;
    
    public DataGridDescribedObj() {
        
    }

    public DataGrid getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(DataGrid dataGrid) {
        this.dataGrid = dataGrid;
    }
        
}
