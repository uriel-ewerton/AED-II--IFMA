
package Questao01;

/**
 *
 * @author Uriel Ewerton
 * @param <T> Tipo Genérico
 */
public class Grafo <T>{
    private final Lista<Vertice> vertices;
    private final Lista<Aresta> arestas;
    private final boolean grafoDirecionado;
    
    public Grafo(boolean direcionadoOuNao) {
        vertices = new Lista<>();
        arestas = new Lista<>();
        this.grafoDirecionado = direcionadoOuNao;
    }
    
    public Vertice adicionarVertice(T info) {
        Vertice vertice = new Vertice(info);
        vertices.inserir(vertice);
        return vertice;
    }
    
    public Aresta adicionarAresta(Vertice origem, Vertice destino) {
        Aresta aresta = new Aresta(origem, destino);
        origem.adicionarAdjacente(aresta);
        if(!grafoDirecionado){
            Aresta arestaInversa = new Aresta(destino, origem);
            destino.adicionarAdjacente(arestaInversa);
            arestas.inserir(arestaInversa);
        }
        arestas.inserir(aresta);
        return aresta;
    }
    public boolean removerVertice(String nome){
        
        return false;
    }
    
    @Override
    public String toString() {
        String res = "";
        boolean virgula;
        for (Vertice origem : vertices) {
            res += origem.info + " >>> ";
            virgula = false;
            for (Aresta destino : origem.adjacentes) {
                Vertice v = destino.destino;
                res += ( virgula ? ", ": "") + v.info ;
                virgula = true;
            }
            res += "\n";
        }
        return res;
        }
    }

    class Vertice <T>{ 
        T info;
        Lista<Aresta> adjacentes;
        Vertice(T info) {
            this.info = info;
            this.adjacentes = new Lista<>();
        }
        private void adicionarAdjacente(Aresta e) {
            adjacentes.inserir(e);
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

    

