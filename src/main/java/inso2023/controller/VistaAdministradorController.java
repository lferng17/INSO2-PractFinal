package inso2023.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class VistaAdministradorController implements Serializable {

    public void verificarAdministrador() throws Exception {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")
                    .equals("admin")) {
                String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                String url = contextPath + "/faces/index.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            }
        } catch (Exception e) {
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }

    }

    public void crearJugador() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaCrearJugador.xhtml");
    }

    public void editarJugador() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEditarJugador.xhtml");
    }

    public void eliminarJugador() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEliminarJugador.xhtml");
    }

    public void crearEquipo() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaCrearEquipo.xhtml");
    }

    public void editarEquipo() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEditarEquipo.xhtml");
    }

    public void eliminarEquipo() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEliminarEquipo.xhtml");
    }

    public void crearPartido() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaCrearPartido.xhtml");
    }

    public void editarPartido() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEditarPartido.xhtml");
    }

    public void eliminarPartido() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEliminarPartido.xhtml");
    }

    public void crearArbitro() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaCrearArbitro.xhtml");
    }

    public void editarArbitro() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEditarArbitro.xhtml");
    }

    public void eliminarArbitro() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaEliminarArbitro.xhtml");
    }

}
