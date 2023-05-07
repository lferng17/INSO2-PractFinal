/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inso2023.ejb;

import inso2023.model.Jugador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luifer
 */
@Local
public interface JugadorFacadeLocal {

    void create(Jugador jugador);

    void edit(Jugador jugador);

    void remove(Jugador jugador);

    Jugador find(Object id);

    List<Jugador> findAll();

    List<Jugador> findRange(int[] range);

    int count();
    
    Jugador buscarUsuario(String usuario, String password); 
    
}
