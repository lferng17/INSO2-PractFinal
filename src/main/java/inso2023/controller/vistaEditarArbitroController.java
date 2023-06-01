package inso2023.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import inso2023.ejb.ArbitroFacadeLocal;
import inso2023.model.Arbitro;

import java.util.Date;
import java.util.List;

@ManagedBean
public class vistaEditarArbitroController {
    private int idArbitroMod;
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private int licencia;
    private String dni;
    private String email;
    private String contrasena;

    @EJB
    ArbitroFacadeLocal arbitroFacadeLocal;

    public void editarArbitro(){
        Arbitro arbitro = new Arbitro();
        arbitro.setIdArbitro(this.idArbitroMod);
        arbitro.setNombre(this.nombre);
        arbitro.setApellidos(this.apellidos);
        arbitro.setFechaNac(this.fechaNac);
        arbitro.setLicencia(this.licencia);
        arbitro.setDni(this.dni);
        arbitro.setEmail(this.email);
        arbitro.setContrasena(this.contrasena);

        arbitroFacadeLocal.edit(arbitro);
        System.out.println("Arbitro editado");
    }

    public void setDatosArbitro(){
        Arbitro arbitro = arbitroFacadeLocal.find(this.idArbitroMod);
        this.nombre = arbitro.getNombre();
        this.apellidos = arbitro.getApellidos();
        this.fechaNac = arbitro.getFechaNac();
        this.licencia = arbitro.getLicencia();
        this.dni = arbitro.getDni();
        this.email = arbitro.getEmail();
        this.contrasena = arbitro.getContrasena();
    }

    public List<Arbitro> getArbitros(){
        return arbitroFacadeLocal.findAll();
    }

    public int getIdArbitroMod() {
        return idArbitroMod;
    }

    public void setIdArbitroMod(int idArbitroMod){
        this.idArbitroMod = idArbitroMod;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArbitroFacadeLocal getArbitroFacadeLocal() {
        return arbitroFacadeLocal;
    }

    public void setArbitroFacadeLocal(ArbitroFacadeLocal arbitroFacadeLocal) {
        this.arbitroFacadeLocal = arbitroFacadeLocal;
    }

}