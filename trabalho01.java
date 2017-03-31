import java.lang.System;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class trabalho01{
    
    
    //Exibe todos os nomes que estão na lista
    public static void Exibir_nome(ArrayList<String> lista){
    	String line = null;	//Referencia uma linha por vez
    	
    	//Percorre a lista imprimindo o nome em cada posição
    	for(int i = 0; i < lista.size(); i++)
    		System.out.println(line);

    }
    
    public static void Inserir_nome(ArrayList<String> lista){
    	String nome = null;
    	Scanner teclado = new Scanner(System.in);
    	System.out.println("Entre com um nome: ");
    	nome = teclado.nextLine();
		lista.add(nome);
    }
    
    //Grava os nomes da lista no arquivo
    public static void Gravar_lista(ArrayList<String> lista, int n){
    	int tam = lista.size();
    	try{
	    	FileWriter arq = new FileWriter("pessoas.txt");
			BufferedWriter gravarArq = new BufferedWriter(arq);
	    	for (int i=n+1; i<tam; i++){
	    		 gravarArq.write(lista[n]);
	    		 n++;
	    	}
	    	gravarArq.close();
    	}
    	 catch(IOException ex) {
	    	 System.out.println( "Erro ao escrever no arquivo");
	    	 return n;
	    }
    }
    
    
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);	//lê a entrada do teclado
        ArrayList<String> lista = new ArrayList();	//Vetor dinâmico que irá receber a lista do arquivo de texto
        int op = 9;	//variavel usada para escolher as ações do switch
        int n = 0;	//usado para marcar a posição do ultimo elemento da lista
		
		do {
			//Menu
		    System.out.println("Escolha uma operacao:");
		    System.out.println("    0 - Carregar a lista de nomes de pessoas");
		    System.out.println("    1 - Inserir um nome de pessoa na lista");
		    System.out.println("    2 - Exibir os nomes das pessoas da lista");
		    System.out.println("    3 - Salvar a lista de nomes de pessoas");
		    System.out.println("    9 - Fim");
		    
		    //Decide a ação a ser seguida
		    switch (op = scanner.nextInt() ) {
		        case 0:
		        	//Verifica se a lista já foi carregada antes
		        	if(lista.isEmpty() == false){
		        		char confirm = null;
		        		do {
		        			System.out.println("Uma lista já foi carregada e pode possuir modificações não salvas.");
		        			System.out.println("Deseja carregar uma nova lista (s/n)?");
		        			confirm = scanner.nextLine();
		        		} while( (confirm != s) && (confirm != S) && (confirm != n) && (confirm != N) );
		        	    if(confirm == "n")
		        	    	break;
		        	}
		        	
		        	//carrega o arquivo
		            FileReader arquivo = new FileReader("pessoas.txt");
		            BufferedReader lerArquivo = new BufferedReader(arquivo);
		            
		            String line = null;	//Referencia uma linha por vez
		            
		            //Preenche o vetor com os dados do arquivo
		            while( (line = lerArquivo.readLine() ) != null)
		            	lista.add(line);
		            
		            //marca a posição do ultimo item da lista
		            //size() retorna o numero total de elementos do vetor
		            n = lista.size() - 1;
		            
		            lerArquivo.close();	//Fecha o arquivo
		            break;
		        case 1:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()){
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            Inserir_nome(lista);
		            break;
		        case 2:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()){
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            Exibir_nome(lista);
		            break;
		        case 3:
		        	//Verifica se a lista foi carregada
		        	if (lista.isEmpty()){
		        		System.out.println("A lista ainda não foi carregada.");
		        		break;
		        	}
		        	
		            n = Gravar_lista(lista);
		            break;
		        case 9:
		            System.out.println("Fim");
		            break;
		        default:
		            System.out.println("Operacao invalida!");
		    }
		    
		} while(op != 9);
	
        
    }

    
}
