/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db_entitiesExt.DescriptionExt;
import java.util.List;

/**
 *
 * @author andrusha
 */
public class PageOfDataGridDescriptions {

    private long totalDescriptionCount;
    //private DescriptionExt selectedDescription;
    //private int indexOfSelectedObj;
    //private int rowIndex;
    private int from;
    private int to;
    private List<DescriptionExt> list;

    public PageOfDataGridDescriptions() {

    }

    public List<DescriptionExt> getList() {
        return list;
    }

    public void setList(List<DescriptionExt> list) {
        // rowIndex = -1;
        this.list = list;
    }

    /*public int getRowIndex() {
        return ++rowIndex;
    }

    public int getSameRowIndex() {
        System.out.println(rowIndex);        
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }*/
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

    public long getTotalDescriptionCount() {
        return totalDescriptionCount;
    }

    public void setTotalDescriptionCount(long totalDescribedObj) {
        this.totalDescriptionCount = totalDescribedObj;
    }

}
