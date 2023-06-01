package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import java.util.List;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.PartidoFacadeLocal;
import inso2023.model.Arbitro;
import inso2023.model.Equipo;
import inso2023.model.Partido;
import javax.faces.context.FacesContext;

@ManagedBean
public class VistaEditarPartidoController {
    private int idPartidoMod;
    private int idEquipoLocal;
    private int idEquipoVis;
    private int idArbitro;
    private String fecha;
    private String hora;

    @EJB
    PartidoFacadeLocal partidoFacadeLocal;
    @EJB
    EquipoFacadeLocal equipoFacadeLocal;
    @EJB
    ArbitroFacadeLocal arbitroFacadeLocal;

    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void editarPartido(){
        try{
            Partido partido = new Partido();
            partido.setIdPartido(this.idPartidoMod);
            partido.setIdEquipoLocal(equipoFacadeLocal.find(this.idEquipoLocal));
            partido.setIdEquipoVis(equipoFacadeLocal.find(this.idEquipoVis));
            partido.setIdArbitro(arbitroFacadeLocal.find(this.idArbitro));
            partido.setFecha(this.fecha);
            partido.setHora(this.hora);
            partido.setGolesLocal(0);
            partido.setGolesVis(0);
            partido.setTarjAma(0);
            partido.setTarjRojas(0);
    
            partidoFacadeLocal.edit(partido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partido editado", "Partido editado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Partido no editado", "Error al editar el partido, compruebe los datos introducidos."));
        }

    }

    public void setDatosPartido(){
        Partido partido = partidoFacadeLocal.find(this.idPartidoMod);
        this.idEquipoLocal = partido.getIdEquipoLocal().getIdEquipo();
        this.idEquipoVis = partido.getIdEquipoVis().getIdEquipo();
        this.idArbitro = partido.getIdArbitro().getIdArbitro();
        this.fecha = partido.getFecha();
        this.hora = partido.getHora();
    }

    public List<Partido> getPartidos(){
        return partidoFacadeLocal.findAll();
    }

    public List<Equipo> getEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public List<Arbitro> getArbitros(){
        return arbitroFacadeLocal.findAll();
    }

    public int getIdPartidoMod() {
        return idPartidoMod;
    }

    public void setIdPartidoMod(int idPartidoMod) {
        this.idPartidoMod = idPartidoMod;
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getIdEquipoVis() {
        return idEquipoVis;
    }

    public void setIdEquipoVis(int idEquipoVis) {
        this.idEquipoVis = idEquipoVis;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
