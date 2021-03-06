package db_entities;
// Generated 08.05.2017 21:44:36 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Long id;
     private String password;
     private int role;
     private String username;
     private Set descriptions = new HashSet(0);

    public User() {
    }

	
    public User(String password, int role, String username) {
        this.password = password;
        this.role = role;
        this.username = username;
    }
    public User(String password, int role, String username, Set descriptions) {
       this.password = password;
       this.role = role;
       this.username = username;
       this.descriptions = descriptions;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole() {
        return this.role;
    }
    
    public void setRole(int role) {
        this.role = role;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public Set getDescriptions() {
        return this.descriptions;
    }
    
    public void setDescriptions(Set descriptions) {
        this.descriptions = descriptions;
    }




}


