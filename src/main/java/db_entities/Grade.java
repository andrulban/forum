package db_entities;
// Generated 08.05.2017 21:44:36 by Hibernate Tools 4.3.1



/**
 * Grade generated by hbm2java
 */
public class Grade  implements java.io.Serializable {


     private Long id;
     private long idUser;
     private int value;
     private long idDescObj;

    public Grade() {
    }

    public Grade(long idUser, int value, long idDescObj) {
       this.idUser = idUser;
       this.value = value;
       this.idDescObj = idDescObj;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public long getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    public long getIdDescObj() {
        return this.idDescObj;
    }
    
    public void setIdDescObj(long idDescObj) {
        this.idDescObj = idDescObj;
    }




}


