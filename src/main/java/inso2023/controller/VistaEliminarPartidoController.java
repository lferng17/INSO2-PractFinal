package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Partido;

@ManagedBean
@ViewScoped
public class VistaEliminarPartidoController implements Serializable{
    private List<Partido> listaPartidos;
    private int idPartido;
    @EJB
    private PartidoFacadeLocal partidoEJB;


    @PostConstruct
    public void init(){
        listaPartidos = partidoEJB.findAll();
    }

    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println(usuario);
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void eliminarPartido() throws IOException{
        try{
            Partido partido = partidoEJB.find(idPartido);
            partidoEJB.remove(partido);
            listaPartidos.clear();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partido eliminado", "Partido eliminado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Partido no eliminado", "No se ha podido eliminar el partido, compruebe los datos introducidos."));
        }   

    }

    public List<Partido> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

}