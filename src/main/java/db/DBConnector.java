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
import db_entitiesExt.GradeExt;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
    
    public void addDescrObj(DescribedObjExt describedObjExt) {
        getSession().save(describedObjExt);
    }
    
    public void deleteDescrObj(DescribedObjExt describedObjExt) {
        getSession().delete(describedObjExt);
    }
    
    public void editDescrObj(DescribedObjExt describedObjExt) {
        getSession().update(describedObjExt);
    }
    
    public void vote() {
        Criteria c = getSession().createCriteria(GradeExt.class);
        c.add(Restrictions.eq("this.idUser", 3L)).add(Restrictions.eq("this.idDescObj", pageOfDataGrid.getSelectedDescribedObj().getId()));
        List tr = c.list();
        if (!tr.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You can't rate it, you have already done it before. One person - one grade."));
            return;
        }
        GradeExt grade = new GradeExt();
        grade.setIdDescObj(pageOfDataGrid.getSelectedDescribedObj().getId());
        grade.setIdUser(1);
        grade.setValue(pageOfDataGrid.getSelectedDescribedObj().getGrade());
        getSession().save(grade);
        
        updateDescObjGrade();
    }
    
    private void updateDescObjGrade() {
       
        Criteria criteria = getSession().createCriteria(GradeExt.class);
        //criteria.setProjection(Projections.count("this.value")).add(Restrictions.eq("this.idDescObj", describedObjExt.getId()));
        //int count = (Integer) criteria.uniqueResult();
        criteria.setProjection(Projections.avg("this.value")).add(Restrictions.eq("this.idDescObj", pageOfDataGrid.getSelectedDescribedObj().getId()));
        int grade = Double.valueOf(criteria.uniqueResult().toString()).intValue();
        pageOfDataGrid.getSelectedDescribedObj().setGrade(grade);
        getSession().update(pageOfDataGrid.getSelectedDescribedObj());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have rated"));
        
        /* Query query = getSession().createQuery("select new map(round(avg(value)) as rating, count(value) as gradeCount)  from GradeExt g where g.idDescObj.id=:id");
        query.setParameter("id", describedObjExt.getId());

        List list = query.list();

        HashMap<String, Object> map = (HashMap<String, Object>) list.get(0);

        long gradeCount = Long.valueOf(map.get("gradeCount").toString());
        int grade = Float.valueOf(map.get("rating").toString()).intValue();

        query = getSession().createQuery("update DescribedObjExt set grade = :grade "
                + " where id = :id");

        query.setParameter("grade", grade);
        query.setParameter("id", describedObjExt.getId());

        int result = query.executeUpdate();*/
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
    
    public void deleteCommend(DescriptionExt commend) {
        getSession().delete(commend);
    }
    
    
    
    

}
