package com.My_Rhythmus_Spring.Spring_MR.dto;

import java.time.LocalDate;

/**
 * DTO de entrada — define O QUE o cliente precisa enviar no corpo da requisição.
 * É usado tanto no cadastro quanto na atualização de usuário.
 * Aqui sim temos a senha, porque o cliente precisa enviá-la para cadastrar ou alterar.
 */
public class UsuarioRequestDTO {

    private String nomeUser;
    private String apelidoUser;
    private String emailUser;
    private String senhaUser;       // Aqui vem a senha em texto puro — o Service que vai criptografar
    private LocalDate nascimentoUser;
    private String phoneUser;

    // Getters e Setters — o Jackson usa esses métodos para preencher os campos
    // com os valores que vieram no JSON da requisição
    public String getNomeUser() { return nomeUser; }
    public void setNomeUser(String nomeUser) { this.nomeUser = nomeUser; }

    public String getApelidoUser() { return apelidoUser; }
    public void setApelidoUser(String apelidoUser) { this.apelidoUser = apelidoUser; }

    public String getEmailUser() { return emailUser; }
    public void setEmailUser(String emailUser) { this.emailUser = emailUser; }

    public String getSenhaUser() { return senhaUser; }
    public void setSenhaUser(String senhaUser) { this.senhaUser = senhaUser; }

    public LocalDate getNascimentoUser() { return nascimentoUser; }
    public void setNascimentoUser(LocalDate nascimentoUser) { this.nascimentoUser = nascimentoUser; }

    public String getPhoneUser() { return phoneUser; }
    public void setPhoneUser(String phoneUser) { this.phoneUser = phoneUser; }
}