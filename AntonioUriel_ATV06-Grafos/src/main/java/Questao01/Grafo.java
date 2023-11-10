
package Questao01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Uriel Ewerton
 */
public class Grafo {
    private final List<Vertice> vertices;
    private final List<Aresta> arestas;
    private final boolean grafoDirecionado;
    
    public Grafo(boolean direcionadoOuNao) {
        vertices = new ArrayList<>();
        arestas = new ArrayList<>();
        this.grafoDirecionado = direcionadoOuNao;
    }
    
    public Vertice adicionarVertice(String nome) {
        Vertice vertice = new Vertice(nome);
        vertices.add(vertice);
        return vertice;
    }
    
    public Aresta adicionarAresta(Vertice origem, Vertice destino) {
        Aresta aresta = new Aresta(origem, destino);
        origem.adicionarAdjacente(aresta);
        if(!grafoDirecionado){
            Aresta arestaInversa = new Aresta(destino, origem);
            destino.adicionarAdjacente(arestaInversa);
            arestas.add(arestaInversa);
        }
        arestas.add(aresta);
        return aresta;
    }
    
    @Override
    public String toString() {
        String res = "";
        boolean virgula;
        for (Vertice origem : vertices) {
            res += origem.nome + " >>> ";
            virgula = false;
            for (Aresta destino : origem.adjacentes) {
                Vertice v = destino.destino;
                res += ( virgula ? ", ": "") + v.nome ;
                virgula = true;
            }
            res += "\n";
        }
        return res;
        }
    }

    class Vertice { 
        String nome;
        List<Aresta> adjacentes;
        Vertice(String nome) {
            this.nome = nome;
            this.adjacentes = new ArrayList<>();
        }
        void adicionarAdjacente(Aresta e) {
            adjacentes.add(e);
        }
    }
    
    class Aresta {
        Vertice origem;
        Vertice destino;
        Aresta(Vertice origem, Vertice destino){
            this.origem = origem;
            this.destino = destino;
        }
    }

    

