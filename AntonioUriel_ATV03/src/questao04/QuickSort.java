
package questao04;

import java.util.Arrays;

/**
 *
 * @author uriel
 */
public class QuickSort <T>{
    private static void troca(int[] dados, int x, int y) {
        int aux = dados[x];
        dados[x] = dados[y];
        dados[y] = aux;
    }
    public static int lomutoDecresc(int[] dados, int inicio, int fim) {
        
        int pivot = dados[inicio];
        int i = inicio;

        for (int j = inicio + 1; j <= fim; j++) {
            if (dados[j] > pivot) {
                i++;
                troca(dados, i, j);
                
            }
        }
        troca(dados, i,inicio);
        return i; 
    }
    
    public static void quickSortDecresc(int[] dados, int inicio, int fim) {
	if (inicio < fim) {
		int index_pivot = lomutoDecresc(dados, inicio, fim);
		quickSortDecresc(dados, inicio, index_pivot - 1);
		quickSortDecresc(dados, index_pivot + 1, fim);	
	}
    }
    
    public static int partLomuto(int[] dados, int inicio, int fim) {
        
        int pivot = dados[inicio];
        int i = inicio;

        for (int j = inicio + 1; j <= fim; j++) {
            if (dados[j] <= pivot) {
                i++;
                troca(dados, i, j);
            }
        }
        troca(dados, i, inicio);
        return i; 
    }
    public static void quickSort(int[] dados, int inicio, int fim) {
	if (inicio < fim) {
		int index_pivot = partLomuto(dados, inicio, fim);
		quickSort(dados, inicio, index_pivot - 1);
		quickSort(dados, index_pivot + 1, fim);	
	}
    }
    
    public void ordem (int[] dados){
        //insere dados de entrada em duas listas
        ListaDE listaPar = new ListaDE();
        ListaDE listaImpar = new ListaDE();
        
        for (int a : dados){
            if(a%2 == 0){
                listaPar.inserir(a);
            } else
                listaImpar.inserir(a);
        }
        //Devolve as listas para vetores
        int[] par = new int[listaPar.getTamanho()];
        NoDuplo<T> atual = listaPar.getPrimeiro();
        int i = 0;
        while (atual != null) {
            par[i] = (int)atual.getElemento();
            atual = atual.getProximo();
            i++;
        }

        int[] impar = new int[listaImpar.getTamanho()];
        atual = listaImpar.getPrimeiro();
        i = 0;
        while (atual != null) {
            impar[i] = (int)atual.getElemento();
            atual = atual.getProximo();
            i++;
        }
        //ordena os vetores
        quickSortDecresc(impar,0,impar.length-1);
        quickSort(par,0,par.length-1);
        
        //concatena tudo em um array final
        int[] resultado = new int[dados.length];
        for (int j=0; j < impar.length ; j++){
            resultado[j] = impar[j];
        }
        int k = 0;
        for (int j=impar.length ; j<dados.length; j++){
            resultado[j] = par[k]; k++;
        }
        
        System.out.println("Saída: " + Arrays.toString(resultado));
        
    }
}
