/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.AdministradorFacadeLocal;
import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class indexController implements Serializable {

    // Controlar si el usuario introducido es: administrador, arbitro o jugador 
    // y redirigir a la página correspondiente
    private String usuario;
    private String password;
    private String tipoUsuario;

    @EJB
    private AdministradorFacadeLocal administradorFacade;

    @EJB
    private ArbitroFacadeLocal arbitroFacade;

    @EJB
    private JugadorFacadeLocal jugadorFacade;

    @PostConstruct
    public void init() {
        tipoUsuario = "";
    }

    public String verificarUsuario() {
        init();

        if(administradorFacade.buscarUsuario(usuario, password) != null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "admin");
            tipoUsuario = "privado/administrador/vistaAdministrador.xhtml?faces-redirect=true";
            System.out.println("administrador");    
        }
        else if(arbitroFacade.buscarUsuario(usuario, password) != null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "arbitro");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idArbitro", arbitroFacade.buscarUsuario(usuario, password).getIdArbitro());
            tipoUsuario = "privado/arbitro/vistaArbitro.xhtml?faces-redirect=true";
            System.out.println("Arbitro");
        }
        else if(jugadorFacade.buscarUsuario(usuario, password) != null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "jugador");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idUsuario", jugadorFacade.buscarUsuario(usuario, password).getIdJugador());
            tipoUsuario = "privado/jugador/vistaJugador.xhtml?faces-redirect=true";
            System.out.println("Jugador");
        }
        else{
            tipoUsuario = "error";
            System.out.println("Error");
        }

        return tipoUsuario;

    }

    public void cerrarSesion() throws Exception{
        // Lógica para cerrar la sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        // Redirige a la página de inicio de sesión
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        String url = contextPath + "/faces/index.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
