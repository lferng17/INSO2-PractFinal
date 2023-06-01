package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.model.Equipo;
import inso2023.model.Jugador;

@ManagedBean
public class VistaCrearJugadorController implements Serializable{

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

    @EJB
    EquipoFacadeLocal equipoFacadeLocal;

    @EJB
    JugadorFacadeLocal jugadorFacadeLocal;
    
    
    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void crearJugador(){
        try{
            Jugador jugador = new Jugador();
            jugador.setNombre(this.nombre);
            jugador.setApellidos(this.apellidos);
            jugador.setDorsal(this.dorsal);
            jugador.setDni(this.dni);
            jugador.setFechaNac(this.fechaNac);
            jugador.setGoles(0);
            jugador.setAsistencias(0);
            jugador.setTarjAma(0);
            jugador.setTarjRojas(0);
            jugador.setIdEquipo(equipoFacadeLocal.find(this.idEquipo));
            if(capitan == true){
                jugador.setCapitan(1);
                jugador.setEmail(this.nombre + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com");
                jugador.setContrasena(this.dni);
            }else{
                jugador.setCapitan(0);
                jugador.setEmail(null);
                jugador.setContrasena(null);
            }
    
            jugadorFacadeLocal.create(jugador);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador creado", "Jugador creado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Error al crear el jugador."));
        }
    }

    public List<Equipo> listaEquipos(){
        return equipoFacadeLocal.findAll();
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
