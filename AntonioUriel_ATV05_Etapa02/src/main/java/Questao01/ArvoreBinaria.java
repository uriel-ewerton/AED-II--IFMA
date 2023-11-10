
package Questao01;

/**
 *
 * @author uriel
 * @param <T>
 */
class NoArvore<T> {
    private T elemento;
    private NoArvore<T> esquerda;
    private NoArvore<T> direita;
    
    public NoArvore (T elemento){
        this.elemento = elemento;
        this.esquerda = null;
        this.direita = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NoArvore<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvore<T> esquerda) {
        this.esquerda = esquerda;
    }

    public NoArvore<T> getDireita() {
        return direita;
    }

    public void setDireita(NoArvore<T> direita) {
        this.direita = direita;
    }
    
}

public class ArvoreBinaria<T extends Comparable> {
    
    private NoArvore<T> raiz;
    
    public ArvoreBinaria(){
        this.raiz = null;
    }
   
    public void inserir (T elemento){
        NoArvore<T> NoArvore = new NoArvore<>(elemento);
        
        if (raiz == null){
            this.raiz = NoArvore;
        }else{
            NoArvore<T> atual = this.raiz;
            while(true){
                if (NoArvore.getElemento().compareTo(atual.getElemento()) < 0 ){
                    if (atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(NoArvore);
                        break;
                    }
                }else{ //se for maior ou igual
                    if (atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(NoArvore);
                        break;
                    }
                }
            }
        }
    }

    public NoArvore<T> getRaiz() {
        return raiz;
    }
    
    public void remover(T elemento) {
    raiz = removerElemento(raiz, elemento);
    }

    private NoArvore<T> removerElemento(NoArvore<T> raiz, T elemento) {
        if (raiz == null) {
            return raiz;
        }

        int comp = elemento.compareTo(raiz.getElemento());

        if (comp < 0) {
            raiz.setEsquerda(removerElemento(raiz.getEsquerda(), elemento));
        } else if (comp > 0) {
            raiz.setDireita(removerElemento(raiz.getDireita(), elemento));
        } else {
            if (raiz.getEsquerda() == null) {
                return raiz.getDireita();
            } else if (raiz.getDireita() == null) {
                return raiz.getEsquerda();
            }

            NoArvore<T> substituto = encontrarMinimo(raiz.getDireita());
            raiz.setElemento(substituto.getElemento());
            raiz.setDireita(removerElemento(raiz.getDireita(), substituto.getElemento()));
        }

        return raiz;
    }

    private NoArvore<T> encontrarMinimo(NoArvore<T> nó) {
        while (nó.getEsquerda() != null) {
            nó = nó.getEsquerda();
        }
        return nó;
    }
    
    public String buscar (T elemento){
        NoArvore<T> resposta = buscarInterno(raiz, elemento);
        if (resposta == null){
            return null;
        }
        else
            return resposta.getElemento().toString();
    }
    
    private NoArvore<T> buscarInterno (NoArvore arv, T elemento){
        NoArvore<T> buscaEsq = null;
        NoArvore<T> buscaDir = null;
        if(!(arv.getElemento().equals(elemento))){
            if(arv.getEsquerda() != null){
                buscaEsq = buscarInterno(arv.getEsquerda(), elemento);
            }
            if(buscaEsq == null){
                if(arv.getDireita()!= null)
                    buscaDir = buscarInterno(arv.getDireita(), elemento);
                if( buscaDir == null)
                    return null;
                else
                    return buscaDir;
            }
            else
                return buscaEsq;
        }
        else
            return arv;
    }
    
    public void imprimir(){
        preOrdem(this.raiz);
    }
    public void emOrdem(NoArvore<T> atual){
        if (atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getElemento());
            emOrdem(atual.getDireita());
        }        
    }
    
    public void preOrdem(NoArvore<T> atual){
        if (atual != null){
            System.out.println(atual.getElemento());
            preOrdem(atual.getEsquerda());            
            preOrdem(atual.getDireita());
        }        
    }
    
    public void posOrdem(NoArvore<T> atual){
        if (atual != null){            
            posOrdem(atual.getEsquerda());            
            posOrdem(atual.getDireita());
            System.out.println(atual.getElemento());
        }        
    }
    public void prettyPrint() {
        printHelper(this.raiz, "", true);
    }
    private void printHelper(NoArvore currPtr, String indent, boolean last) {
            // print the tree structure on the screen
            if (currPtr != null) {
                System.out.print(indent);
                if (last) {
                    System.out.print("R----");
                    indent += "     ";
                } else {
                    System.out.print("L----");
                    indent += "|    ";
                }

                System.out.println(currPtr.getElemento());

                printHelper(currPtr.getEsquerda(), indent, false);
                printHelper(currPtr.getDireita(), indent, true);
            }
    }
    public int encontrarMenorProfundidade(NoArvore<T> raiz) {
        if (raiz == null) {
            return 0;
        }
        if (raiz.getEsquerda() == null && raiz.getDireita() == null) {
            return 1;
        }
        else if (raiz.getEsquerda() == null) {
            return encontrarMenorProfundidade(raiz.getDireita()) + 1;
        }
        else if (raiz.getDireita() == null) {
            return encontrarMenorProfundidade(raiz.getEsquerda()) + 1;
        }
        
        int profundidadeEsquerda = encontrarMenorProfundidade(raiz.getEsquerda());
        int profundidadeDireita = encontrarMenorProfundidade(raiz.getDireita());

        return Math.min(profundidadeEsquerda, profundidadeDireita) + 1;
        }
    
    //recebe raiz e elemento do nó pai para busca e adiciona novo nó
    public int addDirPai(NoArvore raiz, T elemPai, T elemNovo){
        NoArvore<T> pai = buscarInterno(raiz,elemPai);
        NoArvore<T> novo = new NoArvore(elemNovo);
        if(pai != null){
            if(pai.getDireita() != null){
                return 0;
            }
            else{
                pai.setDireita(novo);
                return 1;
            } 
        }
        else
            return 0;
    }
    public int addEsqPai(NoArvore raiz, T elemPai, T elemNovo){
        NoArvore<T> pai = buscarInterno(raiz,elemPai);
        NoArvore<T> novo = new NoArvore(elemNovo);
        if(pai != null){
            if(pai.getEsquerda() != null){
                return 0;
            }
            else{
                novo.setElemento(elemNovo);
                pai.setEsquerda(novo);
                return 1;
            } 
        }
        else
            return 0;
    }
}
