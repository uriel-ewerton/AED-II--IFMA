
package atv01;

import util.EstruturasDeDados;

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

public class ArvoreBinaria<T extends Comparable> implements EstruturasDeDados<T>{
    
    private NoArvore<T> raiz;
    
    @Override
    public EstruturasDeDados criarEstrutura(){
        return new ArvoreBinaria();
    }
    public ArvoreBinaria(){
        this.raiz = null;
    }
    
    /*private NoArvore<T> getRaiz() {
    return raiz;
    }*/
    
    @Override
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
                }else{ 
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

    
    @Override
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
    //remover() mais completo, mas copiado :)
    /*
    public void remover(T Elemento){
        //buscar o NoArvore na árvore
        NoArvore<T> atual = this.raiz;
        NoArvore<T> paiAtual = null;
        while(atual != null){
            if (atual.getElemento().equals(Elemento)){
                break;                
            }else if (Elemento.compareTo(atual.getElemento()) == -1){ //Elemento procurado é menor que o atual 
                paiAtual = atual;
                atual = atual.getEsquerda();
            }else{
                paiAtual = atual;
                atual = atual.getDireita();
            }
        }
        //verifica se existe o nó
        if (atual != null){
            
            //Nó tem 2 filhos ou Nó tem somente filho à direita
            if (atual.getDireita() != null){
                
                NoArvore<T> substituto = atual.getDireita();
                NoArvore<T> paiSubstituto = atual;
                while(substituto.getEsquerda() != null){
                    paiSubstituto = substituto;
                    substituto = substituto.getEsquerda();
                }
                substituto.setEsquerda(atual.getEsquerda());
                if (paiAtual != null){
                    if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(substituto);
                    }else{
                        paiAtual.setDireita(substituto);
                    }
                }else{ //se não tem paiAtual, então é a raiz
                    this.raiz = substituto;
                    paiSubstituto.setEsquerda(null);
                    this.raiz.setDireita(paiSubstituto);
                    this.raiz.setEsquerda(atual.getEsquerda());
                }
                
                //removeu o NoArvore da árvore
                if (substituto.getElemento().compareTo(paiSubstituto.getElemento()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsquerda(null);
                }else{
                    paiSubstituto.setDireita(null);
                }
                
            }else if (atual.getEsquerda() != null){ //tem filho só à esquerda
                NoArvore<T> substituto = atual.getEsquerda();
                NoArvore<T> paiSubstituto = atual;
                while(substituto.getDireita() != null){
                    paiSubstituto = substituto;
                    substituto = substituto.getDireita();
                }
                if (paiAtual != null){
                    if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(substituto);
                    }else{
                        paiAtual.setDireita(substituto);
                    }
                }else{ //se for a raiz
                    this.raiz = substituto;
                }
                
                //removeu o nó da árvore
                if (substituto.getElemento().compareTo(paiSubstituto.getElemento()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsquerda(null);
                }else{
                    paiSubstituto.setDireita(null);
                }
            }else{ //não tem filho
                if (paiAtual != null){
                    if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1){ //atual < paiAtual
                        paiAtual.setEsquerda(null);
                    }else{
                        paiAtual.setDireita(null);
                    }
                }else{ //é a raiz
                    this.raiz = null;
                }
            }
        }
    }
    */
    @Override
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
    
    //Para alterar a ordem de impressão basta alternar "preOrdem", "emOrdem" e "posOrdem" em imprimir()
    @Override
    public void imprimir(){
        preOrdem(this.raiz);
    }
    private void emOrdem(NoArvore<T> atual){
        if (atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getElemento());
            emOrdem(atual.getDireita());
        }        
    }
    
    private void preOrdem(NoArvore<T> atual){
        if (atual != null){
            System.out.println(atual.getElemento());
            preOrdem(atual.getEsquerda());            
            preOrdem(atual.getDireita());
        }        
    }
    
    private void posOrdem(NoArvore<T> atual){
        if (atual != null){            
            posOrdem(atual.getEsquerda());            
            posOrdem(atual.getDireita());
            System.out.println(atual.getElemento());
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
    
    // Unsupported causado pelo uso (de iniciante) da interface.
    @Override
    public int getTamanho() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void remover() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
