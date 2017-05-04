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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andrusha
 */
@ManagedBean
@SessionScoped

public class DescribedObjectListController implements Serializable {

    private String searchString = "";
    private DBConnector dBConnector;
    private DescribedObjListDataModel describedObjListDataModel;
    private DataGridDescribedObj dataGridDescribedObj;
    private PageOfDataGrid pageOfDataGrid;
    private long selectedIndex;
    private DescribedObjExt addingDescribedObjExt;

    public DescribedObjectListController() {
        pageOfDataGrid = new PageOfDataGrid();
        dBConnector = new DBConnector(pageOfDataGrid);
        describedObjListDataModel = new DescribedObjListDataModel(pageOfDataGrid, dBConnector);
        addingDescribedObjExt = new DescribedObjExt();
        //search();
    }

    public String search() {
        dBConnector.searchDescObjByName(searchString);
        return "forum";
    }

    public String chooseObj(long id) {
        for (int i = 0; i < pageOfDataGrid.getList().size(); i++) {
            DescribedObjExt obj = pageOfDataGrid.getList().get(i);
            if (obj.getId() == id) {
                pageOfDataGrid.setSelectedDescribedObj(obj);
                pageOfDataGrid.setIndexOfSelectedObj(i);
                if (selectedIndex != 0) {
                    dBConnector.searchDescriptionById(id);
                }
                selectedIndex = id;
                System.out.println("Selected idex " + i);
                return "opinion";
            }
        }
        return "fail";
    }

    public void addObj() {
        RequestContext.getCurrentInstance().execute("PF('describedObjAddingDialog').show();");
    }

    public void closeObjAdding() {
        RequestContext.getCurrentInstance().execute("PF('describedObjAddingDialog').hide();");
    }

    public void saveObjAdding() {
        dBConnector.addDescrObj(addingDescribedObjExt);
        RequestContext.getCurrentInstance().execute("PF('describedObjAddingDialog').hide();");
        addingDescribedObjExt = new DescribedObjExt();
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

    public long getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(long selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public DescribedObjExt getAddingDescribedObjExt() {
        return addingDescribedObjExt;
    }

    public void setAddingDescribedObjExt(DescribedObjExt addingDescribedObjExt) {
        this.addingDescribedObjExt = addingDescribedObjExt;
    }

}
