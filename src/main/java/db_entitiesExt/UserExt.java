package db_entitiesExt;
// Generated 08.05.2017 21:44:36 by Hibernate Tools 4.3.1


import db_entities.User;

/**
 * UserExt generated by hbm2java
 */
public class UserExt  extends User{


     @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (super.getId() != null ? super.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserExt other = (UserExt) obj;
        if (super.getId() != other.getId() && (super.getId() == null || !super.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }



}


