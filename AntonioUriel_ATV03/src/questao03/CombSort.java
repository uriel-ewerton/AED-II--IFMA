
package questao03;

/**
 *
 * @author uriel
 */
public class CombSort {
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
}
