package inso2023.controller;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import inso2023.ejb.EquipoFacadeLocal;
import inso2023.model.Equipo;


@ManagedBean
public class VistaEquipoCrearController {
    public String nombre;

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


    public void crearEquipo(){
        try{
            Equipo equipo = new Equipo();
            equipo.setNombre(this.nombre);
            equipo.setPuntos(0);
            equipo.setGolesFav(0);
            equipo.setGolesContra(0);
            equipoFacadeLocal.create(equipo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo creado", "Equipo creado con éxito!"));    
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Equipo no creado", "Error al crear el equipo."));
        }
            
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }   
    public String getNombre(){
        return this.nombre;
    }   

}
