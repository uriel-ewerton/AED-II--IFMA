
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
        for (Vertice vertice : vertices) {
            if (vertice.info.equals(info)) {
                // Um vértice com a mesma informação já existe, 
                // então retornamos esse vértice
                return vertice;
            }
        }
        Vertice vertice = new Vertice(info);
        vertices.inserir(vertice);
        return vertice;
    }
    /**
     * 
     * 
     * A alteração dos graus leva em conta que loops "valem" 2 no vértice
     * afetado, pois temos um grau de entrada e um de saída
     * 
     * @param origem
     * @param destino
     * @param peso
     * @return 
     */
    public Aresta adicionarAresta(Vertice origem, Vertice destino) {
        //evita que se insira arestas não ponderadas em grafos ponderados
        if(!grafoPonderado){
            Aresta aresta = new Aresta(origem, destino);
            if(arestas.buscarInterno(aresta) == null){
                //caso não direcionado, adiciona aresta e sua duplicata inversa
                if(!grafoDirecionado){
                    //caso inserção  de loop (ex: (A,A)), não duplica
                    if(!aresta.origem.equals(aresta.destino)){
                        Aresta arestaInversa = new Aresta(destino, origem);
                        origem.grau--;
                        destino.grau++;
                        destino.adicionarAdjacente(arestaInversa);
                        arestas.inserir(arestaInversa);
                    }
                    origem.adicionarAdjacente(aresta);
                    origem.grau += 2;
                    arestas.inserir(aresta);
                    return aresta;
                }
                Aresta arestaInversa = new Aresta(destino, origem);
                //caso direcionado, adiciona aresta, se não houver inversa
                if(arestas.buscarInterno(arestaInversa) == null){
                    origem.grau++;
                    destino.grau++;
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
    /**
     * 
     * 
     * A alteração dos graus leva em conta que loops "valem" 2 no vértice
     * afetado, pois temos um grau de entrada e um de saída
     * 
     * @param origem
     * @param destino
     * @param peso
     * @return 
     */
    public Aresta adicionarAresta(Vertice origem, Vertice destino, int peso) {
        //evita que se insira arestas ponderadas em grafos não ponderados
        if(grafoPonderado){
            Aresta aresta = new Aresta(origem, destino,peso);
            if(arestas.buscarInterno(aresta) == null){
                //caso não direcionado, adiciona aresta e sua duplicata inversa
                if(!grafoDirecionado){
                    //caso inserção  de loop (ex: (A,A)), não duplica
                    if(!origem.equals(destino)){
                        Aresta arestaInversa = new Aresta(destino, origem, peso);
                        origem.grau--;
                        destino.grau++;
                        destino.adicionarAdjacente(arestaInversa);
                        arestas.inserir(arestaInversa);
                    }
                    origem.adicionarAdjacente(aresta);
                    origem.grau += 2;
                    arestas.inserir(aresta);
                    return aresta;
                }
                
                Aresta arestaInversa = new Aresta(destino, origem, peso);
                //caso direcionado, adiciona aresta, se não houver inversa
                if(arestas.buscarInterno(arestaInversa) == null){
                    origem.grau++;
                    destino.grau++;
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
    public void removerVertice(Vertice vertice) {
        // Remover o vértice da lista de vértices
        vertices.remover(vertice);

        // Criar uma nova lista para armazenar as arestas a serem removidas
        Lista<Aresta> arestasParaRemover = new Lista<>();

        // Adicionar todas as arestas que contêm o vértice à lista de arestas a serem removidas
        for (Aresta aresta : arestas) {
            if (aresta.origem.equals(vertice) || aresta.destino.equals(vertice)) {
                arestasParaRemover.inserir(aresta);
            }
        }

        // Remover todas as arestas na lista de arestas a serem removidas
        for (Aresta aresta : arestasParaRemover) {
            arestas.remover(aresta);
        }

        // Remover o vértice das listas de adjacências de todos os outros vértices
        for (Vertice outroVertice : vertices) {
            Lista<Aresta> adjacentesParaRemover = new Lista<>();
            for (Object x : outroVertice.adjacentes) {
                Aresta aresta = (Aresta) x;
                if (aresta.origem.equals(vertice) || aresta.destino.equals(vertice)) {
                    adjacentesParaRemover.inserir(aresta);
                }
            }
            for (Aresta aresta : adjacentesParaRemover) {
                outroVertice.adjacentes.remover(aresta);
            }
        }
        atualizarGraus();
    }
    public void removerAresta(T info1, T info2) {
        // Encontrar os vértices correspondentes aos identificadores
        Vertice vertice1 = null;
        Vertice vertice2 = null;
        for (Vertice vertice : vertices) {
            if (vertice.info.equals(info1)) {
                vertice1 = vertice;
            }
            if (vertice.info.equals(info2)) {
                vertice2 = vertice;
            }
        }

        // Se não encontramos ambos os vértices, não podemos remover a aresta
        if (vertice1 == null || vertice2 == null) {
            System.out.println("Um ou ambos os vértices não foram encontrados.");
            return;
        }

        // Remover a aresta da lista de arestas
        for (Aresta aresta : arestas) {
            if ((aresta.origem.equals(vertice1) && aresta.destino.equals(vertice2)) ||
                (aresta.origem.equals(vertice2) && aresta.destino.equals(vertice1))) {
                arestas.remover(aresta);
                break;
            }
        }

        // Remover a aresta das listas de adjacências dos vértices
        vertice1.adjacentes.remover(new Aresta(vertice1, vertice2));
        if (!grafoDirecionado) {
            vertice2.adjacentes.remover(new Aresta(vertice2, vertice1));
        }
        atualizarGraus();
    }
    void verticeeadj(){
        for(Vertice<T> v : vertices){
                System.out.println("Vértice: " + v.info);
                for(Aresta a : v.adjacentes){
                    System.out.println("Aresta: ");
                    System.out.println("        Origem: " + a.origem.info + " " + a.origem.grau);

                    System.out.println("        Destino: " + a.destino.info + " " + a.destino.grau);
                    System.out.println();
                }
            }
    }
    
    void verarestas(){
        for(Aresta yz : arestas){
            System.out.println(yz.origem.info+" "+yz.destino.info);
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
    public Lista<Aresta> obterAdjascentes(Vertice v){
        return v.adjacentes;
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

    public Lista<Vertice> getVertices() {
        return vertices;
    }

    public Lista<Aresta> getArestas() {
        return arestas;
    }
    
    private void atualizarGraus() {
        if(!grafoDirecionado){
            for (Vertice<T> vertice : vertices) {
                int grauAtualizado = 0;
                for(Aresta aresta : vertice.adjacentes){
                    if(aresta.origem.equals(aresta.destino)){
                        grauAtualizado += 2;
                    } else
                        grauAtualizado += 1;
                }
                vertice.grau = grauAtualizado;
            }
        }else if(grafoDirecionado){
            for (Vertice<T> vertice : vertices) {
                int grauEntrada = 0;
                int grauSaida = 0;

                // Calcular o grau de saída contando o número de arestas na lista de adjacências
                for (Aresta aresta : vertice.adjacentes) {
                    if (aresta.origem.equals(vertice)) {
                        grauSaida++;
                    }
                    if (aresta.destino.equals(vertice)) {
                        grauEntrada++;
                    }
                }
                // Calcular o grau de entrada procurando arestas que apontam para o vértice
                for (Aresta aresta : arestas) {
                    if (aresta.destino.equals(vertice) && !aresta.origem.equals(vertice)) {
                        grauEntrada++;
                    }
                }
                // Atualizar o grau do vértice
                vertice.grau = grauEntrada + grauSaida;
            }
        }
    }
    public class Vertice<T> { 
        T info;
        int grau;
        Lista<Aresta> adjacentes;
        
        Vertice(T info) {
            this.info = info;
            this.grau = 0;
            this.adjacentes = new Lista<>();
        }
        void adicionarAdjacente(Aresta a) {
            adjacentes.inserir(a);
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
    public class Aresta {
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
}

    

    

