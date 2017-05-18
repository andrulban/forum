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
import db_entitiesExt.DescribedObjExt;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
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
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserController uc = (UserController) session.getAttribute("userController");
        dBConnector = uc.getdBConnector();
        dBConnector.setPageOfDataGrid(pageOfDataGrid);
        describedObjListDataModel = new DescribedObjListDataModel(pageOfDataGrid, dBConnector);
        addingDescribedObjExt = new DescribedObjExt();
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
        dBConnector.addDescrObj(addingDescribedObjExt);
        RequestContext.getCurrentInstance().execute("PF('describedObjAddingDialog').hide();");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Object has been added!"));
        addingDescribedObjExt = new DescribedObjExt();
        dBConnector.populatePageOfDataGrid();
    }

    public String deleteObj() {
        dBConnector.deleteDescrObj(pageOfDataGrid.getSelectedDescribedObj());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(pageOfDataGrid.getSelectedDescribedObj().getName()+" has been deleted!"));
        pageOfDataGrid.setSelectedDescribedObj(new DescribedObjExt());
        dBConnector.populatePageOfDataGrid();
        return "forum";
    }
    
    public void editObj() {
        dBConnector.editDescrObj(pageOfDataGrid.getSelectedDescribedObj());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(pageOfDataGrid.getSelectedDescribedObj().getName()+" has been edited!"));
    }   
    
    public void vote() {
        dBConnector.vote();
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
