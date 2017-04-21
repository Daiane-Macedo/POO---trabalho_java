import java.lang.Character;
import java.lang.String;
import java.lang.System;
import java.util.Scanner;
import java.util.Random;

//Classe do cliente com o número e se ele é prioritário
    class Cliente {
        static private int num_cliente;
        private boolean prioritario;
        private double valor_posse;
        
        
        public boolean getPrioritario() {
            return prioritario;
        }
    
        public void setPrioritario(boolean prioritario) {
            this.prioritario = prioritario;
        }
        
         public int getNumero() {
            return num_cliente;
        }
        
        public double getPosse() {
            return valor_posse;
        }
        
        public void setValorPosse(double valor_posse) {
            this.valor_posse = valor_posse;
        }
        
        //método construtor
        public  Cliente(int num_cliente, boolean prioritario, double valor_posse) {
            this.num_cliente = num_cliente;
            this.prioritario = prioritario;
            this.valor_posse = valor_posse;
            //Conta conta = new Conta (cliente.getNumero(), );
        }
    }
    
// Classe conta     
    class Conta{
        private double saldo;
        private int numero_cliente;

        public double getSaldo() {
            return saldo;
        }

        public double setSaldo() {
            this.saldo = saldo;
        }
        //método construtor
        public  Conta(int numero_cliente, double saldo) {
            this.numero_cliente = num_cliente;
            this.saldo = saldo;
        }
    }
    
    /*----------------Main---------------------------------------------------*/
    public class trabalho02{
        
        public static void main(String[] args){
            Scanner teclado = new Scanner(System.in);
            Random random = new Random();
            int max = 0;
            
            ArrayList<Cliente> fila_geral = new ArrayList();
            ArrayList<Cliente> fila_preferencial = new ArrayList();
            ArrayList<Conta> lista_contas = new ArrayList();
            
            System.out.println("Quantos clientes deseja?");
            max = teclado.nextInt();

            for (int i = 1; max; i++) {
                Cliente cliente = new Cliente(i, random.nextBoolean, random.nextDouble * 1000);
                
                if (cliente.getPrioritario)
                    fila_preferencial.add(cliente);
                else
                    fila_geral.add(cliente);
                    
                Conta conta = new Conta(i, random.);
                lista_contas.add(conta);
            } 
        }
}
