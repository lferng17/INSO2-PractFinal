/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Partido;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class PartidoController implements Serializable {

    @EJB
    private PartidoFacadeLocal partidoEjb;
    private Partido partido;

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @PostConstruct
    public void init() {
        partido = new Partido();
    }

    public void guardar() {
        try {
            partidoEjb.create(partido);
        } catch (Exception e) {

        }
    }
}
