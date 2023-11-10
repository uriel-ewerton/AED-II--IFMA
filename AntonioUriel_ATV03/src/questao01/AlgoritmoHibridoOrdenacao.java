
package questao01;

/**
 *
 * @author uriel
 */
public class AlgoritmoHibridoOrdenacao {
    
    public static void hybridMergeSort(int[] dados, int H) {
        int n = dados.length;
        
        if (n > H) {
            // Divide o vetor em duas partes
            int meio = n / 2;
            int[] esq = new int[meio];
            int[] dir = new int[n - meio];
            System.arraycopy(dados, 0, esq, 0, meio);
            System.arraycopy(dados, meio, dir, 0, n - meio);
            
            // Chama o Merge Sort recursivamente para ambas as partes
            hybridMergeSort(esq, H);
            hybridMergeSort(dir, H);
            
            // Combina as duas partes ordenadas
            merge(dados, esq, dir);
        } else {
            // Se o tamanho for menor que H, aplica o SelectionSort
            selectionSort(dados);
        }
    }

    public static void merge(int[] result, int[] esq, int[] dir) {
        int i = 0, j = 0, k = 0;
        
        while (i < esq.length && j < dir.length) {
            if (esq[i] < dir[j]) {
                result[k++] = esq[i++];
            } else {
                result[k++] = dir[j++];
            }
        }
        
        while (i < esq.length) {
            result[k++] = esq[i++];
        }
        
        while (j < dir.length) {
            result[k++] = dir[j++];
        }
    }
    
    public static void selectionSort(int[] dados){
        int i,j,min,aux;
        for(i=0;i<(dados.length-1);i++){
            min=i;
            for(j=i+1;j<dados.length;j++){
                if(dados[j]<dados[min]){
                    min=j;
                }
            }
            if(dados[i] != dados[min]){
                aux=dados[i];
                dados[i]=dados[min];
                dados[min]=aux;
            }
        }
    }
}






