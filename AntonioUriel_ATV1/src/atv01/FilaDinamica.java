
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

public class FilaDinamica<T> implements EstruturasDeDados<T>{
    
    private NoSimples<T> primeiro;
    private NoSimples<T> ultimo;
    private int tamanho;

    @Override
    public EstruturasDeDados criarEstrutura(){
        return new FilaDinamica();
    }
    public FilaDinamica() {
        this.tamanho = 0;
    }
    
    public NoSimples<T> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoSimples<T> primeiro) {
        this.primeiro = primeiro;
    }

    public NoSimples<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NoSimples<T> ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    // Elemento é adicionado ao final da fila como ultimo.
    @Override
    public void inserir (T elemento){
        NoSimples<T> nodo = new NoSimples<>(elemento);
        if (this.tamanho > 0){
            ultimo.setProximo(nodo);
            ultimo = nodo;
        }
        else{
            this.primeiro = nodo;
            this.ultimo = nodo;
        }
        this.tamanho++;
    } 
     
    @Override
    public void remover (){
        
        if (this.tamanho == 1){
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
        }
        else if (this.tamanho > 1){
            this.primeiro = this.primeiro.getProximo();
            this.tamanho--;
        } 
    }
    
    @Override
    public String buscar (T elemento){
        NoSimples<T> resposta = this.primeiro;
       
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
        NoSimples<T> aux = this.primeiro;
        
        if(this.tamanho == 0){
            System.out.println("Fila vazia.");
        }
        else {
            while(aux != null){
                System.out.println(aux.getElemento().toString());
                aux = aux.getProximo();
            }
        System.out.println();
        }
    }

    @Override
    public void remover(T elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

