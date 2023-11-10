
package questao1;

/**
 *
 * @author uriel
 */
public class Contador {
    
    /**
     *
     * @param atual recebe primeiro elemento da lista
     * @return recursivamente retorna a quantidade de números 1
     */
    public static int conta1(NoDuplo<Integer> atual){
        if(atual == null ){
            return 0;
        }
        int valorAtual = atual.getElemento();
        
        if (valorAtual == 0) {
            return conta1(atual.getProximo());
        }
        else if (valorAtual == 1) {
            return 1 + conta1(atual.getProximo());
        }
        return 0;
    }
}
