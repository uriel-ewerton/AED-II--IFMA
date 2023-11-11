
package Questao01;

import java.util.Objects;

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
        if(vertices.buscarInterno(vertice) == null){
            vertices.inserir(vertice);
            return vertice;
        }else
            return null;
    }
    
    public Aresta adicionarAresta(Vertice origem, Vertice destino) {
        //evita que se insira arestas não ponderadas em grafos ponderados
        if(!grafoPonderado){
            Aresta aresta = new Aresta(origem, destino);
            if(arestas.buscarInterno(aresta) == null){
                //caso não direcionado, adiciona aresta e sua duplicata inversa
                if(!grafoDirecionado){
                    Aresta arestaInversa = new Aresta(destino, origem);
                    destino.adicionarAdjacente(arestaInversa);
                    arestas.inserir(arestaInversa);
                    origem.adicionarAdjacente(aresta);
                    arestas.inserir(aresta);
                    return aresta;
                }
                Aresta arestaInversa = new Aresta(destino, origem);
                //caso direcionado, adiciona aresta, se não houver inversa
                if(arestas.buscarInterno(arestaInversa) == null){
                    origem.adicionarAdjacente(aresta);
                    arestas.inserir(aresta);
                    return aresta;
                }
                System.out.println("Aresta já existe no caminho inverso: " 
                        + origem.info + " " + destino.info);
                return null;
            }
            System.out.println("Aresta já existe: " + origem.info + " " + destino.info);
            return null;
        }
        return null;
    }
    public Aresta adicionarAresta(Vertice origem, Vertice destino, int peso) {
        //evita que se insira arestas ponderadas em grafos não ponderados
        if(grafoPonderado){
            Aresta aresta = new Aresta(origem, destino,peso);
            if(arestas.buscarInterno(aresta) == null){
            //caso não direcionado, adiciona aresta e sua duplicata inversa
                if(!grafoDirecionado){
                    Aresta arestaInversa = new Aresta(destino, origem, peso);
                    destino.adicionarAdjacente(arestaInversa);
                    arestas.inserir(arestaInversa);
                    origem.adicionarAdjacente(aresta);
                    arestas.inserir(aresta);
                    return aresta;
                }
                Aresta arestaInversa = new Aresta(destino, origem, peso);
                //caso direcionado, adiciona aresta, se não houver inversa
                if(arestas.buscarInterno(arestaInversa) == null){
                    origem.adicionarAdjacente(aresta);
                    arestas.inserir(aresta);
                    return aresta;
                }
                System.out.println("Aresta já existe: " + origem.info + " " + destino.info);
                return null;
            }
            System.out.println("Aresta já existe: " + origem.info + " " + destino.info);
            return null;
        }
        return null;
    }
    /**
     * Remove um vértice com "nome" contido em info e limpa as arestas que 
     * se ligavam a esse vértice.
     * @param info identificador genérico do vértice.
     */
    public void removerVertice(T info){
        Vertice aux = new Vertice(info);
        vertices.remover(aux);
        removerAresta(info);
    }
    /**
     * Remove aresta que incluam vértice com o parametro info.
     * Serve para limpar arestas ao excluir um vértice.
     * @param info identificador genérico do vértice.
     */
    public void removerAresta(T info){
        for(Aresta xy : arestas){
            if(xy.origem.info.equals(info) || xy.destino.info.equals(info)){
                arestas.remover(xy);
                xy.origem.adjacentes.remover(xy);
                xy.destino.adjacentes.remover(xy);
            }
        }
    }
    /**
     * Remove aresta que inclui dois vertices, passados por info1 e info2.
     * 
     * Serve para limpar arestas específicas.
     * 
     * @param info1 identificador genérico do primeiro vértice.
     * @param info2 identificador genérico do segundo vértice.
     */
    public void removerAresta(T info1, T info2){
        for(Aresta xy : arestas){
            if(xy.origem.info.equals(info1) && xy.destino.info.equals(info2)){
                arestas.remover(xy);
                xy.origem.adjacentes.remover(xy);
                xy.destino.adjacentes.remover(xy);
            }else if(!grafoDirecionado){  
                if(xy.origem.info.equals(info2) && xy.destino.info.equals(info1)){
                    arestas.remover(xy);
                    xy.origem.adjacentes.remover(xy);
                    xy.destino.adjacentes.remover(xy);
                }
            }
        }
    }
    public String pesquisarVertice(Vertice v){
        if(vertices.buscarInterno(v) != null){
            return v.toString();
        }
        return null;
    }
    public String pesquisarAresta(Vertice x, Vertice y){
        Aresta buscada = new Aresta(x,y);
        if(arestas.buscarInterno(buscada) != null){
            return buscada.toString();
        }
        return null;
    }
    public String pesquisarAresta(Aresta xy){
        if(arestas.buscarInterno(xy) != null){
            return xy.toString();
        }
        return null;
    }
    
    public void imprimir() {
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
        System.out.print(res);
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Vertice<?> other = (Vertice<?>) obj;
            return Objects.equals(this.info, other.info);
        }

        @Override
        public String toString() {
            return "Vertice " + info;
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Aresta other = (Aresta) obj;
            if (this.peso != other.peso) {
                return false;
            }
            if (!Objects.equals(this.origem, other.origem)) {
                return false;
            }
            return Objects.equals(this.destino, other.destino);
        }

        @Override
        public String toString() {
            return "Aresta: " + "Origem  = " + origem.info 
                    + "\n        Destino = " + destino.info 
                    + "\n        peso    = " + peso;
        }
        
    }

    

