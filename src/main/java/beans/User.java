/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author andrusha
 */
@SessionScoped
@ManagedBean


public class User implements Serializable{
    
    private String username;
    private String password;

    public User() {
    }

    /*public User(String username, String password) {
        this.username = username;
        this.password = password;
    }*/

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

 /*   public String login() {
        try {

//            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).logout();
//            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (request.getUserPrincipal() == null || (request.getUserPrincipal() != null && !request.getUserPrincipal().getName().equals(username))) {
                request.logout();
                request.login(username, password);
            }

            // ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).login(username, password);
            return "tobooksfromindex";
        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle rs = ResourceBundle.getBundle("propertiesFiles/messages", context.getViewRoot().getLocale());
            FacesMessage message = new FacesMessage(rs.getString("index_invalidLogOrPas"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("login_form", message);

        }

        return "index";

    }

    public String logout() {
        String result = "index";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return result;
    }
*/
    public String goHome() {
        return "index";
    }

    public String goToBooks() {
        return "forum";
    }
    
    
}