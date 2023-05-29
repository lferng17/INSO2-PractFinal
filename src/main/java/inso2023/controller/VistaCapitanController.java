package inso2023.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class VistaCapitanController implements Serializable {

    public void verificarCapitan() throws Exception {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != "jugador") {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        } else {
            System.out.println("Vista Capitan");
        }
    }
}
