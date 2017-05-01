/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db_entitiesExt.DescribedObjExt;
import java.util.List;

/**
 *
 * @author andrusha
 */
public class PageOfDataGrid {
    private long totalDescribedObjCount;
    private DescribedObjExt selectedDescribedObj;
    private int rowIndex;
    private int from;
    private int to;
    private List<DescribedObjExt> list;

    public PageOfDataGrid() {
        //setFrom(0);
        //setTo(10);
    }    
   
    public List<DescribedObjExt> getList() {
        return list;
    }

    public void setList(List<DescribedObjExt> list) {
        rowIndex=-1;
        this.list = list;
    }
    
    public int getRowIndex() {
        return ++rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex=rowIndex;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public long getTotalDescribedObjCount() {
        return totalDescribedObjCount;
    }

    public void setTotalDescribedObjCount(long totalDescribedObj) {
        this.totalDescribedObjCount = totalDescribedObj;
    }

    public DescribedObjExt getSelectedDescribedObj() {
        return selectedDescribedObj;
    }

    public void setSelectedDescribedObj(DescribedObjExt selectedDescribedObj) {
        this.selectedDescribedObj = selectedDescribedObj;
    }
    
    
}
