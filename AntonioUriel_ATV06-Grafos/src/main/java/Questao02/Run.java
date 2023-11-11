
package Questao02;


import Questao01.Grafo;
import Questao01.Lista;
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
        Grafo grafo = new Grafo(true,false);
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
        /*
        grafo.imprimir();
        grafo.adicionarAresta(vertice1, vertice2);
        grafo.imprimir();
        */



        Fila fila = new Fila();
    }
}
