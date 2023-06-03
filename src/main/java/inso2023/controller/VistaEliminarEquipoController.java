package inso2023.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.JugadorFacadeLocal;
import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Equipo;
import inso2023.model.Jugador;
import inso2023.model.Partido;

@ManagedBean
@ViewScoped
public class VistaEliminarEquipoController implements Serializable{
    private int idEquipo;
    private List<Equipo> listaEquipos;
    @EJB
    private EquipoFacadeLocal equipoEJB;
    @EJB
    private JugadorFacadeLocal jugadorEJB;
    @EJB
    private PartidoFacadeLocal partidoEJB;


    @PostConstruct
    public void init(){
        listaEquipos = equipoEJB.findAll();
    }

    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void eliminarEquipo() throws IOException{
        try{
            Equipo equipo = equipoEJB.find(idEquipo);
            List<Jugador> listaJugadores = jugadorEJB.findAll();
            List<Partido> listaPartidos = partidoEJB.findAll();
            for(Partido partido : listaPartidos){
                if(partido.getIdEquipoLocal().getIdEquipo() == equipo.getIdEquipo() || partido.getIdEquipoVis().getIdEquipo() == equipo.getIdEquipo()){
                    partidoEJB.remove(partido);
                }
            }
            for(Jugador jugador : listaJugadores){
                if(jugador.getIdEquipo().getIdEquipo() == equipo.getIdEquipo()){
                    jugadorEJB.remove(jugador);
                }
            }
            equipoEJB.remove(equipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo eliminado", "Equipo eliminado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Equipo no eliminado", "Error al eliminar equipo, compruebe los datos introducidos."));
        }

    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

}
