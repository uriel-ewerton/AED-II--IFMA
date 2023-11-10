
package questao01;

import java.util.Random;

/**
 *
 * @author uriel
 */
public class Run {

    public static void main(String[] args) {
        //int teste[] = {3,4,2,5,1,9,142,0};
        int aleatorio[]= new int[1000];
        Random r = new Random();
        for(int i=0; i < aleatorio.length; i++){
            aleatorio[i] = r.nextInt(1000) ;
        }
        //medidor 
        long inicio = System.nanoTime();
        
        
        //limita o tamanho do array que será ordenado usando SelectionSort.
        //Acima desse valor, será utilizado o MergeSort.
        int H = 100;
        
        AlgoritmoHibridoOrdenacao.hybridMergeSort(aleatorio,H);
        for(int num : aleatorio){
            System.out.println(num);
        }
        
        
        long fim = System.nanoTime();
        long tempoDeExecucao = (fim - inicio) / 1_000_000; // Converte para milissegundos
        System.out.println("Tempo de execução: " + tempoDeExecucao + " ms");
    }
    
}
