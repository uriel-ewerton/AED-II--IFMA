
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
        Aresta ab = grafo.adicionarAresta(a, b, 0);
        Aresta ba = grafo.adicionarAresta(b, a,0);
        Aresta bc = grafo.adicionarAresta(b, c,0 );
        Aresta bd = grafo.adicionarAresta(b, d,0 );
        Aresta cd = grafo.adicionarAresta(c, d,0);
        Aresta dd = grafo.adicionarAresta(d, d,0);
        Aresta ddd = grafo.adicionarAresta(d, d,0);
        //Vertice f = null;
        //Aresta g = null;
        //grafo.removerVertice("A");
        System.out.println("Adjascentes de D:");
        grafo.obterAdjascentes(d).imprimir();
       
        System.out.println();
        grafo.imprimir();
    }
}
