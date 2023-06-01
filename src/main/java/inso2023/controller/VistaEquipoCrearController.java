package inso2023.controller;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
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
        System.out.println(usuario);
        if(!usuario.equals("admin")){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            String url = contextPath + "/faces/index.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
    }


    public void crearEquipo(){
        Equipo equipo = new Equipo();
        equipo.setNombre(this.nombre);
        equipo.setPuntos(0);
        equipo.setGolesFav(0);
        equipo.setGolesContra(0);
        equipoFacadeLocal.create(equipo);
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }   
    public String getNombre(){
        return this.nombre;
    }   

}
