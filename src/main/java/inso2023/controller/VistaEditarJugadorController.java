package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.model.Equipo;
import inso2023.model.Jugador;

@ManagedBean
public class VistaEditarJugadorController {
    private int idEquipoMod;
    private int idJugadorMod;
    private String nombre;
    private String apellidos;
    private int dorsal;
    private String dni;
    private Date fechaNac;
    private int goles;
    private int asistencias;
    private int tarjAma;
    private int tarjRojas;
    private int idEquipo;
    private boolean capitan;
    private List<Jugador> jugadoresEquipo = new ArrayList<>();

    @EJB
    EquipoFacadeLocal equipoFacadeLocal;

    @EJB
    JugadorFacadeLocal jugadorFacadeLocal;
    
    
    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println(usuario);
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void editarJugador(){
        Jugador jugador = new Jugador();
        jugador.setIdJugador(this.idJugadorMod);
        jugador.setNombre(this.nombre);
        jugador.setApellidos(this.apellidos);
        jugador.setDorsal(this.dorsal);
        jugador.setDni(this.dni);
        jugador.setFechaNac(this.fechaNac);
        jugador.setGoles(this.goles);
        jugador.setAsistencias(this.asistencias);
        jugador.setTarjAma(this.tarjAma);
        jugador.setTarjRojas(this.tarjRojas);
        jugador.setIdEquipo(equipoFacadeLocal.find(this.idEquipo));
        if(this.capitan == true){
            jugador.setCapitan(1);
            jugador.setEmail(this.nombre + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com");
            jugador.setContrasena(this.dni);
        }else{
            jugador.setCapitan(0);
            jugador.setEmail(null);
            jugador.setContrasena(null);
        }

        jugadorFacadeLocal.edit(jugador);

        System.out.println("Jugador editado");
    }

    public List<Equipo> getEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public void setJugadoresEquipo(){
        List<Jugador> jugadores = jugadorFacadeLocal.findAll();
        for(Jugador jugador : jugadores){
            if(jugador.getIdEquipo().getIdEquipo() == this.idEquipoMod){
                jugadoresEquipo.add(jugador);
            }
        }

    }

    public List<Jugador> getJugadoresEquipo(){
        if(jugadoresEquipo.isEmpty()){
            jugadoresEquipo = jugadorFacadeLocal.findAll();
        }
        return jugadoresEquipo;
    }

    public void setDatosJugador(){
        Jugador jugadorMod = jugadorFacadeLocal.find(this.idJugadorMod);
        this.nombre = jugadorMod.getNombre();
        this.apellidos= jugadorMod.getApellidos();
        this.dorsal = jugadorMod.getDorsal();
        this.dni = jugadorMod.getDni();
        this.fechaNac = jugadorMod.getFechaNac();
        this.goles = jugadorMod.getGoles();
        this.asistencias = jugadorMod.getAsistencias();
        this.tarjAma = jugadorMod.getTarjAma();
        this.tarjRojas = jugadorMod.getTarjRojas();
        this.idEquipo = jugadorMod.getIdEquipo().getIdEquipo();
        if(jugadorMod.getCapitan() == 1){
            this.capitan = true;
        }else{
            this.capitan = false;
        }
    }

    public void isCapitan(){
        this.capitan = true;
    }

    public int getIdEquipoMod() {
        return idEquipoMod;
    }
    
    public void setIdEquipoMod(int idEquipoMod) {
        this.idEquipoMod = idEquipoMod;
    }

    public int getIdJugadorMod() {
        return idJugadorMod;
    }
    
    public void setIdJugadorMod(int idJugadorMod){
        this.idJugadorMod = idJugadorMod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public String getDni(){
        return dni;
    }   

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getTarjAma() {
        return tarjAma;
    }

    public void setTarjAma(int tarjAma) {
        this.tarjAma = tarjAma;
    }

    public int getTarjRojas() {
        return tarjRojas;
    }

    public void setTarjRojas(int tarjRojas) {
        this.tarjRojas = tarjRojas;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public boolean getCapitan() {
        return capitan;
    }

    public void setCapitan(boolean capitan) {
        this.capitan = capitan;
    }

}
