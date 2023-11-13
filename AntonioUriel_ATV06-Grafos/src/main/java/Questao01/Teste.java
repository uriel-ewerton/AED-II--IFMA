
package Questao01;

import Questao01.Grafo.Aresta;
import Questao01.Grafo.Vertice;
/**
 *
 * @author Uriel ewerton
 */
public class Teste {

    public static void main(String[] args) {
        Grafo grafo = new Grafo(false,true);
        Vertice a = grafo.adicionarVertice("A");
        Vertice b = grafo.adicionarVertice("B");
        Vertice c = grafo.adicionarVertice("C");
        Vertice d = grafo.adicionarVertice("D");
        Aresta aa = grafo.adicionarAresta(a, a,0);
        Aresta ab = grafo.adicionarAresta(a, b,0);
        Aresta bc = grafo.adicionarAresta(b, c,0);
        Aresta bd = grafo.adicionarAresta(b, d,0);
        Aresta cd = grafo.adicionarAresta(c, d,0);
        grafo.imprimir();
        System.out.println();
        
        grafo.removerAresta("D","B");
        //grafo.verarestas();
        //grafo.verticeeadj();
        //grafo.removerVertice(b);
        System.out.println();
        grafo.verarestas();
        grafo.verticeEAdj();
        System.out.println();
        grafo.imprimir();
    }
}
