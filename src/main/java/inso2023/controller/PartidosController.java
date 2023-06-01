package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import inso2023.model.Partido;
import inso2023.ejb.PartidoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class PartidosController implements Serializable{

    @EJB
    private PartidoFacadeLocal partidoFacade;
    
    private List<Partido> listaPartidos;
    
    @PostConstruct
    public void init() {
        listaPartidos = partidoFacade.findAll();
    }

    public List<Partido> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }
      
    
    
    
    

    
    
    

}