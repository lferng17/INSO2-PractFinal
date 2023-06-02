package inso2023.controller;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.model.Arbitro;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ManagedBean
public class VistaCrearArbitroController {
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private int licencia;
    private String dni;
    private boolean crear;

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
            crear = true;
            arbitro.setNombre(this.nombre);
            arbitro.setApellidos(this.apellidos);

            LocalDate fechaActual = LocalDate.now();
            String fechaPartido = this.fechaNac.toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaPartido, formatter);
            if(fechaActual.isBefore(fecha)){
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Arbitro no creado", "La fecha de nacimiento no es válida."));
            }else{
                arbitro.setFechaNac(this.fechaNac);
            }
            arbitro.setLicencia(this.licencia);

            if(this.dni.matches("[0-9]{8}[A-HJ-NP-TV-Za-hj-np-tv-z]")){
                arbitro.setDni(this.dni);
            }else{
                crear = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Arbitro no creado", "El DNI no es válido."));
            }

            arbitro.setEmail(generarCorreo());
            arbitro.setContrasena(this.dni.toLowerCase());

            if(crear==true){
                arbitroFacadeLocal.create(arbitro);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro creado", "Arbitro creado con éxito!"));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Arbitro no creado", "Arbitro no creado, compruebe los datos introducidos."));
        }
    }

    public String generarCorreo(){
        String correo = this.nombre.replaceAll(" ", "") + "." + this.apellidos.replaceAll(" ", "") + "@ulescore.com";
        correo = correo.toLowerCase();
        String correoFinal = Normalizer.normalize(correo, Normalizer.Form.NFD)
        .replaceAll("\\p{M}", "");
        return correoFinal;
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
