package com.My_Rhythmus_Spring.Spring_MR.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nomeUser;

    @Size(max = 50, message = "O apelido deve ter no máximo 50 caracteres")
    private String apelidoUser;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String emailUser;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senhaUser;

    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate nascimentoUser;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Telefone inválido")
    private String phoneUser;

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