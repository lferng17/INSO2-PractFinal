package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.model.Equipo;
import inso2023.model.Jugador;

@Named
@ViewScoped
public class VistaEliminarJugadorController implements Serializable{   
    private int idJugador;
    private int idEquipo;
    private List<Jugador> listaJugadores;
    private List<Equipo> listaEquipos;
    private boolean mostrarCombo = false;
    List<Jugador> listaJugadoresEquipo = new ArrayList<>();

    @EJB
    private JugadorFacadeLocal jugadorEJB;
    @EJB
    private EquipoFacadeLocal equipoEJB;

    @PostConstruct
    public void init(){
        listaEquipos = equipoEJB.findAll();
    }
    

    public void eliminarJugador() throws IOException{
        Jugador jugador = jugadorEJB.find(idJugador);
        jugadorEJB.remove(jugador);
        listaJugadoresEquipo.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("vistaAdministrador.xhtml");
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public int getIdEquipo() {
        return idEquipo;
    }


    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }


    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }


    public boolean isMostrarCombo() {
        return mostrarCombo;
    }

    public void mostrarCombo(){
        listaJugadoresEquipo.clear();
        mostrarCombo = true;
        listaJugadores = jugadorEJB.findAll();
        for (Jugador j : listaJugadores){
            if(j.getIdEquipo().getIdEquipo() == idEquipo){
                listaJugadoresEquipo.add(j);
            }
        }
        //imprimir lista de jugadores
        for(Jugador j : listaJugadores){
            System.out.println(j.getNombre());
        }
    }


    public void setMostrarCombo(boolean mostrarCombo) {
        this.mostrarCombo = mostrarCombo;
    }


    public List<Jugador> getListaJugadoresEquipo() {
        return listaJugadoresEquipo;
    }


    public void setListaJugadoresEquipo(List<Jugador> listaJugadoresEquipo) {
        this.listaJugadoresEquipo = listaJugadoresEquipo;
    }

    

}
