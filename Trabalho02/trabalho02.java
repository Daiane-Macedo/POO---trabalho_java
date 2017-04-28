/*
 *	TRABALHO 01: Simulador de caixa de banco. O banco possui duas filas (geral e priotitária) e dois caixas (geral e prioritário).
 * Caso a fila prioritária tenha mais de 3 clientes, o caixa geral atenderá clientes prioritários.
 *
 *	DATA:			28 de abril de 2017
 *
 *	ALUNOS:			Daiane S. Macedo
 *					João Gabriel C. Melo
 *
 */


import java.lang.Character;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//Classe do cliente com o número e se ele é prioritário
class Cliente {
    private int num_cliente;
    final private boolean prioritario;
    private double valor_posse;
    
    //Informa se é prioritário
    public boolean getPrioritario() {
        return prioritario;
    }
    
    //Informa numero de identificação do cliente
    public int getNumero() {
        return num_cliente;
    }
    
    //Informa valor que o cliente possui
    public double getPosse() {
        return valor_posse;
    }
    
    //Atualiza valor que o cliente possui
    private void setValorPosse(double valor_posse) {
        this.valor_posse = valor_posse;
    }
    
    //Aumenta a quantia que o cliente possui
    public void somaValor(double valor) {
        this.setValorPosse(valor_posse + valor);
    }
    
    //Diminui a quantia que o cliente possui
    public void subtraiValor(double valor) {
        this.setValorPosse(valor_posse - valor);
    }
    
    //método construtor
    public  Cliente(int num_cliente, boolean prioritario, double valor_posse) {
        this.num_cliente = num_cliente;
        this.prioritario = prioritario;
        this.valor_posse = valor_posse;
        
    }
}
    
// Classe conta     
class Conta{
    private double saldo;
    final int numero_cliente;
    
    //Informa quantia na conta
    public double getSaldo() {
        return saldo;
    }
    
    //Atualiza quantia na conta
    private void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    //Deposita na conta
    public void depositar(double valor_dep) {
        this.setSaldo(valor_dep + saldo);
    }
    
    //Saca quantia da conta
    public void sacar(double valor_saque) {
        this.setSaldo(saldo - valor_saque);
    }
    
    //método construtor
    public  Conta(int numero_cliente, double saldo) {
        this.numero_cliente = numero_cliente;
        this.saldo = saldo;
    }
}

//Classe caixa
class Caixa{
    private int num_caixa;
    private Cliente cliente;
    private Conta conta_cliente;
    
    //Chama próximo cliente e busca a conta correspondente
    public void proxAtendimento(Cliente cliente, ArrayList<Conta> lista_contas) {
        int i = 0;
        
        this.cliente = cliente;
        
        //Percorre a lista de contas até encontrar a correta ou a lista acabar
        while (lista_contas.get(i).numero_cliente !=  cliente.getNumero() && i < lista_contas.size()) {
            i ++;
        }
        
        //Verifica o motivo de ter saído do while
        if (lista_contas.get(i).numero_cliente != cliente.getNumero()) {
            System.out.println("Cliente não possui conta");
        } else {
            conta_cliente = lista_contas.get(i);
        }
        
        dadosCliete();   
    }
    
    
    //Faz um deposito na conta do cliente
    public void depositoNaConta(double valor) {
        
        if (cliente.getPosse() < valor) {
            System.out.println("Cliente não possui dinheiro suficiente.");
            cliente.getPosse();
        } else {
            cliente.subtraiValor(valor);
            conta_cliente.depositar(valor);
            consultarSaldo();

        }
        
    }
    
    //Faz um saque na conta do cliente
    public void sacarDaConta(double valor) {

        if (conta_cliente.getSaldo() < valor) {
            System.out.println("Saldo insuficiente.");
        } else {
            conta_cliente.sacar(valor);
            cliente.somaValor(valor);
            System.out.println("Saque efetuado.");

        }
        
        consultarSaldo();
        
    }
    
    //Consulta saldo do cliente
    public void consultarSaldo() {
        System.out.printf("Saldo atual: %.2f\n", conta_cliente.getSaldo());
    }
    
