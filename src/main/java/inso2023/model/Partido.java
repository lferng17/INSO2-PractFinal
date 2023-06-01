package inso2023.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="partido")
public class Partido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartido;
    
    @ManyToOne
    @JoinColumn(name="idEquipoLocal")
    private Equipo idEquipoLocal;

    @ManyToOne
    @JoinColumn(name="idEquipoVis")
    private Equipo idEquipoVis;

    @ManyToOne
    @JoinColumn(name="idArbitro")
    private Arbitro idArbitro;

    @Column(name="fecha")
    private String fecha;

    @Column(name="hora")
    private String hora;
    
    @Column(name="golesLocal")
    private Integer golesLocal;
    
    @Column(name="golesVis")
    private Integer golesVis;
    
    @Column(name="tarjAma")
    private Integer tarjAma;
    
    @Column(name="tarjRojas")
    private Integer tarjRojas;

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Equipo getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Equipo idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public Equipo getIdEquipoVis() {
        return idEquipoVis;
    }

    public void setIdEquipoVis(Equipo idEquipoVis) {
        this.idEquipoVis = idEquipoVis;
    }

    public Arbitro getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(Arbitro idArbitro) {
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

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVis() {
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