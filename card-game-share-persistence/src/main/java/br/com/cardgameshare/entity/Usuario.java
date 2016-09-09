package br.com.cardgameshare.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "usuario")
public class Usuario extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "data_ultimo_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoLogin;

    @Column(name = "bloqueado", nullable = false)
    private Boolean bloqueado;

    @Column(name = "senha", length = 32, nullable = false)
    private String senha;

    @OneToMany
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<CartasUsuarios> cartas;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(Date dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public List<CartasUsuarios> getCartas() {
        return cartas;
    }

    public void setCartas(List<CartasUsuarios> cartas) {
        this.cartas = cartas;
    }
}
