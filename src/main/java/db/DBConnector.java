/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.PageOfDataGrid;
import db_entities.DescribedObj;
import db_entities.HibernateUtil;
import db_entitiesExt.DescribedObjExt;
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
    private List<DescribedObjExt> listOfDescribedObj;
    private PageOfDataGrid pageOfDataGrid;
    private DetachedCriteria dCriteria;
    private DetachedCriteria dCriteriaCount;

    public DBConnector(PageOfDataGrid pageOfDataGrid) {
        sessionFactory = HibernateUtil.getSessionFactory();
        this.pageOfDataGrid = pageOfDataGrid;
        createDCriteria();
        populatePageOfDataGrid();
        
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
        //criteria.setProjection(bookProjection).setResultTransformer(Transformers.aliasToBean(DescribedObjExt.class));
        List<DescribedObjExt> list = criteria.setFirstResult(pageOfDataGrid.getFrom()).setMaxResults(pageOfDataGrid.getTo()).list();
        pageOfDataGrid.setList(list);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void getLAllDescribedObjs() {        
        createDCriteria();
        populatePageOfDataGrid();
    }
    
    public void searchByName(String name) {
        createDCriteria();
        dCriteria.add(Restrictions.ilike("this.name", name, MatchMode.ANYWHERE));        
        dCriteriaCount.add(Restrictions.ilike("this.name", name, MatchMode.ANYWHERE));        
        populatePageOfDataGrid();
    }

    

}
