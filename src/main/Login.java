package main;

import java.util.Objects;

public class Login {
    public String tipoUsuario, usuario, senha, confirmaSenha, nome;
    String[] resposta = new String[5];

    public String[] realizarLogin(String usuario, String senha) {
        resposta[1] = "";

        if (usuario.isEmpty() || senha.isEmpty()) {
            resposta[0] = "Os campos devem ser preenchidos.";
        } else {
            if (usuario.equals("Amanda") & senha.equals("123")) {
                resposta[0] = "Login realizado com sucesso!";
                resposta[1] = "CLIENTE";
            } else {
                resposta[0] = "usuário ou senha incorretos.";
            }
        }

        return resposta;
    }

    public String[] cadastrarUsuario(String tipoUsuario, String nome, String usuario, String senha, String confirmaSenha) {
        resposta[3] = "";

        if (tipoUsuario.isEmpty() || nome.isEmpty() || usuario.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            resposta[0] = "Os campos devem ser preenchidos.";
        } else {
            if (tipoUsuario.equals("CLIENTE") || tipoUsuario.equals("ADM")) {
                if (senha.equals(confirmaSenha)) {
                    resposta[0] = "Usuário cadastrado com sucesso!";
                    resposta[2] = "CADASTRADO";
                } else resposta[0] = "As senhas não conferem.";
            } else {
                resposta[0] = "O tipo de usuário é inválido. Escolha CLIENTE ou ADM.";
            }

        }

        return resposta;
    }

}
