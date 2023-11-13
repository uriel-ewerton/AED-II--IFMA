
package Questao05;

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
        
        Grafo grafo = new Grafo(true,true);
        Grafo.Vertice origem = null;
        Grafo.Vertice destino = null; 
        try(BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\dadosQ5.txt"))){
            String linha;
            //Lê as duas primeiras linhas. Origem e destino, respectivamente
            linha = arquivo.readLine();
            origem = grafo.adicionarVertice(linha);
            linha = arquivo.readLine();
            destino = grafo.adicionarVertice(linha);
            
            while ((linha = arquivo.readLine()) != null){
                String[] vertices = linha.split(";");
                Grafo.Vertice v1 = grafo.adicionarVertice(vertices[0].trim());
                Grafo.Vertice v2 = grafo.adicionarVertice(vertices[1].trim());
                int distancia = Integer.parseInt(vertices[2].trim());
                grafo.adicionarAresta(v1, v2, distancia);
            }

        } catch (IOException e) {
            System.out.println("Erro de leitura: " + e);
        }
        grafo.imprimir();
        Grafo.BellmanFord bellmanFord = grafo.new BellmanFord(grafo, origem, destino);
        Lista<Vertice> caminho = bellmanFord.encontrarCaminho();
        caminho.imprimir();
        /*Grafo grafo = new Grafo(true, true);
        Grafo.Vertice origem = grafo.adicionarVertice("0");
        Grafo.Vertice destino = grafo.adicionarVertice("4");
        Grafo.Vertice a = grafo.adicionarVertice("1");
        Grafo.Vertice b = grafo.adicionarVertice("2");
        Grafo.Vertice c = grafo.adicionarVertice("3");
        grafo.adicionarAresta(origem, a, 1);
        grafo.adicionarAresta(origem, b, 4);
        grafo.adicionarAresta(a, b, 2);
        grafo.adicionarAresta(a, c, 5);
        grafo.adicionarAresta(b, destino, 1);
        grafo.adicionarAresta(c, destino, 3);

        Grafo.BellmanFord bellmanFord = grafo.new BellmanFord(grafo, origem, destino);
        Lista<Vertice> caminho = bellmanFord.encontrarCaminho();
        caminho.imprimir();

        */
        
    }
    
}