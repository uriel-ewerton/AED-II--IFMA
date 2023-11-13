
package Questao03;

import Questao01.Grafo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Uriel Ewerton
 */
public class Run{
    public static void main (String[] args){
        Grafo grafo = new Grafo(true,false);
        try(BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\dadosQ2eQ3.txt"))){
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
        System.out.println(grafo.temCiclo(grafo) ? "O grafo contém um ciclo" : "O grafo não contém um ciclo");
        //A implementação está dentro do método temCiclo, dentro da implementação
        //do grafo.
    }
    
}