
package Questao04;

import Questao01.Grafo;
import Questao01.Lista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 *
 * @author Uriel Ewerton
 */
public class Run{
    public static void main (String[] args){
        Grafo grafo = new Grafo(true,false);
        /*
        try(BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\dadosQ4.txt"))){
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
*/
        //grafo.imprimir();
        char[][] matriz = {
        {'A', 'A', 'A', 'A', 'E'},
        {'X', 'X', 'X', 'X', 'A'},
        {'X', 'X', 'A', 'A', 'A'},
        {'X', 'X', 'A', 'X', 'X'},
        {'S', 'A', 'A', 'X', 'X'}
        };

    Grafo.Labirinto labirinto = grafo.new Labirinto(matriz);
    Lista<Grafo.Vertice> caminho = labirinto.encontrarCaminho();
    caminho.imprimir();
    //System.out.println("SAÍDA: " + caminho.stream().map(v -> v.info).collect(Collectors.joining(" – ")));

    }
    
}