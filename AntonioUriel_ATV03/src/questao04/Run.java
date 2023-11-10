
package questao04;

import util.Reader;

/**
 *
 * @author uriel
 */
public class Run {
    public static void main(String[] args){
        Reader leitorEntrada = new Reader("src/entrada/dadosQ4.txt");
        String entrada = leitorEntrada.lerProximaLinha();
        System.out.println("Entrada: " + entrada);
        //recebe e trata dados de entrada
        entrada = entrada.substring(1, entrada.length()-1); 
        String[] colecao = entrada.split(",");
        
        for (int i = 0; i < colecao.length; i++){
            colecao[i]= colecao[i].trim();
        }
        
        int[] num= new int[colecao.length];
        for (int i = 0; i < colecao.length; i++){
            num[i] = Integer.parseInt(colecao[i]);
        }
        //ordena e exibe coleção ordenada
        QuickSort ord = new QuickSort();
        ord.ordem(num);
        leitorEntrada.fecharArquivo();
    }
}
/*
Considerando uma coleção de números inteiros, ordenar de maneira que a primeira parte sejam os números
ímpares em ordem decrescente enquanto que a parte final contenha os números pares em ordem crescente.
Entrada = {1, 2, 3, 5, 4, 7, 10}
Saida = {7, 5, 3, 1, 2, 4, 10}

Entrada = {0, 4, 5, 3, 7, 2, 1}
Saida = {7, 5, 3, 1, 0, 2, 4}
*/