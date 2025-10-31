package main;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Login login = new Login();
        Cliente cliente = new Cliente();
        Scanner ler = new Scanner(System.in);

        String tipoUsuario, usuario, senha, confirmaSenha, nome, nomeProfissional;
        String[] resposta;

        int opcao, opcaoCliente, idProfissional, horarioProfissional;
        boolean buscarProfissional, cadastrarUsuario, rodarSistema;

        rodarSistema = true;

        while(rodarSistema) {

            System.out.println("Escolha uma opção:");
            System.out.println("1-Realizar login / 2-Cadastrar usuário / 3-Fechar");
            opcao = ler.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Usuário: ");
                    usuario = ler.next();
                    System.out.println("Senha: ");
                    senha = ler.next();

                    resposta = login.realizarLogin(usuario, senha);
                    System.out.println(resposta[0]);

                    if (resposta[1].equals("CLIENTE")) {

                        buscarProfissional = true;

                        while (buscarProfissional) {

                            System.out.println("Digite o nome do profissional: ");
                            nomeProfissional = ler.next();

                            resposta = cliente.buscarProfissional(nomeProfissional);

                            //Profissional encontrado
                            if (resposta[0] != null) {

                                //Escolhe horário
                                System.out.println(resposta[0]);
                                idProfissional = ler.nextInt();

                                if (idProfissional != 0) {
                                    resposta = cliente.selecionarProfissional(idProfissional);
                                    System.out.println(resposta[0]);
                                    horarioProfissional = ler.nextInt();

                                    if (horarioProfissional != 0) {

                                        resposta = cliente.agendarConsulta(idProfissional, horarioProfissional);
                                        System.out.println(resposta[0]);

                                        System.out.println("0-Voltar para login / 1-Visualizar consultas cadastradas / 2-Cadastrar outra consulta");
                                        opcaoCliente = ler.nextInt();

                                        switch (opcaoCliente) {
                                            case 1:
                                                buscarProfissional = false;
                                                break;

                                            case 2:
                                                // Lógica para visualizar consultas
                                                break;

                                            default:
                                                System.out.println("Opção inválida!");
                                                break;
                                        }

                                    }

                                }
                            } else System.out.println("Profissional não encontrado...");
                            nomeProfissional = "";
                        }
                    }

                    break;

                case 2:
                    cadastrarUsuario = true;

                    while (cadastrarUsuario) {
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

                        if (resposta[2].equals("CADASTRADO")) {
                            cadastrarUsuario = false;
                        }
                    }

                    break;

                case 3:
                    System.out.println("Fechando...");
                    rodarSistema = false;

                    break;
            }
        }

    }
}