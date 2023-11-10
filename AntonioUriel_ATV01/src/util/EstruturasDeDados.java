
package util;

/**
 *
 * @author uriel
 * @param <T>
 * 
 */
public interface EstruturasDeDados<T> {
    //estabelece conexão entre todas as estruturas a fim de poder instanciá-las de forma flexível
    EstruturasDeDados criarEstrutura();

    void inserir(T elemento);
    void remover (T elemento);
    void remover ();
    String buscar (T elemento);
    void imprimir();
    int getTamanho();
    
    
}
