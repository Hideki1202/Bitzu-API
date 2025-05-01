package com.bitzu.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Table
@Entity(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String telefone;
    private String email;
    private String senha;
    @Column(columnDefinition = "text", nullable = true)
    private String link = "https://drcdjxojoyrftwrhsolr.supabase.co/storage/v1/object/sign/imagesbucket/default_profile_icon.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5X2JmOThkODRiLTZkM2MtNDkwZS04MDZmLTJmNTI5ZjY3MDdlMCJ9.eyJ1cmwiOiJpbWFnZXNidWNrZXQvZGVmYXVsdF9wcm9maWxlX2ljb24ucG5nIiwiaWF0IjoxNzQ2MDA5MzE0LCJleHAiOjE3Nzc1NDUzMTR9.6Yv4lb00NAtqANx7EKjSMIMHT71zUBb63N5IGL7JA0g";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public User() {

    }

    public User(Date dataNascimento, String senha, String email, String telefone, String username, Long id) {
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.username = username;
        this.id = id;
    }

    public User(String username, String telefone, String email, String senha, Date dataNascimento) {
        this.username = username;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public User(Long id, String username, String telefone, String email, String senha, String link, Date dataNascimento) {
        this.id = id;
        this.username = username;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.link = link;
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
