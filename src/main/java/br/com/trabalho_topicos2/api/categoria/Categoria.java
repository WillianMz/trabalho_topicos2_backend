package br.com.trabalho_topicos2.api.categoria;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias", schema="public")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column()
    private String url_img;

    public Categoria() {
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getUrl_img() {
        return url_img;
    }
    //
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
    //
    @Override
    public String toString() {
        return nome;
    }
}
