
package Questao01;

/**
 *
 * @author Uriel Ewerton
 * @param <T> Tipo Genérico
 */
public class Grafo <T>{
    /**
     * grafoDirecionado e grafoPonderado são booleanos que regulam o tipo de 
     * grafo que será montado. Verdadeiro para a ativação da característica.
     *
     */
    private final Lista<Vertice> vertices;
    private final Lista<Aresta> arestas;
    private final boolean grafoDirecionado;
    private final boolean grafoPonderado;
    
    public Grafo(boolean direcionadoOuNao, boolean ponderadoOuNao) {
        vertices = new Lista<>();
        arestas = new Lista<>();
        this.grafoDirecionado = direcionadoOuNao;
        this.grafoPonderado = ponderadoOuNao;
    }
    
    public Vertice adicionarVertice(T info) {
        Vertice vertice = new Vertice(info);
        vertices.inserir(vertice);
        return vertice;
    }
    
    public Aresta adicionarAresta(Vertice origem, Vertice destino) {
        if(!grafoPonderado){
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
        return null;
    }
    public boolean removerVertice(T info){
        
        return false;
    }
    
    @Override
    public String toString() {
        String res = "";
        boolean virgula;
        for (Vertice origem : vertices) {
            res += origem.info + " >>> ";
            virgula = false;
            for (Object x : origem.adjacentes){
                Aresta destino = (Aresta) x;
                Vertice v = destino.destino;
                res += ( virgula ? ", ": "") + v.info ;
                virgula = true;
            }
            res += "\n";
        }
        return res;
        }
    }

    class Vertice<T> { 
        T info;
        Lista<Aresta> adjacentes;
        Vertice(T info) {
            this.info = info;
            this.adjacentes = new Lista<>();
        }
        void adicionarAdjacente(Aresta e) {
            adjacentes.inserir(e);
        }
    }
    
    class Aresta {
        Vertice origem;
        Vertice destino;
        int peso;
        
        Aresta(Vertice origem, Vertice destino){
            this.origem = origem;
            this.destino = destino;
        }

        Aresta(Vertice origem, Vertice destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
        
    }

    

