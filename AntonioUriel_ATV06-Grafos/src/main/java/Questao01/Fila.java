
package Questao01;

/**
 *
 * @author uriel
 * @param <T>
 */
public class Fila<T> {
    
    private NoSimples<T> primeiro;
    private NoSimples<T> ultimo;
    private int tamanho;

    public Fila() {
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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public boolean vazia(){
        return tamanho <= 0;
    }
    
    // Elemento é adicionado ao final da fila como ultimo.
    
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
     
    public T remover (){
        NoSimples<T> nodo = primeiro;
        if (this.tamanho == 1){
            this.primeiro = null;
            this.ultimo = null;
            this.tamanho--;
            return nodo.getElemento();
        }
        else if (this.tamanho > 1){
            this.primeiro = this.primeiro.getProximo();
            this.tamanho--;
            return nodo.getElemento();
        } 
        return null;
    }
    
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
    public class NoSimples <T>{
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
}
