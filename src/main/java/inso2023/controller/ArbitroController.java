/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.model.Arbitro;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class ArbitroController implements Serializable {

    @EJB
    private ArbitroFacadeLocal arbitroEjb;
    private Arbitro arbitro;

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    @PostConstruct
    public void init() {
        arbitro = new Arbitro();
    }

    public void guardar() {
        try {
            arbitroEjb.create(arbitro);
        } catch (Exception e) {

        }
    }
}
