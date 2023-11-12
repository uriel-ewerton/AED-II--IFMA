
package Questao02;


import Questao01.Grafo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import util.Fila;

/**
 *
 * @author uriel
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
        Fila fila = new Fila();
        
    }
}
/* Uma abordagem seria: 
dada uma fila dinâmica, inicialmente com todos os vértices de grau 1. 
Percorrer a fila de forma que sejam visitados os adjacentes do vértice corrente, 
para cada um decrementar de 1 o seu grau. Sempre que um vértice ficar com grau
1 deve ser inserido na fila. Após finalizar a fila, caso existam vértices não 
visitados, eles fazem parte de um ciclo.
*/