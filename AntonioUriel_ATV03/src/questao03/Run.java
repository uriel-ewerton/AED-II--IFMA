
package questao03;

import java.util.Arrays;
import util.Reader;

/**
 *
 * @author uriel
 */

public class Run {
    public static void main(String[] args){
        Reader leitorEntrada = new Reader("src/entrada/dadosQ3.txt");
        String entrada = leitorEntrada.lerProximaLinha();
        
        //recebe e trata dados de entrada
        entrada = entrada.substring(1, entrada.length()-1); //necessário somente pela especificação do exercício
        String[] colecao = entrada.split(",");
        
        for (int i = 0; i < colecao.length; i++){
            colecao[i]= colecao[i].trim();
        }
        
        int[] num= new int[colecao.length];
        for (int i = 0; i < colecao.length; i++){
            num[i] = Integer.parseInt(colecao[i]);
        }
        
        //ordena o array de entrada
        CombSort ord = new CombSort();
        ord.combSort(num);
        System.out.println("Entrada ordenada: " + Arrays.toString(num));
        
        //encontra a mediana
        int result = num.length/2;
        if(num.length%2 == 0){ //caso par
            System.out.println("Saída: " + (num[result-1]+ num[result])/2);
        }
        else
            System.out.println("Saída: " + num[result]);
        
        leitorEntrada.fecharArquivo();
    }
}
/*
Dada uma coleção de números inteiros, distintos entre si, determine a mediana.
A mediana corresponde ao valor que seja maior que a metade dos elementos e menor
que a outra metade. Não é a mesma coisa que a média, por exemplo: 
a coleção [22, 15, 74] possui média = 37, enquanto a mediana = 22.
Entrada: [1, 2, 3, 4, 7, 22, 425, 17, 54, 32, 93, 105, 55, 56, 57, 58, 59]
Saída: 54
*/