package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean
@ViewScoped
public class VistaArbitroController implements Serializable{

    public void verificarArbitro() throws Exception{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != "arbitro"){
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        }else{
            System.out.println("Vista Arbitro");
        }
    }

    //metodo boolean que devuelve true si el usuario es arbitro
    public boolean esArbitro(){
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == "arbitro"){
            return true;
        } else{
            return false;
        }
    }

}
