/*
 *	TRABALHO 01:	Carregar uma lista de nomes a partir de um arquivo "pessoas.txt".
 *					Exibir os nomes, adicionar novos e salvar no arquivo.
 *
 *	DATA:			31 de março de 2017
 *
 *	ALUNOS:			Daiane Macedo
 *					João Gabriel C. Melo
 *		
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;


public class trabalho01{
    
    //Exibe todos os nomes que estão no vetor
    public static void Exibir_nome(ArrayList<String> lista) {
    	
    	//Percorre a lista imprimindo o nome em cada posição
    	for (int i = 0; i < lista.size(); i++)
    		System.out.println(lista.get(i));
    		
    }
    
    //Lê um novo nome adiciona ao fim do vetor
    public static void Ler_nome(ArrayList<String> lista) {
    	String nome = null;	
    	Scanner teclado = new Scanner(System.in);
    	
    	//Lê o nome fornecido e armazena em uma variável
    	System.out.println("Entre com um nome: ");
    	nome = teclado.nextLine();
    	
    	//Verifica se o nome fornecido é composto somente de espaços ou é nulo
    	if ( nome.trim().isEmpty() ) {
    		System.out.println("Entrada invalida!");	//Caso seja, exibe mensagem de erro
    	} else {
			lista.add(nome);	//Se não, adiciona o nome ao fim do vetor
    	}
    	
    }
    
    //Grava os nomes da lista no arquivo
    public static int Gravar_lista(ArrayList<String> lista, int n) {
    	int tam = lista.size();	//Recebe o numero de elementos do vetor
    	
    	try {
    		//Abre o arquivo e prepara para a escrita
	    	FileWriter arq = new FileWriter("pessoas.txt");
			BufferedWriter gravarArq = new BufferedWriter(arq);
			
			//Grava as novas entradas no arquivo
	    	for (int i = n + 1; i < tam; i++) {
	    		gravarArq.newLine();
	    		gravarArq.write(lista.get(n));
	    		n++;	//atualiza a posição da ultima entrada presente no arquivo
	    	}
	    	//Fecha o arquivo
	    	gravarArq.close();
    	} catch (IOException ex) {
	    	 System.out.println( "Erro ao escrever no arquivo pessoas.txt");
	    }
	    
    	 return n;	//Retorna a posição,, no vetor, da ultima entrada presente no arquivo
    	 
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);	//lê a entrada do teclado
        ArrayList<String> lista = new ArrayList();	//Vetor dinâmico que irá receber a lista do arquivo de texto
        int op = 9;	//variavel usada para escolher as ações do switch
        int n = 0;	//usado para marcar a posição do ultimo elemento da lista
		
		//Reppete o menu até que o usuário decida encerrar
		do {
			//Menu
		    System.out.println("Escolha uma operacao:");
		    System.out.println("    0 - Carregar a lista de nomes de pessoas");
		    System.out.println("    1 - Inserir um nome de pessoa na lista");
		    System.out.println("    2 - Exibir os nomes das pessoas da lista");
		    System.out.println("    3 - Salvar a lista de nomes de pessoas");
		    System.out.println("    9 - Fim");
		    
		    //Decide a ação a ser seguida
		    switch (op = scanner.nextInt()) {
		        case 0:
		        	//Verifica se a lista já foi carregada antes
		        	if (!lista.isEmpty()) {
		        		int confirm = 0;
		        		while( (confirm != 1) && (confirm != 2) ) {
		        			System.out.println("Uma lista já foi carregada e pode possuir modificações não salvas.");
		        			System.out.println("Deseja carregar uma nova lista");
		        			System.out.println("	1 - Sim		2 - Não");
		        			confirm = scanner.nextInt();
		        		}
		        	    if (confirm == 2)
		        	    	break;
		        	    	
		        	}
		        	
		        	try {
			        	//carrega o arquivo e prepara para a leitura
			            FileReader arquivo = new FileReader("pessoas.txt");
			            BufferedReader lerArquivo = new BufferedReader(arquivo);
			            
			            String line = null;	//Referencia uma linha por vez
			            
			            //Garante que não existe nada no vetor antes de começar a ser usado
			            lista.clear();
			            //Preenche o vetor com os dados do arquivo
			            while ( (line = lerArquivo.readLine() ) != null)
			            	lista.add(line);
			            
			            /*
			             * marca a posição do ultimo item da lista
			             * size() retorna o numero total de elementos do vetor
			             */
			            n = lista.size() - 1;
			            
			            lerArquivo.close();	//Fecha o arquivo
			            
					} catch (FileNotFoundException ex) {
						System.out.println("Não foi possivel abrir o arquivo 'pessoas.txt'.");
					} catch(IOException ex) {
						System.out.println("Erro ao ler o arquivo 'pessoas.txt'.");
					}
					break;
		            
		        case 1:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()) {
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            Ler_nome(lista);
		            break;
		            
		        case 2:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()) {
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            Exibir_nome(lista);
		            break;
		            
		        case 3:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()) {
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            n = Gravar_lista(lista, n);
		            break;
		            
		        case 9:
		            System.out.println("Fim");
		            break;
		            
		        default:
		            System.out.println("Operacao invalida!");
		    
		    	
		    }
		    
		} while (op != 9);
	
    }

}
