package inso2023.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import inso2023.ejb.JugadorFacadeLocal;
import inso2023.ejb.MensajeFacadeLocal;
import inso2023.model.Jugador;
import inso2023.model.Mensaje;

@ManagedBean
@ViewScoped
public class VistaCapitanController implements Serializable {

    private String mensaje;
    private Mensaje objMensaje;

    @EJB
    private MensajeFacadeLocal mensajeEJB;

    @EJB
    private JugadorFacadeLocal jugadorEJB;

    public void mandarMensaje() {
        objMensaje = new Mensaje();
        objMensaje.setMensaje(mensaje);
        int idJugador = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUsuario");
        Jugador jugador = jugadorEJB.find(idJugador);
        objMensaje.setIdJugador(jugador);
        try {
            mensajeEJB.create(objMensaje);
        } catch (Exception e) {
            System.out.println("Error al crear mensaje");
            e.printStackTrace();
        }
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../index.xhtml");
        }catch(Exception e){
            System.out.println("Error al redirigir");
            e.printStackTrace();
        }
    }

    public void verificarCapitan() throws Exception {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != "jugador") {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        } else {
            System.out.println("Vista Capitan");
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    

}
