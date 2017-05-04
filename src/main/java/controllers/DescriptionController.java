/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.DataGridDescribedObj;
import beans.DataGridDescription;
import beans.PageOfDataGridDescriptions;
import dataModels.DescriptionListDataModel;
import db.DBConnector;
import db_entitiesExt.DescriptionExt;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrusha
 */
@ManagedBean
@SessionScoped

public class DescriptionController implements Serializable{

    private DBConnector dBConnector;
    private DescriptionListDataModel descriptionListDataModel;
    private DataGridDescription dataGridDescription;
    private PageOfDataGridDescriptions pageOfDataGridDescriptions;
    private String currentText;
    private DescribedObjectListController describedObjectListController;

    public DescriptionController() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        describedObjectListController = (DescribedObjectListController) session.getAttribute("describedObjectListController");
        pageOfDataGridDescriptions = new PageOfDataGridDescriptions();
        dBConnector = describedObjectListController.getdBConnector();
        dBConnector.setPageOfDataGridDescriptions(pageOfDataGridDescriptions);
        descriptionListDataModel = new DescriptionListDataModel(pageOfDataGridDescriptions, dBConnector);
        dBConnector.searchDescriptionById(describedObjectListController.getSelectedIndex());
    }
    
    
    public void addCommend() {
        if(currentText==null&currentText.equals("")) {
            return;
        }
        DescriptionExt descriptionExt = new DescriptionExt();
        descriptionExt.setDescription(currentText);
        descriptionExt.setDateOfDescription(new Date());
        descriptionExt.setIdObject(describedObjectListController.getPageOfDataGrid().getSelectedDescribedObj().getId());
        descriptionExt.setIdUser(1);
        dBConnector.addCommend(descriptionExt);
        
        currentText=null;
    }
    
    public void deleteCommend(DescriptionExt descriptionExt) {
        dBConnector.deleteCommend(descriptionExt);
        dBConnector.populatePageOfDataGridDescription();
    }
    
    
    
    
    
    

    public DBConnector getdBConnector() {
        return dBConnector;
    }

    public void setdBConnector(DBConnector dBConnector) {
        this.dBConnector = dBConnector;
    }

    public DescriptionListDataModel getDescriptionListDataModel() {
        return descriptionListDataModel;
    }

    public void setDescriptionListDataModel(DescriptionListDataModel descriptionListDataModel) {
        this.descriptionListDataModel = descriptionListDataModel;
    }

    public DataGridDescription getDataGridDescription() {
        return dataGridDescription = new DataGridDescription();
    }

    public void setDataGridDescription(DataGridDescription dataGridDescription) {
        this.dataGridDescription = dataGridDescription;
    }

    public PageOfDataGridDescriptions getPageOfDataGridDescriptions() {
        return pageOfDataGridDescriptions;
    }

    public void setPageOfDataGridDescriptions(PageOfDataGridDescriptions pageOfDataGridDescriptions) {
        this.pageOfDataGridDescriptions = pageOfDataGridDescriptions;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

}
