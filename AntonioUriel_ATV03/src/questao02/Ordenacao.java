
package questao02;

/**
 *
 * @author uriel
 */
public class Ordenacao<T> {
    //3 adaptações de bubbleSort
    public void decrescente(ListaDE lista) {
        boolean trocou;
        do {
            trocou = false;
            NoDuplo<T> atual = lista.getPrimeiro();
            
            while (atual != null && atual.getProximo() != null) {
                NoDuplo<T> proximo = atual.getProximo();
                Produto prod = (Produto)atual.getElemento();
                Produto prodcomp = (Produto)proximo.getElemento();
                
                if (prod.getValor() < prodcomp.getValor()) {
                    // Troca os elementos
                    T temp = atual.getElemento();
                    atual.setElemento(proximo.getElemento());
                    proximo.setElemento(temp);
                    trocou = true;
                }
                atual = proximo;
            }
        } while (trocou);
    }
    public void crescente(ListaDE lista) {
        boolean trocou;
        do {
            trocou = false;
            NoDuplo<T> atual = lista.getPrimeiro();
            
            while (atual != null && atual.getProximo() != null) {
                NoDuplo<T> proximo = atual.getProximo();
                Produto prod = (Produto)atual.getElemento();
                Produto prodcomp = (Produto)proximo.getElemento();
                
                if (prod.getValor() > prodcomp.getValor()) {
                    // Troca os elementos
                    T temp = atual.getElemento();
                    atual.setElemento(proximo.getElemento());
                    proximo.setElemento(temp);
                    trocou = true;
                }
                atual = proximo;
            }
        } while (trocou);
    }
    
    public void ordemAlfabetica(ListaDE lista) {
        boolean trocou;
        do {
            trocou = false;
            NoDuplo<T> atual = lista.getPrimeiro();
            while (atual != null && atual.getProximo() != null) {
                NoDuplo<T> proximo = atual.getProximo();
                Produto prod = (Produto)atual.getElemento();
                Produto prodcomp = (Produto)proximo.getElemento();
                if (prod.getDescricao().compareTo(prodcomp.getDescricao()) > 0) {
                    // Troca os elementos
                    T temp = atual.getElemento();
                    atual.setElemento(proximo.getElemento());
                    proximo.setElemento(temp);
                    trocou = true;
                }
                atual = proximo;
            }
        } while (trocou);
    }
}
