
package Questao01;

/**
 *
 * @author 20221SI0027
 */
public class Teste {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(true);
        Vertice a = grafo.adicionarVertice("A");
        Vertice b = grafo.adicionarVertice("B");
        Vertice c = grafo.adicionarVertice("C");
        Vertice d = grafo.adicionarVertice("D");
        Aresta ab = grafo.adicionarAresta(a, b);
        Aresta bc = grafo.adicionarAresta(b, c);
        Aresta bd = grafo.adicionarAresta(b, d);
        Aresta cd = grafo.adicionarAresta(c, d);
        Aresta dd = grafo.adicionarAresta(d, d);
        System.out.println(grafo);
    }
}
