package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

    public void eliminarPartido() throws IOException{
        Partido partido = partidoEJB.find(idPartido);
        partidoEJB.remove(partido);
        listaPartidos.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaAdministrador.xhtml");
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