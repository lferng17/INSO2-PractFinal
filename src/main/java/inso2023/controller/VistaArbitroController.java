package inso2023.controller;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Jugador;
import inso2023.model.Partido;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class VistaArbitroController implements Serializable{
    
    public int idPartido;
    public int idArbitro;
    public int idEquipoLocal;
    public int idEquipoVis;
    public Partido partidoSeleccionado;
    public List<Partido> listaPartidos;  
    public List<Partido> listaPartidosArbitro = new ArrayList<>();
    public List<Jugador> listaJugadores;
    public List<Jugador> listaJugadoresEquipoLocal = new ArrayList<>();
    public List<Jugador> listaJugadoresEquipoVis = new ArrayList<>();
    public boolean mostrarActas = false;
   
    @EJB
    private PartidoFacadeLocal partidoEJB;
 
    @EJB
    private ArbitroFacadeLocal arbitroEJB;
 
    @EJB
    private JugadorFacadeLocal jugadorEJB;
    
     
    @PostConstruct
    public void init(){
        //filtrar la lista de partidos
        listaPartidos = partidoEJB.findAll();
        idArbitro = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idArbitro");
        //Añado todos los jugadores de la liga a una lista para más tarde
        listaJugadores = jugadorEJB.findAll();
        
        //Añadir a listaPartidosArbitro los partidos de listaPartidos que tengan el idArbitro y no se hayan jugado
        for(Partido p : listaPartidos){
            if((p.getIdArbitro().getIdArbitro() == idArbitro)){
                LocalDate fechaActual = LocalDate.now();
                String fechaPartido = p.getFecha();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(fechaPartido, formatter);
                boolean esAnterior = fecha.isBefore(fechaActual);
                
                if(esAnterior){
                    listaPartidosArbitro.add(p);       
                }

            }
        }
        
        
    }

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

 
    public void cargarJugadoresEquipos(){
       
        mostrarActas = true;
        
        listaJugadoresEquipoLocal.clear();
        for (Jugador j : listaJugadores){
            if(j.getIdEquipo().getIdEquipo() == idEquipoLocal){
                listaJugadoresEquipoLocal.add(j);
            }
        }
        
        listaJugadoresEquipoVis.clear();
        for (Jugador j : listaJugadores){
            if(j.getIdEquipo().getIdEquipo() == idEquipoVis){
                listaJugadoresEquipoVis.add(j);
            }
        }
        
        //imprimir lista de jugadores
        for(Jugador j : listaJugadoresEquipoLocal){
            System.out.println(j.getNombre());
        }

    } 

    
    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getIdEquipoVis() {
        return idEquipoVis;
    }

    public void setIdEquipoVis(int idEquipoVis) {
        this.idEquipoVis = idEquipoVis;
    }


    public List<Partido> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }
    
    
    public List<Partido> getListaPartidosArbitro() {
        return listaPartidosArbitro;
    }

    public void setListaPartidosArbitro(List<Partido> listaPartidosArbitro) {
        this.listaPartidosArbitro = listaPartidosArbitro;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public List<Jugador> getListaJugadoresEquipoLocal() {
        return listaJugadoresEquipoLocal;
    }

    public void setListaJugadoresEquipoLocal(List<Jugador> listaJugadoresEquipoLocal) {
        this.listaJugadoresEquipoLocal = listaJugadoresEquipoLocal;
    }

    public List<Jugador> getListaJugadoresEquipoVis() {
        return listaJugadoresEquipoVis;
    }

    public void setListaJugadoresEquipoVis(List<Jugador> listaJugadoresEquipoVis) {
        this.listaJugadoresEquipoVis = listaJugadoresEquipoVis;
    }

    public Partido getPartidoSeleccionado() {
        return partidoSeleccionado;
    }

    public void setPartidoSeleccionado(Partido partidoSeleccionado) {
        this.partidoSeleccionado = partidoSeleccionado;
    }
    
    public boolean isMostrarActas() {
        return mostrarActas;
    }

    public void setMostrarActas(boolean mostrarActas) {
        this.mostrarActas = mostrarActas;
    }
    
  
    
    

}
