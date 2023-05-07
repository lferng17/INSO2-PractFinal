/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Arbitro;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luifer
 */
@Stateless
public class ArbitroFacade extends AbstractFacade<Arbitro> implements ArbitroFacadeLocal {

    @PersistenceContext(unitName = "ulescorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArbitroFacade() {
        super(Arbitro.class);
    }

    @Override
    public Arbitro buscarUsuario(String usuario, String password) {
        Arbitro arbitro = null;
        try {
            Query query = em.createQuery("SELECT a FROM Arbitro a WHERE a.email = :usuario AND a.contrasena = :password");
            query.setParameter("usuario", usuario);
            query.setParameter("password", password);
            arbitro = (Arbitro) query.getSingleResult();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", arbitro);
            return arbitro;
        } catch (Exception e) {
        }
        return null;
    }
    
}
