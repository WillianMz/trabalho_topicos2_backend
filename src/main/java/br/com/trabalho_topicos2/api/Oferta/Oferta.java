package br.com.trabalho_topicos2.api.Oferta;

import br.com.trabalho_topicos2.api.empresa.Empresa;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ofertas", schema="public")
public class Oferta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String dtInicio;
    @Column(nullable = false)
    private String dtFim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPRESAID", nullable = false, referencedColumnName = "ID")
    private Empresa empresa;
    @Column()
    private String detalhes;
    @Column()
    private String informacoes;
    @Column()
    private String url_img;
    @Column(nullable = false)
    private boolean destaque;
    //
    public Oferta() {
    }
    //
    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getDtInicio() {
        return dtInicio;
    }
    public String getDtFim() {
        return dtFim;
    }
    public Empresa getEmpresa() {
        return empresa;
    }
    public String getDetalhes() {
        return detalhes;
    }
    public String getInformacoes() {
        return informacoes;
    }
    public String getUrl_img() {
        return url_img;
    }
    public boolean isDestaque() {
        return destaque;
    }
    //
    public void setId(int id) {
        this.id = id;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }
    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }
    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }
    //
@Override
    public String toString() {
        return descricao;
    }
}
