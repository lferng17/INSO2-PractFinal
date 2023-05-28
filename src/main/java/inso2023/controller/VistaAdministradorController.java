package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ViewScoped
public class VistaAdministradorController implements Serializable{

    public void verificarAdministrador() throws Exception{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usario") != "admin"){
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        }else{
            System.out.println("Vista Administrador");
        }
    }

    public void crearJugador() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("crearJugador.xhtml");
    }

    public void editarJugador() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("editarJugador.xhtml");
    }

    public void eliminarJugador() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("eliminarJugador.xhtml");
    }

    public void crearEquipo() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("crearEquipo.xhtml");
    }

    public void editarEquipo() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("editarEquipo.xhtml");
    }

    public void eliminarEquipo() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("eliminarEquipo.xhtml");
    }

    public void crearPartido() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("crearPartido.xhtml");
    }

    public void editarPartido() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("editarPartido.xhtml");
    }

    public void eliminarPartido() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("eliminarPartido.xhtml");
    }

    public void crearArbitro() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("crearArbitro.xhtml");
    }

    public void editarArbitro() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("editarArbitro.xhtml");
    }

    public void eliminarArbitro() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().redirect("eliminarArbitro.xhtml");
    }

}
