/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Arbitro;
import inso2023.model.Partido;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author luifer
 */
@Stateless
public class PartidoFacade extends AbstractFacade<Partido> implements PartidoFacadeLocal {

    @PersistenceContext(unitName = "ulescorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidoFacade() {
        super(Partido.class);
    }

    public List<Partido> findPartidoByArbitro(Arbitro arbitro) {
        TypedQuery<Partido> query = em.createQuery("SELECT p FROM Partido p WHERE p.idArbitro = :idArbitro", Partido.class);
        query.setParameter("idArbitro", arbitro);
        List<Partido> partidos = query.getResultList();
        return partidos;
    }
    
}
