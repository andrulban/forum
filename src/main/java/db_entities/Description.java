package db_entities;
// Generated 08.05.2017 21:44:36 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Description generated by hbm2java
 */
public class Description  implements java.io.Serializable {


     private Long id;
     private User user;
     private long idObject;
     private String description;
     private Date dateOfDescription;

    public Description() {
    }

    public Description(User user, long idObject, String description, Date dateOfDescription) {
       this.user = user;
       this.idObject = idObject;
       this.description = description;
       this.dateOfDescription = dateOfDescription;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public long getIdObject() {
        return this.idObject;
    }
    
    public void setIdObject(long idObject) {
        this.idObject = idObject;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDateOfDescription() {
        return this.dateOfDescription;
    }
    
    public void setDateOfDescription(Date dateOfDescription) {
        this.dateOfDescription = dateOfDescription;
    }




}


