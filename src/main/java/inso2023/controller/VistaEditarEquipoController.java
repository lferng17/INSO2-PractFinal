package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import java.util.List;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.model.Equipo;
import javax.faces.context.FacesContext;

@ManagedBean
public class VistaEditarEquipoController {
    private int idEquipoMod;
    private String nombre;
    private int puntos;
    private int golesFav;
    private int golesContra;

    @EJB
    EquipoFacadeLocal equipoFacadeLocal;

    public void verificarAdministrador() throws Exception{
        String usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println(usuario);
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }

    public void editarEquipo(){
        try{
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(this.idEquipoMod);
            equipo.setNombre(this.nombre);
            equipo.setPuntos(this.puntos);
            equipo.setGolesFav(this.golesFav);
            equipo.setGolesContra(this.golesContra);
    
            equipoFacadeLocal.edit(equipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo editado", "Equipo editado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Equipo no editado.", "Error al editar el equipo, compruebe los datos introucidos."));
        }
    }

    public void setDatosEquipo(){
        Equipo equipo = equipoFacadeLocal.find(this.idEquipoMod);
        this.nombre = equipo.getNombre();
        this.puntos = equipo.getPuntos();
        this.golesFav = equipo.getGolesFav();
        this.golesContra = equipo.getGolesContra();
    }

    public List<Equipo> getEquipos(){
        return equipoFacadeLocal.findAll();
    }

    public int getIdEquipoMod() {
        return this.idEquipoMod;
    }

    public void setIdEquipoMod(int idEquipoMod) {
        this.idEquipoMod = idEquipoMod;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public void setPuntos(int puntos){
        this.puntos = puntos;
    }

    public int getGolesFav(){
        return this.golesFav;
    }

    public void setGolesFav(int golesFav){
        this.golesFav = golesFav;
    }

    public int getGolesContra(){
        return this.golesContra;
    }
    
    public void setGolesContra(int golesContra){
        this.golesContra = golesContra;
    }
}
