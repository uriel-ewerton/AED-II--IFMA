
package questao05;

import java.util.Arrays;
import util.Reader;

/**
 *
 * @author uriel
 */
public class Run {
    public static void main (String[] args){
        /*
        int[] X = {0, 2, 0, 3, 0, 5, 6, 0, 0 };
        int[] Y = { 1, 8, 9, 10, 15 };*/
        
        Reader leitorEntrada = new Reader("src/entrada/dadosQ5.txt");
        
        String entrada = leitorEntrada.lerProximaLinha();
        //recebe e trata dados de entrada
        String[] colecao = entrada.split(",");
        for (int i = 0; i < colecao.length; i++){
            colecao[i]= colecao[i].trim();
        }
        int[] X = new int[colecao.length];
        for (int i = 0; i < colecao.length; i++){
            X[i] = Integer.parseInt(colecao[i]);
        }
        entrada = leitorEntrada.lerProximaLinha();
        //recebe e trata dados de entrada
        colecao = entrada.split(",");
        for (int i = 0; i < colecao.length; i++){
            colecao[i]= colecao[i].trim();
        }
        int[] Y = new int[colecao.length];
        for (int i = 0; i < colecao.length; i++){
            Y[i] = Integer.parseInt(colecao[i]);
        }
        
        
        Mescla m = new Mescla();
        System.out.println(Arrays.toString(m.mescla(X, Y)));
        leitorEntrada.fecharArquivo();
    }
}
/*
Considerando dois vetores X[] e Y[] de tamanho m e n cada, onde m >= n e X[] tem exatamente 
n células vazias, mesclar X, Y mantendo a ordenação.
Entrada: X[] = { 0, 2, 0, 3, 0, 5, 6, 0, 0 }; Y[] = { 1, 8, 9, 10, 15 };
Células vazias em X são representadas pelo 0
Saída: 1, 2, 3, 5, 6, 8, 9, 10, 15
*/