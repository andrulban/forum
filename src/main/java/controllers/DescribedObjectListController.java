/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.DBConnector;
import db_entities.DescribedObj;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author andrusha
 */

@ManagedBean (eager = true)
@SessionScoped

public class DescribedObjectListController implements Serializable{
    private String searchString ="";
    private DBConnector dBConnector;
    private List<DescribedObj> listOfDescribedObjs;
    
               
    public DescribedObjectListController() {
        dBConnector =  new DBConnector();
        search();
    }
    
    
    public void search() {
        listOfDescribedObjs=dBConnector.getLAllDescribedObjs();
        System.out.println(listOfDescribedObjs.get(0).getName());
    }
    
    
    
    

    
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<DescribedObj> getListOfDescribedObjs() {
        return listOfDescribedObjs;
    }

    public void setListOfDescribedObjs(List<DescribedObj> listOfDescribedObjs) {
        this.listOfDescribedObjs = listOfDescribedObjs;
    }
    
    
}
