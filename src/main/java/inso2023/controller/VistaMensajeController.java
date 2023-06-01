package inso2023.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.ejb.MensajeFacadeLocal;
import inso2023.model.Equipo;
import inso2023.model.Jugador;
import inso2023.model.Mensaje;

@ManagedBean
public class VistaMensajeController implements Serializable{
    List<MensajeTabla> listaMensajesTabla = new java.util.ArrayList<MensajeTabla>();
    List<Mensaje> listaMensajes;

    @EJB
    private MensajeFacadeLocal mensajeEJB;
    @EJB
    private EquipoFacadeLocal equipoEJB;
    @EJB
    private JugadorFacadeLocal jugadorEJB;

    @PostConstruct
    public void init(){
        listaMensajes = mensajeEJB.findAll();
        for(Mensaje mensaje : listaMensajes){
            listaMensajesTabla.add(new MensajeTabla(mensajeEJB.find(mensaje.getIdMensaje())));
        }
    }

    public void verificarAdministrador() throws Exception {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")
                    .equals("admin")) {
                String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                String url = contextPath + "/faces/index.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            }
        } catch (Exception e) {
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }

    }
    

    public List<MensajeTabla> getListaMensajesTabla() {
        return listaMensajesTabla;
    }

    public void setListaMensajesTabla(List<MensajeTabla> listaMensajesTabla) {
        this.listaMensajesTabla = listaMensajesTabla;
    }

    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }



    public class MensajeTabla{
        String mensaje;
        String nombreEquipo;
        String nombreJugador;

        public MensajeTabla(Mensaje mensaje) {
            this.mensaje = mensaje.getMensaje();
            Jugador jugador = jugadorEJB.find(mensaje.getIdJugador().getIdJugador());
            Equipo equipo = equipoEJB.find(jugador.getIdEquipo().getIdEquipo());
            this.nombreEquipo = equipo.getNombre();
            this.nombreJugador = jugador.getNombre() + " " + jugador.getApellidos();
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getNombreEquipo() {
            return nombreEquipo;
        }

        public void setNombreEquipo(String nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
        }

        public String getNombreJugador() {
            return nombreJugador;
        }

        public void setNombreJugador(String nombreJugador) {
            this.nombreJugador = nombreJugador;
        }

        
    }
    
}
