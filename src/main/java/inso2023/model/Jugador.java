package inso2023.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJugador;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "dni")
    private String dni;
    
    @Column(name = "dorsal")
    private int dorsal;
    
    @Column(name = "fechanac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    
    @Column(name = "goles")
    private int goles;
    
    @Column(name = "asistencias")
    private int asistencias;
    
    @Column(name = "tarjAma")
    private int tarjAma;
    
    @Column(name = "tarjRojas")
    private int tarjRojas;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEquipo")
    private Equipo idEquipo;
    
    @Column(name = "capitan")
    private int capitan;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "contrasena")
    private String contrasena;

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getTarjAma() {
        return tarjAma;
    }

    public void setTarjAma(int tarjAma) {
        this.tarjAma = tarjAma;
    }

    public int getTarjRojas() {
        return tarjRojas;
    }

    public void setTarjRojas(int tarjRojas) {
        this.tarjRojas = tarjRojas;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getCapitan() {
        return capitan;
    }

    public void setCapitan(int capitan) {
        this.capitan = capitan;
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

    public void anotarGol(int goles) {
        this.goles += goles;
    }

    public void anotarAsistencia(int asistencias) {
        this.asistencias += asistencias;
    }

    public void anotarTarjetaAmarilla(int tarjAma) {
        this.tarjAma += tarjAma;
    }

    public void anotarTarjetaRoja(int tarjRojas) {
        this.tarjRojas += tarjRojas;
    }

    
}