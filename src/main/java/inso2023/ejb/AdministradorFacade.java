/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Administrador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query; 

/**
 *
 * @author luifer, jgomea02
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "ulescorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }

    @Override
    public Administrador buscarUsuario(String usuario, String password) {
        Administrador admin = null;
        try {
            Query query = em.createQuery("SELECT a FROM Administrador a WHERE a.usuario = :usuario AND a.contrasena = :password");
            query.setParameter("usuario", usuario);
            query.setParameter("password", password);
            admin = (Administrador) query.getSingleResult();
            return admin;
        } catch (Exception e) {
        }
        return null;
    }
    
}
