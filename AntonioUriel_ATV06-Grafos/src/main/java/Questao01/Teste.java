
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
        Aresta ab = grafo.adicionarAresta(a, a, 0);
        Aresta ba = grafo.adicionarAresta(a, b,0);
        Aresta bc = grafo.adicionarAresta(b, c,0 );
        Aresta bd = grafo.adicionarAresta(b, d,0 );
        Aresta cd = grafo.adicionarAresta(c, d,0);
        //Vertice f = null;
        //Aresta g = null;
        //grafo.removerVertice("A");
        Lista vertices = grafo.getVertices();
        for(Object x : vertices){
            Vertice destino = (Vertice) x;
            System.out.println(destino.info+" "+destino.grau);
           
        }
        grafo.removerVertice("A");
        
        //System.out.println("Adjascentes de D:");
        //grafo.obterAdjascentes(d).imprimir();
        System.out.println();
        for(Object x : vertices){
            Vertice destino = (Vertice) x;
            System.out.println(destino.info+" "+destino.grau);
           
        }
        System.out.println();
        grafo.imprimir();
    }
}
