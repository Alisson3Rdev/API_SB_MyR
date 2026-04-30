package com.My_Rhythmus_Spring.Spring_MR.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) de resposta.
 * Essa classe define O QUE a API devolve ao cliente.
 * Propositalmente não tem o campo "senhaUser" — nunca devolvemos a senha,
 * nem criptografada, em uma resposta de API.
 */
public class UsuarioDTO {

    private Long idUser;
    private String nomeUser;
    private String apelidoUser;
    private String emailUser;
    private LocalDate nascimentoUser;
    private String phoneUser;

    // Construtor vazio — necessário para o Jackson (biblioteca que converte Java ↔ JSON)
    public UsuarioDTO() {}

    // Construtor com todos os campos — usado no Service para montar a resposta rapidamente
    public UsuarioDTO(Long idUser, String nomeUser, String apelidoUser,
                      String emailUser, LocalDate nascimentoUser, String phoneUser) {
        this.idUser = idUser;
        this.nomeUser = nomeUser;
        this.apelidoUser = apelidoUser;
        this.emailUser = emailUser;
        this.nascimentoUser = nascimentoUser;
        this.phoneUser = phoneUser;
    }

    // Getters e Setters — necessários para o Jackson ler e escrever os campos
    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getNomeUser() { return nomeUser; }
    public void setNomeUser(String nomeUser) { this.nomeUser = nomeUser; }

    public String getApelidoUser() { return apelidoUser; }
    public void setApelidoUser(String apelidoUser) { this.apelidoUser = apelidoUser; }

    public String getEmailUser() { return emailUser; }
    public void setEmailUser(String emailUser) { this.emailUser = emailUser; }

    public LocalDate getNascimentoUser() { return nascimentoUser; }
    public void setNascimentoUser(LocalDate nascimentoUser) { this.nascimentoUser = nascimentoUser; }

    public String getPhoneUser() { return phoneUser; }
    public void setPhoneUser(String phoneUser) { this.phoneUser = phoneUser; }
}