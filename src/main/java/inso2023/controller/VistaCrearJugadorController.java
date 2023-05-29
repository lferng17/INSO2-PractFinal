package inso2023.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Date;

import inso2023.ejb.EquipoFacade;
import inso2023.model.Equipo;
import inso2023.model.Jugador;

@ManagedBean
public class VistaCrearJugadorController {

    private String nombre;
    private String apellidos;
    private int dorsal;
    private Date fechaNac;
    private int goles;
    private int asistencias;
    private int tarjAma;
    private int tarjRojas;
    private int idEquipo;
    private int capitan;
    private String email;
    private String contrasena;
    
    
    public void verificarAdministrador() throws Exception{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != "admin"){
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        }
    }

    public void crearJugador(){
        Jugador jugador = new Jugador();
        jugador.setNombre("Luis");
        jugador.setApellidos("Fernandez");
        jugador.setDorsal(0);
        jugador.setFechaNac(null);
        jugador.setGoles(0);
        jugador.setAsistencias(0);
        jugador.setTarjAma(0);
        jugador.setTarjRojas(0);
        jugador.setIdEquipo(null);
        jugador.setCapitan(0);
        jugador.setEmail("");
        jugador.setContrasena("1234");

        System.out.println("Jugador creado");
    }

    public List<Equipo> listaEquipos(){
        EquipoFacade equipoFacade = new EquipoFacade();
        return equipoFacade.findAll();
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

    public int getCapitan() {
        return capitan;
    }

    public void setCapitan(int capitan) {
        this.capitan = capitan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
}
