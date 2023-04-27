/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Arbitro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luifer
 */
@Local
public interface ArbitroFacadeLocal {

    void create(Arbitro arbitro);

    void edit(Arbitro arbitro);

    void remove(Arbitro arbitro);

    Arbitro find(Object id);

    List<Arbitro> findAll();

    List<Arbitro> findRange(int[] range);

    int count();
    
}
