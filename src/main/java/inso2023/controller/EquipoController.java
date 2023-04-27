/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.model.Equipo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class EquipoController implements Serializable {

    @EJB
    private EquipoFacadeLocal equipoEjb;
    private Equipo equipo;

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @PostConstruct
    public void init() {
        equipo = new Equipo();
    }

    public void guardar() {
        try {
            equipoEjb.create(equipo);
        } catch (Exception e) {

        }
    }
}
