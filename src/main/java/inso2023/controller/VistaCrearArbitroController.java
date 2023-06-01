package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.model.Arbitro;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import java.util.Date;

@ManagedBean
public class VistaCrearArbitroController {
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private int licencia;
    private String dni;

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

    public void crearArbitro(){
        try{
            Arbitro arbitro = new Arbitro();
            arbitro.setNombre(this.nombre);
            arbitro.setApellidos(this.apellidos);
            arbitro.setFechaNac(this.fechaNac);
            arbitro.setLicencia(this.licencia);
            arbitro.setDni(this.dni);
            arbitro.setEmail(this.nombre + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com");
            arbitro.setContrasena(this.dni);
    
            arbitroFacadeLocal.create(arbitro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro creado", "Arbitro creado con éxito!"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Arbitro no creado", "Error al crear el jugador."));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(int licencia) {
        this.licencia = licencia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ArbitroFacadeLocal getArbitroFacadeLocal() {
        return arbitroFacadeLocal;
    }

    public void setArbitroFacadeLocal(ArbitroFacadeLocal arbitroFacadeLocal) {
        this.arbitroFacadeLocal = arbitroFacadeLocal;
    }

}
