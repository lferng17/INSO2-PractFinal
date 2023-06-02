package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.List;
import java.text.Normalizer;
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
    private boolean editar;

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

    public void editarJugador(){
        try{
            Jugador jugador = new Jugador();
            editar = true;
            jugador.setIdJugador(this.idJugadorMod);
            jugador.setNombre(this.nombre);
            jugador.setApellidos(this.apellidos);

            if(this.dorsal > 0 && this.dorsal < 100){
                jugador.setDorsal(this.dorsal);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "El dorsal no es válido."));
            }

            if(this.dni.matches("[0-9]{8}[A-Za-z]")){
                jugador.setDni(this.dni);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "El DNI no es válido."));
            }

            jugador.setFechaNac(this.fechaNac);

            if(this.goles >= 0){
                jugador.setGoles(this.goles);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "Los goles no son válidos."));
            }

            if(this.asistencias >= 0){
                jugador.setAsistencias(this.asistencias);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "Las asistencias no son válidas."));
            }    

            if(this.tarjAma >= 0){
                jugador.setTarjAma(this.tarjAma);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "Las tarjetas amarillas no son válidas."));
            }

            if(this.tarjRojas >= 0){
                jugador.setTarjRojas(this.tarjRojas);
            }else{
                editar = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "Las tarjetas rojas no son válidas."));
            }
            
                jugador.setIdEquipo(equipoFacadeLocal.find(this.idEquipo));

            if(this.capitan == true){
                jugador.setCapitan(1);
                jugador.setEmail(generarCorreo());
                jugador.setContrasena(this.dni.toLowerCase());
            }else{
                jugador.setCapitan(0);
                jugador.setEmail(null);
                jugador.setContrasena(null);
            }
    
            if(editar==true){
                jugadorFacadeLocal.edit(jugador);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador editado", "Jugador editado correctamente!"));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jugador no editado", "El jugador no ha sido editado, compruebe los datos introducidos."));
        }   

    }

    public List<Equipo> getEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public String generarCorreo(){
        String correo = this.nombre.replaceAll(" ", "") + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com";
        correo = correo.toLowerCase();
        String correoFinal = Normalizer.normalize(correo, Normalizer.Form.NFD)
        .replaceAll("\\p{M}", "");
        return correoFinal;
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
