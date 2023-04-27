/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.controller;

import inso2023.ejb.AdministradorFacadeLocal;
import inso2023.model.Administrador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class AdministradorController implements Serializable {

    @EJB
    private AdministradorFacadeLocal administradorEjb;
    private Administrador administrador;

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @PostConstruct
    public void init() {
        administrador = new Administrador();
    }

    public void guardar() {
        try {
            administradorEjb.create(administrador);
        } catch (Exception e) {

        }
    }
}
