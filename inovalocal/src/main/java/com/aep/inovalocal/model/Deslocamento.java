package com.aep.inovalocal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deslocamento")
public class Deslocamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoTransporte tipo;

    private double distancia;

    @Column(name = "pontos_ganhos")
    private int pontosGanhos;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro = LocalDateTime.now();

    public enum TipoTransporte {
        CAMINHADA, BICICLETA, ONIBUS
    }

    public Deslocamento() {}

    public Deslocamento(Usuario usuario, TipoTransporte tipo, double distancia, int pontosGanhos) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.distancia = distancia;
        this.pontosGanhos = pontosGanhos;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getPontosGanhos() {
        return pontosGanhos;
    }

    public void setPontosGanhos(int pontosGanhos) {
        this.pontosGanhos = pontosGanhos;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
