package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import Control.AGenetico;
import Control.Conversores;


public class Dados {

	private int valor[]; //valor de cada item
	private int peso[];	// peso de cada item
	private int valoradc[][]; // Matriz que receberá a matriz de valores adicional
	private int numero_individuos = 6; //numeros de indivíduos a serem gerados
	private ArrayList<String> populacao; //Array de String que recebe um individuo	
	private ArrayList<Integer> srt = new ArrayList(); // Vetor de Inteiros para receber numeros sorteados para gerar um individuo.
	private int capacidade;
	
	
	
	
	Random rd = new Random();
	Conversores conv = new Conversores();
	//Path caminho =  Paths.get("C:/Users/chris/Desktop/UFV/Computação Evolutiva/DadosProjeto/dados.txt");
	
	
	public void lerDados() {
		String linha = new String();
		String nomearquivo = "C:\\3629_Christian_Rodrigues";
		File arq = new File(nomearquivo);
		
			if(arq.exists()){
			try {
				FileReader leitor = new FileReader(nomearquivo);
				BufferedReader buffer = new BufferedReader(leitor);
				
				linha = buffer.readLine();
				String capacidadeMochila = linha;
				capacidade = Integer.parseInt(capacidadeMochila);
				
				linha = buffer.readLine();		
				
				
				String linhavalor = linha.replace(",", "").trim();
				 valor = new int[linhavalor.length()];
				 
				 for (int i = 0; i < valor.length; i++) {
			            String aux = String.valueOf(linhavalor.charAt(i));
			            valor[i] = Integer.parseInt(aux);
			        }
				
				 linha = buffer.readLine();		
					
					
				String linhapeso = linha.replace(",", "").trim();
					 peso = new int[linhavalor.length()];
					 
					 for (int i = 0; i < valor.length; i++) {
				            String aux = String.valueOf(linhapeso.charAt(i));
				            peso[i] = Integer.parseInt(aux);
				        }
				
				
				
				
			}catch(Exception e){
				
			}
		}
		
	}
	
	
	
	
	
	/* funções para receber valores da instancia*/
	
public void populacaoInicial() {
		
		while(srt.size() < numero_individuos) {
			int sorteio = rd.nextInt(255);
            if (sorteio > 128) {
                srt.add(sorteio);
            }
		}
		
		populacao = new ArrayList<>();
		
		for (int s : srt) {
            populacao.add(conv.converteDecBin(s));
        }
		
		
    }

	
	public void exibePopulacao2() {
		for (int i = 0; i <  populacao.size(); i++) {
            System.out.println(populacao.get(i));
        }
    }
	
	
	AGenetico ag = new AGenetico(valor, peso, capacidade, populacao, valoradc);
	
	public void iniciarAlgoritmo() {

        populacaoInicial();
        int ger = 1;

        while (ger <= 100) {
        	
            ag.exibePopulacao(populacao);
            populacao = ag.GeraNovaPopulacao();

            ger++;

        }

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
