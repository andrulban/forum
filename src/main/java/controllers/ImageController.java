/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author andrusha
 */
@ManagedBean
@RequestScoped

public class ImageController implements Serializable {

    private byte[] uploadedImage;
    UploadedFile file;

    public ImageController() {

    }

    public void upload() {
        if (file.getContents().length == 0) {
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DescribedObjectListController describedObjectListController = (DescribedObjectListController) session.getAttribute("describedObjectListController");
        describedObjectListController.getAddingDescribedObjExt().setFoto(file.getContents());
        file = null;
    }
    
    public void uploadFromEditing() {
        if (file.getContents().length == 0) {
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        DescribedObjectListController describedObjectListController = (DescribedObjectListController) session.getAttribute("describedObjectListController");
        describedObjectListController.getPageOfDataGrid().getSelectedDescribedObj().setFoto(file.getContents());
        file = null;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
