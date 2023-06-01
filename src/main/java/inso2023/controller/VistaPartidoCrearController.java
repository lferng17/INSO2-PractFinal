package inso2023.controller;


import javax.ejb.EJB;
import java.util.List;

import inso2023.model.Arbitro;
import inso2023.model.Equipo;
import inso2023.model.Partido;
import inso2023.ejb.PartidoFacadeLocal;
import inso2023.ejb.EquipoFacadeLocal;
import inso2023.ejb.ArbitroFacadeLocal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class VistaPartidoCrearController {
    public int idEquipoLocal;
    public int idEquipoVisitante;
    public int idArbitro;
    public String fecha;
    public String hora;

    @EJB
    PartidoFacadeLocal partidoFacadeLocal;
    @EJB
    ArbitroFacadeLocal arbitroFacadeLocal;
    @EJB
    EquipoFacadeLocal equipoFacadeLocal;


    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }



    public void crearPartido(){
        try{
            Partido partido = new Partido();
            partido.setIdEquipoLocal(equipoFacadeLocal.find(this.idEquipoLocal));
            partido.setIdEquipoVis(equipoFacadeLocal.find(this.idEquipoVisitante));
            partido.setIdArbitro(arbitroFacadeLocal.find(idArbitro));
            partido.setFecha(this.fecha);
            partido.setHora(this.hora);
            partidoFacadeLocal.create(partido);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partido creado", "Partido creado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Partido no creado", "El partido no ha sido creado. Compruebe los datos de nuevo."));
        }

    }

    public List<Equipo> getEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public List<Arbitro> getArbitros(){
        return arbitroFacadeLocal.findAll();
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(int idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
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

    public PartidoFacadeLocal getPartidoFacadeLocal() {
        return partidoFacadeLocal;
    }

    public void setPartidoFacadeLocal(PartidoFacadeLocal partidoFacadeLocal) {
        this.partidoFacadeLocal = partidoFacadeLocal;
    }

    public ArbitroFacadeLocal getArbitroFacadeLocal() {
        return arbitroFacadeLocal;
    }

    public void setArbitroFacadeLocal(ArbitroFacadeLocal arbitroFacadeLocal) {
        this.arbitroFacadeLocal = arbitroFacadeLocal;
    }

    public EquipoFacadeLocal getEquipoFacadeLocal() {
        return equipoFacadeLocal;
    }

    public void setEquipoFacadeLocal(EquipoFacadeLocal equipoFacadeLocal) {
        this.equipoFacadeLocal = equipoFacadeLocal;
    }
    
    
}
