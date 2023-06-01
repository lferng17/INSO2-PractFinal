package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
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
    private int idJugador;

    @EJB
    private MensajeFacadeLocal mensajeEJB;

    @EJB
    private JugadorFacadeLocal jugadorEJB;

    @PostConstruct
    public void init() {
        try {
            idJugador = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idJugador");
        } catch (Exception e) {
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";         
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("Error al redirigir");
            }
        }
    }

    public void mandarMensaje() {
        objMensaje = new Mensaje();
        objMensaje.setMensaje(mensaje);

        Jugador jugador = jugadorEJB.find(idJugador);
        objMensaje.setIdJugador(jugador);
        try {
            mensajeEJB.create(objMensaje);
        } catch (Exception e) {
            System.out.println("Error al crear mensaje");
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
