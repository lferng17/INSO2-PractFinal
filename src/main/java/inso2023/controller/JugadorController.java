/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.JugadorFacadeLocal;
import inso2023.model.Jugador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class JugadorController implements Serializable {

    @EJB
    private JugadorFacadeLocal jugadorEjb;
    private Jugador jugador;

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @PostConstruct
    public void init() {
        jugador = new Jugador();
    }

    public void guardar() {
        try {
            jugadorEjb.create(jugador);
        } catch (Exception e) {

        }
    }
}
