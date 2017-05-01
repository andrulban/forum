/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.DataGridDescribedObj;
import beans.PageOfDataGrid;
import dataModels.DescribedObjListDataModel;
import db.DBConnector;
import db_entities.DescribedObj;
import db_entitiesExt.DescribedObjExt;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author andrusha
 */
@ManagedBean(eager = true)
@SessionScoped

public class DescribedObjectListController implements Serializable {

    private String searchString = "";
    private DBConnector dBConnector;
    //private List<DescribedObjExt> listOfDescribedObjs;
    private DescribedObjListDataModel describedObjListDataModel;
    private DataGridDescribedObj dataGridDescribedObj;
    private PageOfDataGrid pageOfDataGrid;

    
    public DescribedObjectListController() {
        pageOfDataGrid = new PageOfDataGrid();
        dBConnector = new DBConnector(pageOfDataGrid);
        describedObjListDataModel = new DescribedObjListDataModel(pageOfDataGrid, dBConnector);
        //search();
    }

    public void search() {
        dBConnector.searchByName(searchString);
       // listOfDescribedObjs = dBConnector.getLAllDescribedObjs();
       // System.out.println(listOfDescribedObjs.get(0).getName());
    }
    
    
    
    
    
    

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }    

    public DBConnector getdBConnector() {
        return dBConnector;
    }

    public void setdBConnector(DBConnector dBConnector) {
        this.dBConnector = dBConnector;
    }

    public DescribedObjListDataModel getDescribedObjListDataModel() {
        return describedObjListDataModel;
    }

    public void setDescribedObjListDataModel(DescribedObjListDataModel describedObjListDataModel) {
        this.describedObjListDataModel = describedObjListDataModel;
    }

    public DataGridDescribedObj getDataGridDescribedObj() {
        return dataGridDescribedObj = new DataGridDescribedObj();
    }

    public void setDataGridDescribedObj(DataGridDescribedObj dataGridDescribedObj) {
        this.dataGridDescribedObj = dataGridDescribedObj;
    }

    public PageOfDataGrid getPageOfDataGrid() {
        return pageOfDataGrid;
    }

    public void setPageOfDataGrid(PageOfDataGrid pageOfDataGrid) {
        this.pageOfDataGrid = pageOfDataGrid;
    }
    
    
    
    

}
