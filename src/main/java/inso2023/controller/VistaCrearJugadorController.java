package inso2023.controller;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

import inso2023.ejb.EquipoFacade;
import inso2023.model.Equipo;
import inso2023.model.Jugador;

@ManagedBean
public class VistaCrearJugadorController {
    
    public void verificarAdministrador() throws Exception{
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != "admin"){
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../publico/sinAcceso.xhtml");
        }
    }

    public void crearJugador(){
        Jugador jugador = new Jugador();
        jugador.setNombre("Luis");
        jugador.setApellidos("Fernandez");
        jugador.setDorsal(0);
        jugador.setFechaNac(null);
        jugador.setGoles(0);
        jugador.setAsistencias(0);
        jugador.setTarjAma(0);
        jugador.setTarjRojas(0);
        jugador.setIdEquipo(null);
        jugador.setCapitan(0);
        jugador.setEmail("");
        jugador.setContrasena("1234");

        System.out.println("Jugador creado");
    }

    public List<Equipo> listaEquipos(){
        EquipoFacade equipoFacade = new EquipoFacade();
        return equipoFacade.findAll();
    }
}
