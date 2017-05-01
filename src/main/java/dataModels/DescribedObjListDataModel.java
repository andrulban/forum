/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModels;

import beans.PageOfDataGrid;
import db.DBConnector;
import db_entitiesExt.DescribedObjExt;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author andrusha
 */
public class DescribedObjListDataModel extends LazyDataModel<DescribedObjExt>{
    PageOfDataGrid pageOfDataGrid;
    DBConnector dBConnector;

    public DescribedObjListDataModel(PageOfDataGrid pageOfDataGrid, DBConnector dBConnector) {
        this.pageOfDataGrid = pageOfDataGrid;
        this.dBConnector = dBConnector;
    }
    
    @Override
    public Object getRowKey(DescribedObjExt describedObjExt) {
        return describedObjExt.getId();
    }

    @Override
    public DescribedObjExt getRowData(String rowKey) {
        int key = Integer.valueOf(rowKey);
        for (DescribedObjExt describedObjExt: pageOfDataGrid.getList()) {
            if(describedObjExt.getId()==key) {
                return describedObjExt;
            }
        }
        return null;
    }

    @Override
    public List<DescribedObjExt> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        pageOfDataGrid.setFrom(first);
        pageOfDataGrid.setTo(pageSize);
     
        dBConnector.populatePageOfDataGrid();
        String longString = String.valueOf(pageOfDataGrid.getTotalDescribedObjCount());
        int smth = Integer.valueOf(longString);
        this.setRowCount(smth);  
        
        return pageOfDataGrid.getList();        
    }
    
    
    
    
    
    
}
