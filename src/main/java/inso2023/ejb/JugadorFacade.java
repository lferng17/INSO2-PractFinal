/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Jugador;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luifer, jgomea02
 */
@Stateless
public class JugadorFacade extends AbstractFacade<Jugador> implements JugadorFacadeLocal {

    @PersistenceContext(unitName = "ulescorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JugadorFacade() {
        super(Jugador.class);
    }

    @Override
    public Jugador buscarUsuario(String usuario, String password) {
        Jugador jugador = null;
        try {
            Query query = em.createQuery("SELECT j FROM Jugador j WHERE j.email = :usuario AND j.contrasena = :password");
            query.setParameter("usuario", usuario);
            query.setParameter("password", password);
            jugador = (Jugador) query.getSingleResult();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", jugador);
            return jugador;
        } catch (Exception e) {
        }
        return null;
    }
    
}
