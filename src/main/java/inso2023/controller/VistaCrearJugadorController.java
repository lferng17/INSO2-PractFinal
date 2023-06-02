package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.List;
import java.io.Serializable;
import java.text.Normalizer;
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
    private boolean crear;

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
            crear = true;
            jugador.setNombre(this.nombre);
            jugador.setApellidos(this.apellidos);
            if(this.dorsal > 0 && this.dorsal < 100){
                jugador.setDorsal(this.dorsal);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "El dorsal no es válido."));
            }

            if(this.dni.matches("[0-9]{8}[A-Za-z]")){
                jugador.setDni(this.dni);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "El DNI no es válido."));
            }

            jugador.setFechaNac(this.fechaNac);
            if(this.goles >= 0){
                jugador.setGoles(this.goles);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Los goles no son válidos."));
            }

            if(this.asistencias >= 0){
                jugador.setAsistencias(this.asistencias);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Las asistencias no son válidas."));
            }

            if(this.tarjAma >= 0){
                jugador.setTarjAma(this.tarjAma);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Las tarjetas amarillas no son válidas."));
            }

            if(this.tarjRojas >= 0){
                jugador.setTarjRojas(this.tarjRojas);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Las tarjetas rojas no son válidas."));
            }

            jugador.setIdEquipo(equipoFacadeLocal.find(this.idEquipo));

            if(capitan == true){
                jugador.setCapitan(1);
                jugador.setEmail(generarCorreo());
                jugador.setContrasena(this.dni.toLowerCase());
            }else{
                jugador.setCapitan(0);
                jugador.setEmail(null);
                jugador.setContrasena(null);
            }

            if(crear == true){
                jugadorFacadeLocal.create(jugador);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador creado", "Jugador creado con éxito!"));
            }
        
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no creado", "Error al crear el jugador."));
        }
    }

    public List<Equipo> listaEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public String generarCorreo(){
        String correo = this.nombre.replaceAll(" ", "") + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com";
        correo = correo.toLowerCase();
        String correoFinal = Normalizer.normalize(correo, Normalizer.Form.NFD)
        .replaceAll("\\p{M}", "");
        return correoFinal;
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
