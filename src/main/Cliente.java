package main;

import java.util.Arrays;

public class Cliente {
    public String profissional, profissionalBanco = "L";
    String[] resposta = new String[5];
    int idProfissional, idProfissionalBanco = 1, horarioProfissional;

    public String[] buscarProfissional(String profissional) {

        if (profissional.isEmpty()) {
            resposta[0] = "O campo deve ser preenchido.";
        } else {
            if (profissionalBanco.startsWith(profissional)) {
                resposta[0] = "0-LIMPAR CAMPO / 1-Leo / 2-Leonar / 3-Leo ";
            }
        }

        return resposta;
    }

    public String[] selecionarProfissional(int idProfissional) {

        if (idProfissional == idProfissionalBanco) {
            resposta[0] = "0-VOLTAR / 1-10:30 / 2-15:00 / 3-18:15";
        }

        return resposta;
    }

    public String[] agendarConsulta(int idProfissional, int horarioProfissional) {

       resposta[0] = "Consulta agendada para o dia ... com o profissional ...";

       return resposta;
    }
}
