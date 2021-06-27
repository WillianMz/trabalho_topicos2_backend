package br.com.trabalho_topicos2.api.empresa;

import br.com.trabalho_topicos2.api.categoria.Categoria;
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
@Table(name = "empresas", schema="public")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String fantasia;
    @Column()
    private String sobre;
    @Column()
    private String endereco;
    @Column(nullable = false)
    private String telefone;
    @Column()
    private String whatsapp;
    @Column()
    private String facebook;
    @Column()
    private String instagram;
    @Column(columnDefinition = "TEXT")
    private String url_logo;
    @Column(columnDefinition = "TEXT")
    private String url_capa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORIAID", nullable = false, referencedColumnName = "ID")
    private Categoria categoria;

    //horario
    @Column()
    private String dom;
    @Column()
    private String seg;
    @Column()
    private String ter;
    @Column()
    private String qua;
    @Column()
    private String qui;
    @Column()
    private String sex;
    @Column()
    private String sab;
    @Column()
    private boolean destaque;

    public Empresa() {}

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getFantasia() {
        return fantasia;
    }
    public String getSobre() {
        return sobre;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getWhatsapp() {
        return whatsapp;
    }
    public String getFacebook() {
        return facebook;
    }
    public String getInstagram() {
        return instagram;
    }
    public String getUrl_logo() {
        return url_logo;
    }
    public String getUrl_capa() {
        return url_capa;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public String getDom() {
        return dom;
    }
    public String getSeg() {
        return seg;
    }
    public String getTer() {
        return ter;
    }
    public String getQua() {
        return qua;
    }
    public String getQui() {
        return qui;
    }
    public String getSex() {
        return sex;
    }
    public String getSab() {
        return sab;
    }
    public boolean isDestaque() {
        return destaque;
    }
    //
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }
    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }
    public void setUrl_capa(String url_capa) {
        this.url_capa = url_capa;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setDom(String dom) {
        this.dom = dom;
    }
    public void setSeg(String seg) {
        this.seg = seg;
    }
    public void setTer(String ter) {
        this.ter = ter;
    }
    public void setQua(String qua) {
        this.qua = qua;
    }
    public void setQui(String qui) {
        this.qui = qui;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setSab(String sab) {
        this.sab = sab;
    }
    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }
}
