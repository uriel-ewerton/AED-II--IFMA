package questao1;

import utilitario.GerenciadorDeArquivos;

/**
 *
 * @author uriel
 */

public class Run {
    
    public static void main(String[] args) {
        GerenciadorDeArquivos reader = new GerenciadorDeArquivos("src\\entrada\\dadosQ1");
        String entrada = reader.lerProximaLinha();
        Lista<Integer> lista = new Lista<>();
        
        String[] split = entrada.split(">");
        for(String parte : split ){
            lista.inserir(Integer.parseInt(parte));
            //System.out.println(parte); caso deseje confirmar o que foi lido
        }
        System.out.println("Quantidade de 1s = " + Contador.conta1(lista.getPrimeiro()));
    }
    
}
