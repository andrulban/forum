/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.PageOfDataGrid;
import beans.PageOfDataGridDescriptions;
import db_entities.DescribedObj;
import db_entities.HibernateUtil;
import db_entitiesExt.DescribedObjExt;
import db_entitiesExt.DescriptionExt;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author andrusha
 */
public class DBConnector {

    private static SessionFactory sessionFactory;
    //private List<DescribedObjExt> listOfDescribedObj;
    private PageOfDataGrid pageOfDataGrid;
    private DetachedCriteria dCriteria;
    private DetachedCriteria dCriteriaCount;
    private DetachedCriteria dDescriptionCriteria;
    private DetachedCriteria dDescriptionCriteriaCount;
    private PageOfDataGridDescriptions pageOfDataGridDescriptions;

    
    public DBConnector(PageOfDataGrid pageOfDataGrid) {
        sessionFactory = HibernateUtil.getSessionFactory();
        this.pageOfDataGrid = pageOfDataGrid;
        createDCriteria();
        createDDescriptionCriteria();
        populatePageOfDataGrid();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    private void createDCriteria() {
        dCriteria = DetachedCriteria.forClass(DescribedObjExt.class);
        dCriteriaCount = DetachedCriteria.forClass(DescribedObjExt.class);
        //добавить алисы для них                private void makeAliases()
    }
    
    public void populatePageOfDataGrid() {
        runDCriteriaCount();
        runDCriteria();
    }
    
    private void runDCriteriaCount() {
        Criteria criteria = dCriteriaCount.getExecutableCriteria(getSession());
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        pageOfDataGrid.setTotalDescribedObjCount(total);
    }

    private void runDCriteria() {
        Criteria criteria = dCriteria.addOrder(Order.asc("this.name")).getExecutableCriteria(getSession());
        //criteria.setProjection(#).setResultTransformer(Transformers.aliasToBean(DescribedObjExt.class));
        List<DescribedObjExt> list = criteria.setFirstResult(pageOfDataGrid.getFrom()).setMaxResults(pageOfDataGrid.getTo()).list();
        pageOfDataGrid.setList(list);
    }

    private void createDDescriptionCriteria() {
        dDescriptionCriteria = DetachedCriteria.forClass(DescriptionExt.class);
        dDescriptionCriteriaCount = DetachedCriteria.forClass(DescriptionExt.class);
    }

    public void populatePageOfDataGridDescription() {
        runDDescriptionCriteriaCount();
        runDDescriptionCriteria();
    }
    
    private void runDDescriptionCriteriaCount() {
        Criteria criteria = dDescriptionCriteriaCount.getExecutableCriteria(getSession());
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        pageOfDataGridDescriptions.setTotalDescriptionCount(total);
    }
    
    private void runDDescriptionCriteria() {
        Criteria criteria = dDescriptionCriteria.addOrder(Order.desc("this.dateOfDescription")).getExecutableCriteria(getSession());
        List<DescriptionExt> list = criteria.setFirstResult(pageOfDataGridDescriptions.getFrom()).setMaxResults(pageOfDataGridDescriptions.getTo()).list();
        pageOfDataGridDescriptions.setList(list);
    }

    public void getLAllDescribedObjs() {
        createDCriteria();
        populatePageOfDataGrid();
    }

    public void searchDescObjByName(String name) {
        createDCriteria();
        dCriteria.add(Restrictions.ilike("this.name", name, MatchMode.ANYWHERE));
        dCriteriaCount.add(Restrictions.ilike("this.name", name, MatchMode.ANYWHERE));
        populatePageOfDataGrid();
    }
    
    

    public void setPageOfDataGridDescriptions(PageOfDataGridDescriptions pageOfDataGridDescriptions) {
        this.pageOfDataGridDescriptions = pageOfDataGridDescriptions;
    }
    
    public void searchDescriptionById(long id) {
        createDDescriptionCriteria();
        dDescriptionCriteria.add(Restrictions.eq("this.idObject", id));
        dDescriptionCriteriaCount.add(Restrictions.eq("this.idObject", id));
        populatePageOfDataGridDescription();
    }
    
    public void addCommend(DescriptionExt commend) {
        getSession().save(commend);
    }

}
