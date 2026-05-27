# 🎵 My Rhythmus API — Spring MR

API REST desenvolvida em **Spring Boot** para o projeto **My Rhythmus**, com autenticação via **JWT** e banco de dados **PostgreSQL**.

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.5 |
| PostgreSQL | — |
| Spring Security | — |
| JWT (jjwt) | 0.12.6 |
| Lombok | — |
| Bean Validation | — |
| Google API Client | — |

---

## 📁 Estrutura do Projeto

```
com.My_Rhythmus_Spring.Spring_MR/
├── model/
│   └── Usuario.java
├── dto/
│   ├── UsuarioDTO.java
│   └── UsuarioRequestDTO.java
├── repository/
│   └── UsuarioRepository.java
├── service/
│   └── UsuarioService.java
├── controller/
│   ├── UsuarioController.java
│   └── AuthController.java
└── security/
    ├── JwtUtil.java
    ├── JwtFilter.java
    ├── SecurityConfig.java
    └── SecurityUtils.java
```

---

## ▶️ Como rodar

1. Abra o projeto no **IntelliJ IDEA**
2. Confirme que o **PostgreSQL** está rodando e configurado no `application.properties`
3. Execute o `SpringMrApplication.java`
4. Aguarde a mensagem `Tomcat started on port 8080` no console
5. Use o **Postman** para testar os endpoints

---

## 🔐 Autenticação

A API usa **JWT (JSON Web Token)**. Para acessar rotas protegidas, você precisa primeiro fazer login e incluir o token no header de cada requisição:

```
Authorization: Bearer <seu_token_aqui>
```

---

## 📌 Endpoints

### Auth

| Método | Rota | Token | Descrição |
|---|---|---|---|
| POST | `/api/auth/login` | ❌ | Realiza login e retorna o JWT |

**Body do login:**
```json
{
  "email": "seu@email.com",
  "senha": "suasenha"
}
```

---

### Usuários

| Método | Rota | Token | Descrição |
|---|---|---|---|
| POST | `/api/usuarios/cadastrar` | ❌ | Cria um novo usuário |
| GET | `/api/usuarios` | ✅ | Lista todos os usuários |
| GET | `/api/usuarios/{id}` | ✅ | Busca usuário por ID (só o dono) |
| PUT | `/api/usuarios/{id}` | ✅ | Atualiza usuário (só o dono) |
| DELETE | `/api/usuarios/{id}` | ✅ | Deleta usuário (só o dono) |

**Body do cadastro/atualização:**
```json
{
  "nomeUser": "Alisson",
  "apelidoUser": "Alisoo",
  "emailUser": "alisson@email.com",
  "senhaUser": "minimo6",
  "nascimentoUser": "2000-01-01",
  "phoneUser": "49999999999"
}
```

---

## ✅ Validações

Os campos do cadastro e atualização são validados automaticamente:

| Campo | Regra |
|---|---|
| `nomeUser` | Obrigatório, entre 2 e 100 caracteres |
| `apelidoUser` | Opcional, máximo 50 caracteres |
| `emailUser` | Obrigatório, formato válido de email |
| `senhaUser` | Obrigatória, mínimo 6 caracteres |
| `nascimentoUser` | Deve ser uma data no passado |
| `phoneUser` | Opcional, entre 10 e 15 dígitos |

Campos inválidos retornam **HTTP 400 Bad Request**.

---

## 🔒 Autorização

Rotas protegidas verificam se o token JWT pertence ao dono do recurso. Um usuário **não pode** acessar, editar ou deletar dados de outro usuário.

---

## 🗺️ Roadmap

- [x] Cadastro de usuários
- [x] Login com JWT
- [x] Rotas protegidas
- [x] Autorização por dono
- [x] Validação de campos
- [ ] Tratamento de erros global
- [ ] Refresh Token
- [ ] Roles / Permissões
- [ ] Login com Google

---

## 👨‍💻 Autor

**Alisson Eliabi Figueira**  
Projeto: My Rhythmus  
Ambiente: Linux Fedora + IntelliJ IDEA
