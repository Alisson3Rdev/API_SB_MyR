package com.My_Rhythmus_Spring.Spring_MR.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbuser")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "nome_user", nullable = false, length = 150)
    private String nomeUser;

    @Column(name = "apelido_user", nullable = false, length = 50)
    private String apelidoUser;

    @Column(name = "email_user", unique = true, nullable = false, length = 150)
    private String emailUser;

    @Column(name = "senha_user", nullable = false, length = 255)
    private String senhaUser;

    @Column(name = "nascimento_user", nullable = false)
    private java.time.LocalDate nascimentoUser;

    @Column(name = "phone_user", nullable = false, length = 20)
    private String phoneUser;

    // Getters e Setters
    public Long getIdUser() {
        return idUser; }
    public void setIdUser(Long idUser) {
        this.idUser = idUser; }

    public String getNomeUser() {
        return nomeUser; }
    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getApelidoUser() {
        return apelidoUser; }
    public void setApelidoUser(String apelidoUser) {
        this.apelidoUser = apelidoUser; }


    public String getEmailUser() {
        return emailUser; }
    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser; }


    public String getSenhaUser() {
        return senhaUser; }
    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser; }


    public java.time.LocalDate getNascimentoUser() {
        return nascimentoUser; }
    public void setNascimentoUser(java.time.LocalDate nascimentoUser) {
        this.nascimentoUser = nascimentoUser; }


    public String getPhoneUser() {
        return phoneUser; }
    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser; }
}