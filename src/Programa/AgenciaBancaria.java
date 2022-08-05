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

        int operacao = Integer.parseInt(JOptionPane.showInputDialog("\n--- Selecione uma operação ---" +
                "\n|   Opção 1 - Criar conta" +
                "\n|   Opção 2 - Depositar" +
                "\n|   Opção 3 - Sacar" +
                "\n|   Opção 4 - Transferir" +
                "\n|   Opção 5 - Listar" +
                "\n|   Opção 6 - Sair"));

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
                System.exit(0);

            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
                operacoes();
                break;

        }

    }

    public static void criarConta() {

        Pessoa pessoa = new Pessoa();

        pessoa.setNome(JOptionPane.showInputDialog("Nome:"));
        pessoa.setCpf(JOptionPane.showInputDialog("CPF:"));
        pessoa.setEmail(JOptionPane.showInputDialog("Email:"));

        Conta conta = new Conta(pessoa);

        contasBancarias.add(conta);
        JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
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

        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(" Número da conta para depósito:"));

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog(" Qual valor deseja depositar? "));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Valor depositado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Conta nao encontrada");
        }

        operacoes();
    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(" Número da conta para saque:"));

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            Double valorSaque = Double.parseDouble(JOptionPane.showInputDialog(" Qual valor deseja sacar? "));
            conta.sacar(valorSaque);
            JOptionPane.showMessageDialog(null, "Valor sacado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Conta nao encontrada");
        }

        operacoes();
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog(" Numero da conta do remetente: "));

        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            int numeroContaDestinatario = Integer
                    .parseInt(JOptionPane.showInputDialog(" Numero da conta do destinatario: "));

            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                Double valor = Double.parseDouble(JOptionPane.showInputDialog(" Valor da transferencia: "));

                contaRemetente.transferir(contaDestinatario, valor);
                JOptionPane.showMessageDialog(null, "Valor transferido com sucesso");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta nao encontrada");

        }

        operacoes();
    }

    public static void listar() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                JOptionPane.showMessageDialog(null, conta);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas");
        }

        operacoes();
    }
}
