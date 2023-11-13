
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
        
        System.out.println("Mapa da cidade:");
        grafo.imprimir();
        
        Grafo.BellmanFord bellmanFord = grafo.new BellmanFord(grafo, origem, destino);
        Grafo.BellmanFord.ResultadoBellmanFord resultado = bellmanFord.encontrarCaminho();
        
        System.out.println("\nCaminho mais curto entre " + 
                grafo.pesquisarVertice(origem) + " e " +
                grafo.pesquisarVertice(destino));
        
        Lista<Vertice> caminho = resultado.getCaminho();
        caminho.imprimir();

        double distanciaTotal = resultado.getDistanciaTotal();
        System.out.println("Distância Total: " + distanciaTotal + " anos luz.");
        
    }
    
}