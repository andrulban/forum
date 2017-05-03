/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataModels;

import beans.PageOfDataGrid;
import beans.PageOfDataGridDescriptions;
import db.DBConnector;
import db_entitiesExt.DescribedObjExt;
import db_entitiesExt.DescriptionExt;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author andrusha
 */
public class DescriptionListDataModel extends LazyDataModel<DescriptionExt>{
    PageOfDataGridDescriptions pageOfDataGridDescriptions;
    DBConnector dBConnector;

    public DescriptionListDataModel(PageOfDataGridDescriptions pageOfDataGridDescriptions, DBConnector dBConnector) {
        this.pageOfDataGridDescriptions = pageOfDataGridDescriptions;
        this.dBConnector = dBConnector;
    }
    
    @Override
    public Object getRowKey(DescriptionExt descriptionExt) {
        return descriptionExt.getId();
    }

    @Override
    public DescriptionExt getRowData(String rowKey) {
        int key = Integer.valueOf(rowKey);
        for (DescriptionExt descriptionExt: pageOfDataGridDescriptions.getList()) {
            if(descriptionExt.getId()==key) {
                return descriptionExt;
            }
        }
        return null;
    }

    @Override
    public List<DescriptionExt> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        pageOfDataGridDescriptions.setFrom(first);
        pageOfDataGridDescriptions.setTo(pageSize);
     
        dBConnector.populatePageOfDataGridDescription();
        String longString = String.valueOf(pageOfDataGridDescriptions.getTotalDescriptionCount());
        int smth = Integer.valueOf(longString);
        this.setRowCount(smth);  
        
        return pageOfDataGridDescriptions.getList();        
    }
}
