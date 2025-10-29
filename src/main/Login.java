package main;

import java.util.Objects;

public class Login {
    public String usuario, senha, confirmaSenha, nome, resposta;

    public String realizarLogin(String usuario, String senha) {

        if (usuario.isEmpty() || senha.isEmpty()) {
            resposta = "Os campos devem ser preenchidos.";
        } else {
            if (Objects.equals(usuario, "Amanda") & Objects.equals(senha, "123")) {
                resposta = "Login realizado com sucesso!";
            } else resposta = "usuário ou senha incorretos.";
        }

        return resposta;
    }

    public String cadastrarUsuario(String nome, String usuario, String senha, String confirmaSenha) {

        if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            resposta = "Os campos devem ser preenchidos.";
        } else {
            if (Objects.equals(senha, confirmaSenha)) {
                resposta = "usuário cadastrado com sucesso!";
            } else resposta = "As senhas não conferem.";
        }

        return resposta;
    }

}
