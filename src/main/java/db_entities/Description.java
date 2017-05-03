package db_entities;
// Generated 03.05.2017 16:40:53 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Description generated by hbm2java
 */
public class Description  implements java.io.Serializable {


     private Long id;
     private long idObject;
     private long idUser;
     private String description;
     private Date dateOfDescription;

    public Description() {
    }

	
    public Description(long idObject, long idUser, String description) {
        this.idObject = idObject;
        this.idUser = idUser;
        this.description = description;
    }
    public Description(long idObject, long idUser, String description, Date dateOfDescription) {
       this.idObject = idObject;
       this.idUser = idUser;
       this.description = description;
       this.dateOfDescription = dateOfDescription;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public long getIdObject() {
        return this.idObject;
    }
    
    public void setIdObject(long idObject) {
        this.idObject = idObject;
    }
    public long getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

