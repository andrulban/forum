/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db_entities.DescribedObj;
import db_entities.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author andrusha
 */
public class DBConnector {

    private Session session;
    private SessionFactory sessionFactory;
    private Criteria criteria;
    private List<DescribedObj> listOfDescribedObj;

    public DBConnector(Session session, Criteria criteria) {
        this.session = session;
        this.criteria = criteria;
    }

    public DBConnector() {

    }

    public List<DescribedObj> getLAllDescribedObjs() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        criteria = session.createCriteria(DescribedObj.class);
        listOfDescribedObj = criteria.list();

        session.getTransaction().commit();
        return listOfDescribedObj;
    }

}
