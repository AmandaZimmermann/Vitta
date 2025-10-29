package main;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Login login = new Login();
        Scanner ler = new Scanner(System.in);
        int opcao;
        String usuario, senha, confirmaSenha, nome, resposta;

        System.out.println("Escolha uma opção:");
        System.out.print("1-Realizar login / 2-Cadastrar usuário");
        opcao = ler.nextInt();

        if (opcao == 1) {
            System.out.println("Usuário: ");
            usuario = ler.next();
            System.out.println("Senha: ");
            senha = ler.next();

            resposta = login.realizarLogin(usuario,senha);
            System.out.println(resposta);

        } else if (opcao == 2) {
            System.out.println("Digite seu nome completo: ");
            nome = ler.next();
            System.out.println("Cadastre um suário: ");
            usuario = ler.next();
            System.out.println("Cadastre uma senha: ");
            senha = ler.next();
            System.out.println("Confirme sua senha: ");
            confirmaSenha = ler.next();

            resposta = login.cadastrarUsuario(nome, usuario, senha, confirmaSenha);
            System.out.println(resposta);
        }

    }
}