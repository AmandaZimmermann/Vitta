package main;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Login login = new Login();
        Cliente cliente = new Cliente();
        Scanner ler = new Scanner(System.in);

        String tipoUsuario, usuario, senha, confirmaSenha, nome;
        String[] resposta = new String[2];
        int opcao, idProfissional, horarioProfissional;
        boolean buscarProfissional;

        System.out.println("Escolha uma opção:");
        System.out.println("1-Realizar login / 2-Cadastrar usuário");
        opcao = ler.nextInt();

        if (opcao == 1) {
            System.out.println("Usuário: ");
            usuario = ler.next();
            System.out.println("Senha: ");
            senha = ler.next();

            resposta = login.realizarLogin(usuario,senha);
            System.out.println(resposta[0]);

            if (resposta[1].equals("CLIENTE")) {

                buscarProfissional = true;

                while (buscarProfissional) {
                    resposta = cliente.buscarProfissional("L");

                    System.out.println(resposta[0]);
                    idProfissional = ler.nextInt();

                    if (idProfissional != 0) {
                        resposta = cliente.selecionarProfissional(idProfissional);
                        System.out.println(resposta[0]);
                        horarioProfissional = ler.nextInt();

                        if (horarioProfissional != 0) {

                            resposta = cliente.agendarConsulta(idProfissional, horarioProfissional);
                            System.out.println(resposta[0]);
                        } else buscarProfissional = false;

                    } else buscarProfissional = false;
                }
            }

        } else if (opcao == 2) {
            System.out.println("Escolha o tipo de usuário(CLIENTE / ADM): ");
            tipoUsuario = ler.next();
            System.out.println("Digite seu nome completo: ");
            nome = ler.next();
            System.out.println("Cadastre um suário: ");
            usuario = ler.next();
            System.out.println("Cadastre uma senha: ");
            senha = ler.next();
            System.out.println("Confirme sua senha: ");
            confirmaSenha = ler.next();

            resposta = login.cadastrarUsuario(tipoUsuario, nome, usuario, senha, confirmaSenha);
            System.out.println(resposta[0]);
        }

    }
}