    private void dadosCliete() {
        System.out.println("Caixa: " + num_caixa);
        if (num_caixa == 1) {
            System.out.println("Atendimento prioritario");
        } else {
            System.out.println("Atendimento geral");
        }
        System.out.println("Cliente numero: " + cliente.getNumero());
        if (cliente.getPrioritario()) {
            System.out.println("Cliente prioritario");
        }
        System.out.printf("Valor em posse do cliente: %.2f\n", cliente.getPosse());
    }
    
    //Construtor
    public Caixa(int num){
        this.num_caixa = num;
    }
}





/*----------------Main---------------------------------------------------*/
public class trabalho02{
    
    //Exibe o Menu com as opções de operações
    public static void exibeMenu (){
        System.out.println ("Escolha a operação que deseja efetuar");
        System.out.println ("1 - Consultar saldo");
        System.out.println ("2 - Depositar");
        System.out.println ("3 - Sacar");
        System.out.println ("4 - Sair");
    }
    
    // Mostra o menu ao iniciar o atendimento. Cliente pode encerrar o atendimento.
    public static void iniciaAtendimento(Caixa caixa){
        int opcao; //opção da operação escolhida
        Scanner teclado = new Scanner(System.in);
    
        do{    
            exibeMenu();
            opcao = teclado.nextInt();
            executaOperacao(opcao, caixa);
        }while(opcao!=4);
    }
    
    //executa  a operação a partir da opção escolhida
    public static void executaOperacao(int opcao, Caixa caixa){
        Scanner teclado = new Scanner(System.in);
        double valor;
        
        switch(opcao){
            case 1:
                caixa.consultarSaldo();
                break;
    
            case 2:
                System.out.println ("Quanto deseja depositar?");
                valor = teclado.nextDouble();
                caixa.depositoNaConta(valor);
                break;
    
            case 3:
                caixa.consultarSaldo();
                System.out.println ("Quanto deseja sacar?");
                valor = teclado.nextDouble();
                caixa.sacarDaConta(valor);
                break;
            case 4:
                System.out.println("Atendimento encerrado");
                break;
            default:
                System.out.println("Opção inválida");
                break; 
        }
    }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random random = new Random();
        int max = 0;
        
        ArrayList<Cliente> fila_geral = new ArrayList();    //cria a fila geral
        ArrayList<Cliente> fila_preferencial = new ArrayList(); //cria fila preferencial
        ArrayList<Conta> lista_contas = new ArrayList();    //cria a lista com as contas dos clientes
        
        Caixa atendimento_prioritario = new Caixa(1); //cria caixa prioritário
        Caixa atendimento_geral = new Caixa(2); // cria caixa de atendimento geral
        
        System.out.println("Quantos clientes deseja?");
        max = teclado.nextInt();
        
        //cria clientes e os coloca nas filas adequadas
        for (int i = 1; i <= max; i++) {
            Cliente cliente = new Cliente(i, random.nextBoolean(), random.nextDouble() * 1000);
            
            if (cliente.getPrioritario())
                fila_preferencial.add(cliente);
            else
                fila_geral.add(cliente);
                
            lista_contas.add(new Conta (i, random.nextDouble() * 10000));
            
        }
        /*funcionamento das filas: atendendo os clientes e os removendo das respectivas filas */
        while(!(fila_geral.isEmpty() && fila_preferencial.isEmpty())) {
            
            if (!fila_preferencial.isEmpty()) {
                atendimento_prioritario.proxAtendimento(fila_preferencial.get(0), lista_contas);
                iniciaAtendimento(atendimento_prioritario);
                fila_preferencial.remove(0);
            
            }
            // regra: fila preferencial com mais de 3 clientes
            if (!fila_preferencial.isEmpty() && fila_preferencial.size() > 3) {
                atendimento_geral.proxAtendimento(fila_preferencial.get(0), lista_contas);
                iniciaAtendimento(atendimento_geral);
                fila_preferencial.remove(0);
             
            } else if (!fila_geral.isEmpty()) {
                atendimento_geral.proxAtendimento(fila_geral.get(0), lista_contas);
                iniciaAtendimento(atendimento_geral);
                fila_geral.remove(0);
            
            }
    
        }
        
    } 
    
}
