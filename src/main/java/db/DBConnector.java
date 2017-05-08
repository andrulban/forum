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
import db_entities.Invitations;
import db_entitiesExt.DescribedObjExt;
import db_entitiesExt.DescriptionExt;
import db_entitiesExt.GradeExt;
import db_entitiesExt.UserExt;
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
import org.hibernate.exception.ConstraintViolationException;
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
    private UserExt userExt;

    public DBConnector() {
        sessionFactory = HibernateUtil.getSessionFactory();
        createDCriteria();
        createDDescriptionCriteria();
    }

    public void setPageOfDataGrid(PageOfDataGrid pageOfDataGrid) {
        this.pageOfDataGrid = pageOfDataGrid;
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
        describedObjExt.setAmountOfGrade(0);
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
        c.add(Restrictions.eq("this.idUser",userExt.getId())).add(Restrictions.eq("this.idDescObj", pageOfDataGrid.getSelectedDescribedObj().getId()));
        List tr = c.list();
        if (!tr.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You can't rate it, you have already done it before. One person - one grade."));
            return;
        }
        GradeExt grade = new GradeExt();
        grade.setIdDescObj(pageOfDataGrid.getSelectedDescribedObj().getId());
        grade.setIdUser(userExt.getId());
        grade.setValue(pageOfDataGrid.getSelectedDescribedObj().getGrade());
        getSession().save(grade);

        updateDescObjGrade();
    }

    private void updateDescObjGrade() {

        Criteria criteria = getSession().createCriteria(GradeExt.class);
        criteria.setProjection(Projections.avg("this.value")).add(Restrictions.eq("this.idDescObj", pageOfDataGrid.getSelectedDescribedObj().getId()));
        int grade = Double.valueOf(criteria.uniqueResult().toString()).intValue();
        pageOfDataGrid.getSelectedDescribedObj().setGrade(grade);
        pageOfDataGrid.getSelectedDescribedObj().setAmountOfGrade(pageOfDataGrid.getSelectedDescribedObj().getAmountOfGrade() + 1);
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
        commend.setUser(userExt);
        getSession().save(commend);
    }

    public void deleteCommend(DescriptionExt commend) {
        getSession().delete(commend);
    }

    public UserExt login(String username, String password) {
        getSession().beginTransaction();
        Criteria c = getSession().createCriteria(UserExt.class);
        c.add(Restrictions.eq("username", username));
        c.add(Restrictions.like("password", password));
        List<UserExt> list = c.list();
        getSession().getTransaction().commit();

        if (!list.isEmpty()) {
            userExt = list.get(0);
            getSession().close();
            return userExt;
        }
        getSession().close();
        return null;
    }

    public void signUp(String username, String password, String key) {
        getSession().beginTransaction();
        Criteria c = getSession().createCriteria(Invitations.class);
        c.add(Restrictions.eq("keyStr", key));
        List<Invitations> keys = c.list();

        if (!keys.isEmpty()) {
            UserExt u = new UserExt();
            u.setUsername(username);
            u.setPassword(password);
            u.setRole(keys.get(0).getLvlPermission());
            try {
                getSession().save(u);
                getSession().getTransaction().commit();
                getSession().beginTransaction();
                getSession().delete(keys.get(0));
                getSession().getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Congratulations! Now you can log in!"));
            } catch (ConstraintViolationException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This username is already exists, try again."));
            } finally {
                getSession().close();
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Key is not valid!"));
        }

        /*
        getSession().beginTransaction();
        Invitations invite = new Invitations();
        invite.setKeyStr(key);
        try {
            getSession().save(invite);
            getSession().getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("..."));
        }*/
    }

}
