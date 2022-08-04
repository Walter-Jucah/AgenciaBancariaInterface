package Programa;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AgenciaBancaria {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {

        int operacao = Integer.parseInt(JOptionPane.showInputDialog(
                "--- Selecione uma operação ---" +
                        "|   Opção 1 - Criar conta" +
                        "|   Opção 2 - Depositar" +
                        "|   Opção 3 - Sacar" +
                        "|   Opção 4 - Transferir" +
                        "|   Opção 5 - Listar" +
                        "|   Opção 6 - Sair"));

        switch (operacao) {

            case 1:
                criarConta();
                break;

            case 2:
                depositar();
                break;

            case 3:
                sacar();
                break;

            case 4:
                transferir();
                break;

            case 5:
                listar();
                break;

            case 6:
                JOptionPane.showMessageDialog(null, "Obrigado por usar nossa agencia");
                System.out.println("Obrigado por usar nossa agencia");
                System.exit(0);

            default:
                System.out.println("Opcao invalida");
                operacoes();
                break;

        }

    }

    public static void criarConta() {

        System.out.println("\nNome: ");
        String nome = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();

        System.out.println("\nEmail: ");
        String email = input.next();

        Pessoa pessoa = new Pessoa(nome, cpf, email);

        Conta conta = new Conta(pessoa);

        contasBancarias.add(conta);
        System.out.println("Conta criada com sucesso!");

        operacoes();
    }

    public static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta c : contasBancarias) {
                if (c.getNumeroConta() == numeroConta) {
                    conta = c;
                }
            }

        }

        return conta;
    }

    public static void depositar() {
        System.out.println("Numero da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja depositar?");
            Double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
            System.out.println("Valor depositado com sucesso");
        } else {
            System.out.println("Conta nao encontrada");
        }

        operacoes();
    }

    public static void sacar() {
        System.out.println("Numero da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja sacar?");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
            System.out.println("Valor sacado com sucesso");
        } else {
            System.out.println("Conta nao encontrada");
        }

        operacoes();
    }

    public static void transferir() {
        System.out.println("Numero da conta do remetente: ");
        int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            System.out.println("Numero da conta do destinatario: ");
            int numeroContaDestinatario = input.nextInt();

            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                System.out.println("Valor da transferencia: ");
                Double valor = input.nextDouble();

                contaRemetente.transferir(contaDestinatario, valor);
                System.out.println("Valor transferido com sucesso");

            }
        }

        operacoes();
    }

    public static void listar() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("Não há contas cadastradas");
        }

        operacoes();
    }
}
