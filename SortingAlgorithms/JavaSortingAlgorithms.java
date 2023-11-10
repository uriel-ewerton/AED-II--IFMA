/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SortingAlgorithms;

/**
 *
 * @author uriel
 */
public class JavaSortingAlgorithms {
    public void selectionSort(int[] dados){
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
    
    public void bubbleSort(int[] dados){
        int i,j,aux;
        for(i=0;i<dados.length-1;i++){
            for(j=0;j<dados.length-1-i;j++){
                if(dados[j]>dados[j+1]){
                    aux=dados[j];
                    dados[j]=dados[j+1];
                    dados[j+1]=aux;
                }
            }
        }
    }
    
    public void combSort(int[] dados){
        int gap=dados.length;
        boolean trocou=true;
        while(gap!=1 || trocou==true){
            gap=getGap(gap);
            trocou=false;
            for(int i=0;i<dados.length-gap;i++){
                if(dados[i]>dados[i+gap]){
                    int temp=dados[i];
                    dados[i]=dados[i+gap];
                    dados[i+gap]=temp;
                    trocou=true;
                }
            }
        }
    }
    int getGap(int gap){
        gap /=1.3;
        if(gap<1)gap=1;
        return gap;
    }
    
    public void insertionSort(int[] dados){
        int i,j = 0,aux;
        for(i=1; i<dados.length; i++){
            aux=dados[i];
            j= i-1;
            while(j>=0 && (dados[j]>aux)){
                dados[j+1]=dados[j];
                j--;
            }
            dados[j+1]=aux;
        }
    }
    
    public void merge(int[] dados, int inicio, int meio, int fim) {
        int[] aux = new int[dados.length];
        for (int i = inicio; i <= fim; i++) { 
            aux[i] = dados[i]; 
        }
        int i = inicio, j = meio + 1, k = inicio;
        
        while (i <= meio && j <= fim) {
            if (aux[i] <= aux[j]) {
                dados[k] = aux[i]; i++;
            } else {
                dados[k] = aux[j]; j++;
            }
            k++;
        }
        while (i <= meio) {                // copia o resto do lado esquerdo
            dados[k] = aux[i]; k++; i++;
        }
        while (j <= fim) {                // copia o resto do lado direito
            dados[k] = aux[j]; k++; j++;
        }
    }
    public void mergeSort(int[] dados, int inicio, int fim) {   
        if (inicio >= fim)
            return;
        
        else {
            int meio = (inicio + fim) / 2;
            mergeSort(dados, inicio, fim);
            mergeSort(dados, inicio + 1, fim);
    
            merge(dados, inicio, meio, fim);
        }
    }
}
