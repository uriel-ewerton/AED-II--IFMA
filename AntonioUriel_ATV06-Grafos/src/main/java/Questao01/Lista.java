
package Questao01;

import java.util.Iterator;

/**
 *
 * @author uriel
 * @param <T>
 */
public class Lista<T> implements Iterable <T>{
    private NoDuplo<T> primeiro;
    private NoDuplo<T> ultimo;
    private int tamanho;
    
    public Lista(){
        this.tamanho = 0;
    }
    
    public NoDuplo<T> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoDuplo<T> primeiro) {
        this.primeiro = primeiro;
    }

    public NoDuplo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NoDuplo<T> ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public void inserir (T elemento){
        NoDuplo<T> nodo = new NoDuplo<>(elemento);
        if (this.primeiro == null && this.ultimo == null){
            this.primeiro = nodo;
        }
        else {
            this.ultimo.setProximo(nodo);
            nodo.setAnterior(this.ultimo);
        }
        this.ultimo = nodo;
        this.tamanho++;
    } 
    
    public void inserirNoInicio(T elemento) {
        NoDuplo<T> nodo = new NoDuplo<>(elemento);
        if (this.primeiro == null && this.ultimo == null) {
            this.primeiro = nodo;
            this.ultimo = nodo;
        } else {
            nodo.setProximo(this.primeiro);
            this.primeiro.setAnterior(nodo);
            this.primeiro = nodo;
        }
        this.tamanho++;
    }
    
    public void remover (T elemento){
        
        if (this.tamanho == 1){
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
        }
        else if (this.tamanho > 1) {
            NoDuplo<T> aux = buscarInterno(elemento);
            if(aux != null){
                if (aux == primeiro){
                    this.primeiro = aux.getProximo();
                    aux.setProximo(null);
                }
                else if (aux == ultimo){
                    this.ultimo = aux.getAnterior();
                    this.ultimo.setProximo(null);
                }
                else {
                    aux.getProximo().setAnterior(aux.getAnterior());
                    aux.getAnterior().setProximo(aux.getProximo());
                }
                this.tamanho--;
            }
        }
    }
    public void removerUltimo() {
        if (this.tamanho == 1) {
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
        } else if (this.tamanho > 1) {
            NoDuplo<T> penultimo = this.ultimo.getAnterior();
            penultimo.setProximo(null);
            this.ultimo = penultimo;
            this.tamanho--;
        }
    }
    /*
    public String buscar (T elemento){
        NoDuplo<T> resposta = buscarInterno(elemento);
        if (resposta == null){
            return null;
        }
        else
            return resposta.getElemento().toString();
    }
    */
    public boolean buscar (T elemento){
        NoDuplo<T> resposta = buscarInterno(elemento);
        return resposta != null;
    }
    
    public NoDuplo<T> buscarInterno (T elemento){
        NoDuplo<T> auxiliar1 = this.primeiro;
        NoDuplo<T> auxiliar2 = this.ultimo;
        while (auxiliar1 != null){
            if (auxiliar1.getElemento().equals(elemento)){
                return auxiliar1;
            }
            else if (auxiliar2.getElemento().equals(elemento)){
                return auxiliar2;
            }
            auxiliar1 = auxiliar1.getProximo();
            auxiliar2 = auxiliar2.getAnterior();
        }
        return null;
        
    }
              
    public void imprimir(){
        NoDuplo<T> aux = this.primeiro;
        
        if(this.tamanho == 0){
            System.out.println("Lista vazia.");
        }
        else {
            while(aux != null){
                System.out.println(aux.getElemento().toString());
                aux = aux.getProximo();
            }
        }
    }
    
    public void alterar(T elemento, T elementoNovo){
        NoDuplo<T> aux = buscarInterno(elemento);
        if (aux != null){
            aux.setElemento(elementoNovo);
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NoDuplo<T> atual = primeiro;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                T elemento = atual.getElemento();
                atual = atual.getProximo();
                return elemento;
            }
        };
    }
    public class NoDuplo<T> {
        private T elemento;
        private NoDuplo<T> anterior;
        private NoDuplo<T> proximo;

        public NoDuplo (T elemento){
            this.elemento = elemento;
            this.anterior = null;
            this.proximo = null;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public NoDuplo<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(NoDuplo<T> anterior) {
            this.anterior = anterior;
        }

        public NoDuplo<T> getProximo() {
            return proximo;
        }


        public void setProximo(NoDuplo<T> proximo) {
            this.proximo = proximo;
        }
    }

}
