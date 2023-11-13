
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
    /**
     * Auxiliar de debug. 
     * Imprime todos os vértices e seus respectivos adjacentes
     */
    void verticeEAdj(){
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
    /**
     * Auxiliar de debug. 
     * Imprime todas as arestas da lista de arestas
     */
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
    private int indiceDoVertice(Vertice<T> vertice) {
        int indice = 0;
        for (Object ver : vertices) {
            Vertice<T> v = (Vertice<T>) ver;
            if (v.equals(vertice)) {
                return indice;
            }
            indice++;
        }
        throw new RuntimeException("Vértice não encontrado no grafo");
    }
    public Vertice<T> obterVerticePelaPosicao(int posicao) {
        int indice = 0;
        for (Object vertice : vertices) {
            Vertice<T> v = (Vertice<T>) vertice;
            if (indice == posicao) {
                return v;
            }
            indice++;
        }
        throw new RuntimeException("Vértice não encontrado na posição especificada");
    }
    public boolean temCiclo(Grafo grafo){
        Fila<Vertice> fila = new Fila<>();
        
        if(!grafoDirecionado){
            // Inicializar a fila com todos os vértices de grau 1
            for (Object v : grafo.vertices) {
                Vertice vertice = (Vertice) v;
                if (vertice.grau == 1) {
                    fila.inserir(vertice);
                }
            }

            // Percorrer a fila
            while (!fila.vazia()) {
                Vertice vertice = fila.remover();

                // Visitar os adjacentes do vértice corrente
                for (Object a : vertice.adjacentes) {
                    Aresta aresta = (Aresta) a;
                    Vertice adjacente = aresta.destino.equals(vertice) ? aresta.origem : aresta.destino;
                    adjacente.grau--;

                    // Se um vértice ficar com grau 1, inseri-lo na fila
                    if (adjacente.grau == 1) {
                        fila.inserir(adjacente);
                    }
                }
            }

            // Se existirem vértices não visitados (ou seja, vértices com grau maior que 0),
            // eles fazem parte de um ciclo
            for (Object v : grafo.vertices) {
                Vertice vertice = (Vertice) v;
                if (vertice.grau > 0) {
                    return true;
                }
            }
            return false;
        }
        // Caso direcionado
        // Inicializar a fila com todos os vértices de grau 1
        for (Object v : grafo.vertices) {
            Vertice vertice = (Vertice) v;
            int grauEntrada = 0;
            for (Object a : grafo.arestas) {
                Aresta aresta = (Aresta) a;
                if (aresta.destino.equals(vertice)) {
                    grauEntrada++;
                }
            }
            vertice.grau = grauEntrada; // Armazenar o grau de entrada no próprio vértice

            if (grauEntrada == 0) {
                fila.inserir(vertice);
            }
        }

        int contador = 0;

        while (!fila.vazia()) {
            Vertice vertice = fila.remover();

            // Visitar os vértices adjacentes
            for (Object a : vertice.adjacentes) {
                Aresta aresta = (Aresta) a;
                Vertice adjacente = aresta.destino;
                adjacente.grau--; // Decrementar o grau de entrada do vértice adjacente

                // Se um vértice ficar com grau de entrada 0, inseri-lo na fila
                if (adjacente.grau == 0) {
                    fila.inserir(adjacente);
                }
            }

            contador++;
        }

        // Se o contador é igual ao número de vértices, o grafo não contém um ciclo
        return contador != grafo.vertices.getTamanho();
    
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

        public int getGrau() {
            return grau;
        }

        public void setGrau(int grau) {
            this.grau = grau;
        }

        public T getInfo() {
            return info;
        }
        
        public Lista<Aresta> getAdjacentes() {
            return adjacentes;
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
            return "" + info;
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

        public Vertice getOrigem() {
            return origem;
        }

        public Vertice getDestino() {
            return destino;
        }

        public int getPeso() {
            return peso;
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
    
    public class BellmanFord {
        private Grafo grafo;
        private Vertice origem;
        private Vertice destino;
        private double[] distancias;
        private Vertice[] antecessor;

        public BellmanFord(Grafo grafo, Vertice origem, Vertice destino) {
            this.grafo = grafo;
            this.origem = origem;
            this.destino = destino;
            this.distancias = new double[grafo.vertices.getTamanho()];
            this.antecessor = new Vertice[grafo.vertices.getTamanho()];
        }

        public ResultadoBellmanFord encontrarCaminho() {
            for (int i = 0; i < grafo.vertices.getTamanho(); i++) {
                distancias[i] = Double.POSITIVE_INFINITY;
            }
            distancias[indiceDoVertice(origem)] = 0;

            for (int i = 1; i < grafo.vertices.getTamanho(); i++) {
                for (Object a : grafo.arestas) {
                    Aresta aresta = (Aresta) a;
                    int u = indiceDoVertice(aresta.origem);
                    int v = indiceDoVertice(aresta.destino);
                    double peso = aresta.peso;
                    if (distancias[u] + peso < distancias[v]) {
                        distancias[v] = distancias[u] + peso;
                        antecessor[v] = aresta.origem;
                    }
                }
            }

            for (Object a : grafo.arestas) {
                Aresta aresta = (Aresta) a;
                int u = indiceDoVertice(aresta.origem);
                int v = indiceDoVertice(aresta.destino);
                double peso = aresta.peso;
                if (distancias[u] + peso < distancias[v]) {
                    throw new RuntimeException("O grafo contém um ciclo de peso negativo");
                }
            }

            Lista<Vertice> caminho = new Lista<>();
            for (Vertice v = destino; v != null; v = antecessor[indiceDoVertice(v)]) {
                caminho.inserirNoInicio(v);
            }
            double distanciaTotal = distancias[indiceDoVertice(destino)];

            return new ResultadoBellmanFord(caminho, distanciaTotal);
        }
        
        
        
        public class ResultadoBellmanFord<T> {
            private Lista<Vertice<T>> caminho;
            private double distanciaTotal;

            public ResultadoBellmanFord(Lista<Vertice<T>> caminho, double distanciaTotal) {
                this.caminho = caminho;
                this.distanciaTotal = distanciaTotal;
            }

            public Lista<Vertice<T>> getCaminho() {
                return caminho;
            }

            public double getDistanciaTotal() {
                return distanciaTotal;
            }
        }
    }
    
    public class BuscaEmProfundidade {
        private Grafo<T> grafo;
        private boolean[] visitado;
        private Lista<Vertice<T>> caminho;

        public BuscaEmProfundidade(Grafo<T> grafo) {
            this.grafo = grafo;
            this.visitado = new boolean[grafo.getVertices().getTamanho()];
            this.caminho = new Lista<>();
        }

        public Lista<Vertice<T>> buscarCaminho(Vertice<T> inicio, Vertice<T> destino) {
            limparVisitados();
            caminho = new Lista<>();
            dfs(inicio, destino);
            return caminho;
        }

        private void dfs(Vertice<T> atual, Vertice<T> destino) {
            visitado[grafo.indiceDoVertice(atual)] = true;
            caminho.inserir(atual);

            if (atual.equals(destino)) {
                // Encontramos o destino, encerramos a busca
                return;
            }

            for (Aresta aresta : atual.getAdjacentes()) {
                Vertice<T> vizinho = aresta.getDestino();
                if (!visitado[grafo.indiceDoVertice(vizinho)]) {
                    dfs(vizinho, destino);
                    if (caminho.buscar(destino)) {
                        // Já encontramos o destino, encerramos a busca
                        return;
                    }
                }
            }

            // Se chegamos aqui, não há mais caminhos a seguir a partir deste ponto
            caminho.removerUltimo();
        }

        private void limparVisitados() {
            for (int i = 0; i < visitado.length; i++) {
                visitado[i] = false;
            }
        }
    }

}

    

    

