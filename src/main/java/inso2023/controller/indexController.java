/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.AdministradorFacadeLocal;
import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.model.Administrador;
import inso2023.model.Arbitro;
import inso2023.model.Jugador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
        System.out.println(usuario);
        System.out.println(password);
        tipoUsuario = "";
    }

    public String verificarUsuario() {
        init();

        if(administradorFacade.buscarUsuario(usuario, password) != null){
            tipoUsuario = "administrador";
            System.out.println("Administrador");    
        }
        else if(arbitroFacade.buscarUsuario(usuario, password) != null){
            tipoUsuario = "privado/arbitro/vistaArbitro.xhtml?faces-redirect=true";
            System.out.println("Arbitro");
        }
        else if(jugadorFacade.buscarUsuario(usuario, password) != null){
            tipoUsuario = "jugador";
            System.out.println("Jugador");
        }
        else{
            tipoUsuario = "error";
            System.out.println("Error");
        }

        return tipoUsuario;

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
