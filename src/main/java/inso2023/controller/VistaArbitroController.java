package inso2023.controller;

import inso2023.ejb.ArbitroFacadeLocal;
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
    public List<Partido> listaPartidos;  
    public List<Partido> listaPartidosArbitro = new ArrayList<>();
    public List<Jugador> listaJugadores;
    public List<Jugador> listaJugadoresEquipoLocal = new ArrayList<>();
    public List<Jugador> listaJugadoresEquipoVis = new ArrayList<>();
    public boolean mostrarActas = false;
    public List<JugadorTabla> listaJugadoresPartidoLocal = new ArrayList<>();
    public List<JugadorTabla> listaJugadoresPartidoVis = new ArrayList<>();
   
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

        Partido partido = partidoEJB.find(idPartido);
        
        listaJugadoresPartidoLocal.clear();
        for (Jugador j : listaJugadores){
            if(j.getIdEquipo().getIdEquipo() == partido.getIdEquipoLocal().getIdEquipo()){
                listaJugadoresPartidoLocal.add(new JugadorTabla(j, partido, 0, 0, 0, 0));
            }
        }
        
        listaJugadoresPartidoVis.clear();
        for (Jugador j : listaJugadores){
            if(j.getIdEquipo().getIdEquipo() == partido.getIdEquipoVis().getIdEquipo()){
                listaJugadoresPartidoVis.add(new JugadorTabla(j, partido, 0, 0, 0, 0));
            }
        }

        //imprimir listaJugadoresPartidoLocal
        for(JugadorTabla j : listaJugadoresPartidoLocal){
            System.out.println(j.getJugador().getNombre());
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
    
    public boolean isMostrarActas() {
        return mostrarActas;
    }

    public void setMostrarActas(boolean mostrarActas) {
        this.mostrarActas = mostrarActas;
    }
  
    public class JugadorTabla{

        private Jugador jugador;
        private Partido partido;
        private int goles;
        private int asistencias;
        private int tarjetasAmarillas;
        private int tarjetasRojas;
        private boolean local;

        public JugadorTabla(Jugador jugador, Partido partido, int goles, int asistencias, int tarjetasAmarillas, int tarjetasRojas) {
            this.jugador = jugador;
            this.partido = partido;
            this.goles = goles;
            this.asistencias = asistencias;
            this.tarjetasAmarillas = tarjetasAmarillas;
            this.tarjetasRojas = tarjetasRojas;
        }

        public Jugador getJugador() {
            return jugador;
        }

        public void setJugador(Jugador jugador) {
            this.jugador = jugador;
        }

        public Partido getPartido() {
            return partido;
        }

        public void setPartido(Partido partido) {
            this.partido = partido;
        }

        public int getGoles() {
            return goles;
        }

        public void setGoles(int goles) {
            this.goles = goles;
            jugador.anotarGol(goles);
        }

        public int getAsistencias() {
            return asistencias;
        }

        public void setAsistencias(int asistencias) {
            this.asistencias = asistencias; 
            jugador.anotarAsistencia(asistencias);
        }

        public int getTarjetasAmarillas() {
            return tarjetasAmarillas;
        }

        public void setTarjetasAmarillas(int tarjetasAmarillas) {
            this.tarjetasAmarillas = tarjetasAmarillas;
            jugador.anotarTarjetaAmarilla(tarjetasAmarillas);
        }

        public int getTarjetasRojas() {
            return tarjetasRojas;
        }

        public void setTarjetasRojas(int tarjetasRojas) {
            this.tarjetasRojas = tarjetasRojas;
            jugador.anotarTarjetaRoja(tarjetasRojas);
        }

        public boolean isLocal() {
            return local;
        }

        public void setLocal(boolean local) {
            this.local = local;
        }

        
    }

    public List<JugadorTabla> getListaJugadoresPartidoLocal() {
        return listaJugadoresPartidoLocal;
    }

    public void setListaJugadoresPartidoLocal(List<JugadorTabla> listaJugadoresPartidoLocal) {
        this.listaJugadoresPartidoLocal = listaJugadoresPartidoLocal;
    }

    public List<JugadorTabla> getListaJugadoresPartidoVis() {
        return listaJugadoresPartidoVis;
    }

    public void setListaJugadoresPartidoVis(List<JugadorTabla> listaJugadoresPartidoVis) {
        this.listaJugadoresPartidoVis = listaJugadoresPartidoVis;
    }

    
    

}
