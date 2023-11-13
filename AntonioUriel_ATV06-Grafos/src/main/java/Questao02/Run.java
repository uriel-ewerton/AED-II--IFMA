
package Questao02;

import Questao01.Grafo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import util.Fila;

/**
 *
 * @author Uriel Ewerton
 */
public class Run{
    public static void main (String[] args){
        Grafo grafo = new Grafo(false,false);
        try(BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\dadosQ2eQ3"))){
            String linha;
            while ((linha = arquivo.readLine()) != null){
                String[] vertices = linha.split(";");
                Grafo.Vertice origem = grafo.adicionarVertice(vertices[0]);
                Grafo.Vertice destino = grafo.adicionarVertice(vertices[1]);
                grafo.adicionarAresta(origem, destino);
            }

        } catch (IOException e) {
            System.out.println("Erro de leitura: " + e);
        }
        grafo.imprimir();
        System.out.println(temCiclo(grafo) ? "O grafo contém um ciclo" : "O grafo não contém um ciclo");
        
    }
    public static boolean temCiclo(Grafo grafo){
        Fila<Grafo.Vertice> fila = new Fila<>();

        // Inicializar a fila com todos os vértices de grau 1
        for (Object v : grafo.getVertices()) {
            Grafo.Vertice vertice = (Grafo.Vertice) v;
            if (vertice.getGrau() == 1) {
                fila.inserir(vertice);
            }
        }

        // Percorrer a fila
        while (!fila.vazia()) {
            Grafo.Vertice vertice = fila.remover();

            // Visitar os adjacentes do vértice corrente
            for (Object a : vertice.getAdjacentes()) {
                Grafo.Aresta aresta = (Grafo.Aresta) a;
                Grafo.Vertice adjacente = aresta.getDestino().equals(vertice) ? aresta.getOrigem() : aresta.getDestino();
                adjacente.setGrau(adjacente.getGrau()-1);

                // Se um vértice ficar com grau 1, inseri-lo na fila
                if (adjacente.getGrau() == 1) {
                    fila.inserir(adjacente);
                }
            }
        }

        // Se existirem vértices não visitados (ou seja, vértices com grau maior que 0), eles fazem parte de um ciclo
        for (Object v : grafo.getVertices()) {
            Grafo.Vertice vertice = (Grafo.Vertice) v;
            if (vertice.getGrau() > 0) {
                return true;
            }
        }

        return false;
    }

}