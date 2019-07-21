package Control;

import java.util.ArrayList;
import java.util.Random;

public class AGenetico {
	
	
	private int valoresObjeto[];	
    private int pesoObjeto[];
    private int capacidadeMochila;
    private ArrayList<String> _POPULACAO;
    private int valoresADC[][];


    
    
    public AGenetico(int[] valoresObjeto, int[] pesoObj, int capacidadeMochila, ArrayList<String> _POPULACAO,
			int[][] valoresADC) {		
		this.valoresObjeto = valoresObjeto;
		this.pesoObjeto = pesoObj;
		this.capacidadeMochila = capacidadeMochila;
		this._POPULACAO = _POPULACAO;
		this.valoresADC = valoresADC;
	}
    

  
	public boolean validaCromossomo(String fenotipo) {

        /*
        utilizando o vetor global de pesoObj para calcula o peso.  
        retorna um valor Booleano no caso do cromossomo ter o
        peso menor ou igual a a capacidade da mochila.  
         */
        int peso = 0;
        //int []vetorposicoesaux =  new int[fenotipo.length()];
        for (int i = 0; i < fenotipo.length(); i++) {
        	
        	//vetorposicoesaux[i]=Integer.parseInt(fenotipo.substring(i, i+1));      // passa string pra um vetor de array

            String aux = String.valueOf(fenotipo.charAt(i));
            if (aux.equals("1")) {
                peso += pesoObjeto[i];
            }
        }

        return peso <= capacidadeMochila;

    }    
	
    public int calculaAptidao(String fenotipo) {

        /*  
        utiliza o Vetor de valorObj para calcular a aptd�o e retorna o valor da aptd�o. 
        ou retorna 0 caso o mesmo ultrapasse a capacidade maxima da mochila, calulado pela fun��o validaCromossomo
         */
    	int valoreselecionados[]=new int[8];
        int aptdao = -1;
        int posicoes[] = new int[8];
        if (validaCromossomo(fenotipo)) {
            for (int i = 0; i < fenotipo.length(); i++) {
                String aux = String.valueOf(fenotipo.charAt(i));
                if (aux.equals("1")) {
                    aptdao += valoresObjeto[i];
                    posicoes[i]= 1; // na posi��o i do vetor auxiliar recebe 1, os itens que entrar�o na mochila
                }
            }
        }
        int x = 0,contador = 0,valor;
        // int x -> respons�vel por percorrer as posi��es do vetor que vai guardar os bonus
        // int contador - > respons�vel por percorrer o vetor de posi��es da mochila
        //int j - > respons�vel por percorrer o vetor de posi��es da mochila + 1
        
        while(contador<8){
        	for(int j = contador+1;j<8;j++) {
        		if(posicoes[contador]==1 && posicoes[j]==1) {
        			if(contador!=j) {
        				valoreselecionados[x] = valoresADC[contador][j];
        				x++;
        			}	
        		}
        	}
        	contador++;
        }
        
        int somabonus = 0;
        for(int t=0;t < valoreselecionados.length;t++)
        {
        	somabonus+=valoreselecionados[t]; // percorre os valores bonus obtidos da matriz
        }
        

        return aptdao+somabonus; // retorna a aptid�o do indiv�duo que � a soma de todos os valores dos objetos mais os valores bonus

    }
    
    public String Mutacao(String fenotipo) {
        
        Random rd = new Random(); 
        int pos = rd.nextInt(fenotipo.length());// Sorteia uma posi��o da String e altera o valor e retorna outra String //      

        char[] aux = fenotipo.toCharArray();

        for (int i = 0; i < aux.length; i++) {
            if (i == pos) {
                if (aux[pos] == '1') {
                    aux[pos] = '0';
                } else {
                    aux[pos] = '1';
                }
            }
        }

        return String.valueOf(aux);
    }
    
