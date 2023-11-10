
package questao05;

/**
 *
 * @author uriel
 */
public class Mescla {
    public int[] mescla(int[] v1, int[] v2){
        
        //contador de 0's
        int cont0= v2.length; 
        
       //cria cópia "limpa" do vetor 1
        int[] auxV1 = new int[v1.length-cont0];
        int j=0;
        for (int i=0; i<v1.length; i++){
            if (v1[i] != 0){
                auxV1[j] = v1[i];
                j++;
            }
        }
        //retorna resultado da função de mescla
        return merge(auxV1,v2);
    }
    
     public int[] merge(int[] v1, int[] v2) {
        
        int i=0, j=0, k=0;
        int[] result = new int[v1.length + v2.length];
        //copia o menor para o vetor resultado
        while (i < v1.length && j < v2.length) {
            if (v1[i] <= v2[j]) {
                result[k] = v1[i];
                i++;
            } else {
                result[k] = v2[j];
                j++;
            }
            k++;    
        }
        //copia o restante, caso um dos vetores seja maior que o outro
        while (i < v1.length) {
            result[k] = v1[i];
            i++;
            k++;
        }
        while (j < v2.length) {
            result[k] = v2[j];
            j++;
            k++;
        }
        return result;
    }
}
