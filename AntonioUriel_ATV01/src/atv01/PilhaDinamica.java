
package atv01;

import util.EstruturasDeDados;

/**
 *
 * @author uriel
 * @param <T>
 */

class NoSimples <T>{
    private T elemento;
    private NoSimples<T> proximo;
    
    public NoSimples (T elemento){
        this.elemento = elemento;
        this.proximo = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NoSimples<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoSimples<T> proximo) {
        this.proximo = proximo;
    }
}

public class PilhaDinamica<T> implements EstruturasDeDados<T>{
    private NoSimples<T> topo;
    private int tamanho;

    @Override
    public EstruturasDeDados criarEstrutura(){
        return new PilhaDinamica();
    }
    public PilhaDinamica() {
        this.tamanho = 0;
    }
    
    
    public NoSimples<T> getTopo() {
        return topo;
    }

    public void setTopo(NoSimples<T> topo) {
        this.topo = topo;
    }

    @Override
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

   
    @Override
    public void inserir (T elemento){
        NoSimples<T> nodo = new NoSimples<>(elemento);
        if (this.tamanho > 0){
            nodo.setProximo(topo);
            topo = nodo;
        }
        else
            this.topo = nodo;
        this.tamanho++;
    } 
     
    @Override
    public void remover (){
        
        if (this.tamanho == 1){
            this.topo = null;
            this.tamanho--;
        }
        else if (this.tamanho > 1){
            this.topo = this.topo.getProximo();
            this.tamanho--;
        } 
    }
    @Override
    public String buscar (T elemento){
        NoSimples<T> resposta = this.topo;
       
        while (resposta != null){
            if (resposta.getElemento().equals(elemento)){
                return resposta.getElemento().toString();
            }
            resposta = resposta.getProximo();
        }
        return null;
    }
                   
    @Override
    public void imprimir(){
        NoSimples<T> aux = this.topo;
        
        if(this.tamanho == 0){
            System.out.println("Pilha vazia.");
        }
        else {
            while(aux != null){
                System.out.println(aux.getElemento().toString());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public void remover(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

