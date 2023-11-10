/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao01;

/**
 *
 * @author uriel
 */
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
