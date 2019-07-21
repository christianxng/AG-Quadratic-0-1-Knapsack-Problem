package Control;

import javax.swing.JOptionPane;

public class Conversores {
	
	//private int vetorposicoes[];
	public int converteBinDec(String binario) {

        int decimal = 0;
        int potencia = 0;

        for (int i = binario.length() - 1; i >= 0; i--) {
            if (binario.charAt(i) == '0' || binario.charAt(i) == '1') {
                int aux = Character.getNumericValue(binario.charAt(i));
                decimal += Math.pow(2, potencia) * aux;
                potencia++;
            } else {
                JOptionPane.showMessageDialog(null, binario + " Não é um numero Binário");
            }
        }

        return decimal;

    }

    public String converteDecBin(int num) {

        String bin = Integer.toBinaryString(num);

        return bin;

    }
    /*public int[] ordenacaoDecrescente( int[] vetor ) {
        int aux, auxindice;
        vetorposicoes = null;
        
    	for (int i=0; i < vetor.length; i++){   
            for (int j=0; j < vetor.length; j++){   
                if (vetor[j] > vetor[i] && vetorposicoes[j] > vetorposicoes[i] )   
                {   
                	
                		aux = vetor[j];   
                		auxindice = j;
                		vetor[j]=vetor[i];
                    	vetorposicoes[j]=vetorposicoes[i];
                    	vetor[i]=aux;
                    	vetorposicoes[i]=auxindice;
                	}   
            	}          
    		} 
    		return vetorposicoes;
        }
*/
}
