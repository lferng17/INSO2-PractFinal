/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.MensajeFacadeLocal;
import inso2023.model.Mensaje;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class MensajeController implements Serializable {

    @EJB
    private MensajeFacadeLocal mensajeEjb;
    private Mensaje mensaje;

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    @PostConstruct
    public void init() {
        mensaje = new Mensaje();
    }

    public void guardar() {
        try {
            mensajeEjb.create(mensaje);
        } catch (Exception e) {

        }
    }
}
