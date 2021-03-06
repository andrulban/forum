package db_entities;
// Generated 08.05.2017 21:44:36 by Hibernate Tools 4.3.1



/**
 * DescribedObj generated by hbm2java
 */
public class DescribedObj  implements java.io.Serializable {


     private Long id;
     private String name;
     private byte[] foto;
     private String contacts;
     private Integer grade;
     private int amountOfGrade;

    public DescribedObj() {
    }

	
    public DescribedObj(String name, String contacts, int amountOfGrade) {
        this.name = name;
        this.contacts = contacts;
        this.amountOfGrade = amountOfGrade;
    }
    public DescribedObj(String name, byte[] foto, String contacts, Integer grade, int amountOfGrade) {
       this.name = name;
       this.foto = foto;
       this.contacts = contacts;
       this.grade = grade;
       this.amountOfGrade = amountOfGrade;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getFoto() {
        return this.foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public String getContacts() {
        return this.contacts;
    }
    
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    public int getAmountOfGrade() {
        return this.amountOfGrade;
    }
    
    public void setAmountOfGrade(int amountOfGrade) {
        this.amountOfGrade = amountOfGrade;
    }




}


