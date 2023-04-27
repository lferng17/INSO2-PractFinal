/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Arbitro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
