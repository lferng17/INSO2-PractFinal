package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ViewScoped
public class VistaArbitroController implements Serializable{

    public void verificarArbitro() throws Exception{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("publico/sinAcceso.xhtml");
        }
    }

}
