/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_entitiesExt;

import db_entities.Grade;

/**
 *
 * @author andrusha
 */
public class GradeExt extends  Grade{
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
        final GradeExt other = (GradeExt) obj;
        if (super.getId() != other.getId() && (super.getId() == null || !super.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ((Integer) super.getValue()).toString();
    }
}