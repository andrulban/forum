/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.DBConnector;
import db_entities.Invitations;
import db_entitiesExt.UserExt;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author andrusha
 */
@SessionScoped
@ManagedBean

public class UserController implements Serializable {

    private String username;
    private String password;
    private DBConnector dBConnector;
    private Invitations invite;
    private UserExt user;
    private String key;

    public UserController() {
        dBConnector = new DBConnector();
        invite = new Invitations();
    }

    public String goHome() {
        return "index";
    }

    public String goToBooks() {
        user = dBConnector.login(getUsername(), getPassword());
        if (user != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("login", true);
            return "forum";
        }
        return "index";
    }

    public void signUp() {
        
        
        dBConnector.signUp(username, password, invite.getKeyStr());
        RequestContext.getCurrentInstance().execute("PF('registrationDialog').hide();");
        invite = new Invitations();

    }

    public void generateKye() {
        key = dBConnector.generateKye();
        RequestContext.getCurrentInstance().execute("PF('keyGeneration').show();");
    }

    public String logOut() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "index";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setdBConnector(DBConnector dBConnector) {
        this.dBConnector = dBConnector;
    }

    public void setUser(UserExt user) {
        this.user = user;
    }

    public DBConnector getdBConnector() {
        return dBConnector;
    }

    public UserExt getUser() {
        return user;
    }

    public Invitations getInvite() {
        return invite;
    }

    public void setInvite(Invitations invite) {
        this.invite = invite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
