package inso2023.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="partido")
public class Partido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartido;
    @Column(name="idEquipoLocal")
    private int idEquipoLocal;
    @Column(name="idEquipoVis")
    private int idEquipoVis;
    @Column(name="idArbitro")
    private int idArbitro;
    @Column(name="fecha")
    private String fecha;
    @Column(name="hora")
    private String hora;
    @Column(name="golesLocal")
    private int golesLocal;
    @Column(name="golesVis")
    private int golesVis;
    @Column(name="tarjAma")
    private int tarjAma;
    @Column(name="tarjRojas")
    private int tarjRojas;

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
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

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVis() {
        return golesVis;
    }

    public void setGolesVis(int golesVis) {
        this.golesVis = golesVis;
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

    
}