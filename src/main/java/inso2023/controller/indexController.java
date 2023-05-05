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
    private List<Administrador> listaAdmin;
    private List<Arbitro> listaArbitro;
    private List<Jugador> listaJugador;
    private List<String> listaUsuarios;
    private List<String> listaPassword;

    @EJB
    private AdministradorFacadeLocal administradorFacade;

    @EJB
    private ArbitroFacadeLocal arbitroFacade;

    @EJB
    private JugadorFacadeLocal jugadorFacade;

    @PostConstruct
    public void init() {
        listaUsuarios = new ArrayList<>();
        listaPassword = new ArrayList<>();

        listaAdmin = administradorFacade.findAll();
        listaArbitro = arbitroFacade.findAll();
        listaJugador = jugadorFacade.findAll();

        for (int i = 0; i < listaAdmin.size(); i++) {
            listaUsuarios.add(listaAdmin.get(i).getUsuario());
            listaPassword.add(listaAdmin.get(i).getContrasena());
        }

        for (int i = 0; i < listaArbitro.size(); i++) {
            listaUsuarios.add(listaArbitro.get(i).getEmail());
            listaPassword.add(listaArbitro.get(i).getContrasena());
        }

        for (int i = 0; i < listaJugador.size(); i++) {
            listaUsuarios.add(listaJugador.get(i).getEmail());
            listaPassword.add(listaJugador.get(i).getContrasena());
        }
    }

    public String verificarUsuario() {

        init();
        System.out.println(usuario);
        System.out.println(password);

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (usuario.equals(listaUsuarios.get(i)) && password.equals(listaPassword.get(i))) {
                if (i < listaAdmin.size()) {
                    tipoUsuario = "administrador";
                    System.out.println("administradooor");
                } else if (i < listaAdmin.size() + listaArbitro.size()) {
                    tipoUsuario = "arbitro";
                } else {
                    tipoUsuario = "jugador";
                }
            }
        }
        if (tipoUsuario == null) {
            tipoUsuario = "error";
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