    /*
    Fun��o de crossover de cromossomos, pega a metade (1 parte) do cromossomo c1
    mais a metade (2 parte) do cromossomo c2, depois o mesmo com c2 (1 parte) + c1 (2 parte)
     */
    public String Crossover(String pai1, String pai2) {
        Random r = new Random();
        int posicao = r.nextInt(7);
        String filho = pai1.substring(0, posicao) + pai2.substring(posicao+1);

        return filho;

    }
    
    
    public ArrayList<String> aplicaCrossover(ArrayList<String> idvs) {
    	Random rd = new Random();
    	Random ta = new Random();
    	 ArrayList<String> filhos = new ArrayList<>();
    	int taxa =ta.nextInt(100);
    	if(taxa<75)
    	{
    		int contador =1 + rd.nextInt(20);          
        	
            filhos.add(Crossover(idvs.get(contador), idvs.get(contador+1)));
            filhos.add(Crossover(idvs.get(contador+1), idvs.get(contador)));
    		
    	} 	
       

        
        return filhos;
    }
    
    public ArrayList<String> aplicaMutacao(ArrayList<String> idvs) {

        ArrayList<String> mutacao = new ArrayList<>();
        for (int i = 0; i <  idvs.size(); i++) {
        	
        	Random rd = new Random();
        	int contador =  rd.nextInt(100);
        	
        	if(contador<=9) {
        		mutacao.add(Mutacao(idvs.get(i)));
        	}
        	mutacao.add(idvs.get(i));
            
        }

        System.out.println("Aplicando Mutação >> " + mutacao + "\n");
        return mutacao;
    }
    public ArrayList<String[]> selecaoPorAptdao() {

        //Descarta os individuos com aptidão -1 ou seja INVALIDO
        ArrayList<String[]> idvs = new ArrayList<>();
        int cont = 0;

        System.out.println("Calculo aptid�o");
        for (String idv : _POPULACAO) {
            int apt = calculaAptidao(idv); //Calcula a aptdao e adiciona no array aptds
            if (apt > -1) {
                idvs.add(new String[]{idv, String.valueOf(apt)});
                System.out.println("[" + idv + ", " + apt + "] ");
            } else {
                cont++;
            }
        }

        System.out.println("\nDescartados: " + cont + " Individuos com o valor acima da capacidade da mochila\n");
        return idvs;

    }
    
    
    
    public ArrayList<String> selecaoPorRoleta(ArrayList<String[]> idvs) {

        float soma = 0;
        for (String[] apt : idvs) {
            soma += Float.parseFloat(apt[1]);
        }

        //Calcula o % de probabilidade de um idv ser escolhido  
        System.out.println("Probabilidade de Escolha >>\n");
        for (String[] apt : idvs) {
            apt[1] = String.valueOf(soma / Float.parseFloat(apt[1]));
            System.out.println("[" + apt[0] + ", " + apt[1] + "%]\n");
        }

        //Ordena o melhores IDVS de acordo com a probabilidade de ser escolhido
        for (int i = 0; i < idvs.size(); i++) {
            String[] a = idvs.get(i);
            double prob1 = Double.parseDouble(a[1]);

            for (int j = i; j < idvs.size(); j++) {
                String[] b = idvs.get(j);
                double prob2 = Double.parseDouble(b[1]);

                if (prob2 > prob1) {
                    String[] c = idvs.get(j);
                    idvs.remove(j);
                    idvs.add(i, c);
                }
            }
        }
        ArrayList<String> melhores = new ArrayList();

        int cont = 0;
        for (String[] aux : idvs) {
            if (cont <= _POPULACAO.size() / 2) {
                melhores.add(aux[0]);
                cont++;
            }
        }

        System.out.println("\nIndividuos Selecionados >> " + melhores + "\n");

        return melhores;

    }
    
    public void exibePopulacao(ArrayList<String> populacao) {
        this._POPULACAO = populacao;
        
    }
    
    public ArrayList<String> GeraNovaPopulacao() {

        ArrayList<String[]> selecao = selecaoPorAptdao();
        ArrayList<String> pais = selecaoPorRoleta(selecao);
        //Aplica o cross-over nos pais
        ArrayList filhos = aplicaCrossover(pais);
        //Aplica a muta��oo no filhos
        ArrayList mutacao = aplicaMutacao(filhos);

        //Cria a nova populacao de inviduos
        ArrayList nova_populacao = new ArrayList();
        nova_populacao.addAll(mutacao);

        System.out.println("Nova Populacao >> " + nova_populacao + "\n");

        return nova_populacao;

    }


}
