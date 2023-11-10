/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.jadadosa to edit this template
 */
package SortingAlgorithms;

/**
 *
 * @author uriel
 */
public class AlgoritmoHibridoOrdenacao {
    
    public static void hybridSort(int[] arr) {
        int n = arr.length;
        int shellSortEnd = n / 2; // Fim da parte ordenada pelo Shell Sort

        // Usando o Shell Sort para ordenar a primeira parte do vetor
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < shellSortEnd; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }

        // Usando o Insertion Sort para a parte não ordenada restante
        for (int i = shellSortEnd; i < n; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= shellSortEnd && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
    
    public static void hybridMergeSortudo(int[] arr, int H) {
        int n = arr.length;
        
        if (n > H) {
            // Divide o vetor em duas partes
            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];
            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, n - mid);
            
            // Chama o Merge Sort recursivamente para ambas as partes
            hybridMergeSortudo(left, H);
            hybridMergeSortudo(right, H);
            
            // Combina as duas partes ordenadas
            merge(arr, left, right);
        } else {
            // Se o tamanho for menor que H, aplica o Insertion Sort
            boggo(arr);
        }
    }

    public static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        
        while (i < left.length) {
            result[k++] = left[i++];
        }
        
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
    public static void boggo(int[] dados) {

        while (! estaOrdenado(dados)) {
            permutar(dados);
            }
        }
    
    public static void permutar(int[] dados) {
        for (int x = 0; x < dados.length; ++x) {
            int index1 = (int) (Math.random() * dados.length), 
                    index2 = (int) (Math.random() * dados.length);
            int a = dados[index1];
            dados[index1] = dados[index2];
            dados[index2] = a;
        }
    }
    
    public static boolean estaOrdenado(int[] dados) {
        for (int x = 0; x < dados.length - 1; ++x) {
            if (dados[x] > dados[x + 1]) {
                return false;
            }
        }
        return true;
    }
}






