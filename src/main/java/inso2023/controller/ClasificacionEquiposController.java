package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import inso2023.model.Equipo;
import inso2023.ejb.EquipoFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class ClasificacionEquiposController implements Serializable{

    @EJB
    private EquipoFacadeLocal equipoFacade;
    
    private List<Equipo> listaEquipos;
    
    @PostConstruct
    public void init() {
        listaEquipos = equipoFacade.findAll();
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }
    
    

    
    
    

}