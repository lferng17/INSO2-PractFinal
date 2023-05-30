package inso2023.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import inso2023.model.Jugador;
import inso2023.ejb.JugadorFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class EstadisticaJugadoresController implements Serializable{

    @EJB
    private JugadorFacadeLocal jugadorFacade;
    
    private List<Jugador> listaJugadores;
    
    @PostConstruct
    public void init() {
        listaJugadores = jugadorFacade.findAll();
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    
    
    

    
    
    

}