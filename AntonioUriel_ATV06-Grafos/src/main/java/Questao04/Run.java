
package Questao04;

import Questao01.Grafo;
import Questao01.Grafo.Vertice;
import Questao01.Lista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Uriel Ewerton
 */
public class Run{
    public static void main (String[] args){
        
        Labirinto labirinto = new Labirinto("src\\main\\java\\entrada\\dadosQ4.txt");
        Lista<Vertice> caminho = labirinto.encontrarCaminho();

        if (caminho != null) {
            System.out.println("SAÍDA: ");
            caminho.imprimir();
        }
        
    }
    
    public static class Labirinto {

        private Grafo<Integer, Character> grafo;

        public Labirinto(String arquivo) {
            grafo = new Grafo<>(false, false);
            lerLabirinto(arquivo);
            //grafo.imprimir();  // Apenas para verificar se o grafo foi construído corretamente
        }

        private void lerLabirinto(String arquivo) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                int linhas = 0;
                int colunas = 0;

                String linha;
                while ((linha = reader.readLine()) != null) {
                    colunas = linha.length();
                    for (int j = 0; j < colunas; j++) {
                        char c = linha.charAt(j);
                        grafo.adicionarVertice((linhas * colunas) + j, c);

                    }
                    linhas++;
                }

                // Adiciona arestas entre vértices adjacentes
                for (int i = 0; i < linhas; i++) {
                    for (int j = 0; j < colunas; j++) {
                        int indiceAtual = i * colunas + j;

                        char id = grafo.obterVerticePelaPosicao(indiceAtual).getId();
                        if (id == 'A' || id == 'E' || id == 'S') {
                            // Adiciona aresta para a direita
                            if (j < colunas - 1 && (grafo.obterVerticePelaPosicao(indiceAtual + 1).getId() == 'A' || grafo.obterVerticePelaPosicao(indiceAtual + 1).getId() == 'S')) {
                                grafo.adicionarAresta(grafo.obterVerticePelaPosicao(indiceAtual), grafo.obterVerticePelaPosicao(indiceAtual + 1));
                            }
                            // Adiciona aresta para baixo
                            if (i < linhas - 1 && (grafo.obterVerticePelaPosicao(indiceAtual + colunas).getId() == 'A' || grafo.obterVerticePelaPosicao(indiceAtual + colunas).getId() == 'S')) {
                                grafo.adicionarAresta(grafo.obterVerticePelaPosicao(indiceAtual), grafo.obterVerticePelaPosicao(indiceAtual + colunas));
                            }
}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public Lista<Vertice> encontrarCaminho() {
            Vertice entrada = null;
            Vertice saida = null;

            // Encontrar vértice de entrada e de saída
            for (Vertice vertice : grafo.getVertices()) {
                if (vertice.getId().equals('E')) {
                    entrada = vertice;
                } else if (vertice.getId().equals('S')) {
                    saida = vertice;
                }
            }

            if (entrada != null && saida != null) {
                Grafo.BuscaEmProfundidade buscaDfs = grafo.new BuscaEmProfundidade(grafo);
                return buscaDfs.buscarCaminho(entrada, saida);
            } else {
                System.out.println("Entrada ou saída não encontrada.");
                return null;
            }
        }
    }
}