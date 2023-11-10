
package questao02;

import util.Reader;

/**
 *
 * @author uriel
 */
public class Run {
    public static void main(String[] args){
        //Lista base do programa
        ListaDE<Produto> lista = new ListaDE();
        
        //Leitura do arquivo de entrada e inserção na lista
        Reader entrada = new Reader("src/entrada/dadosQ2.txt");
        while(true){
            String prod;
            prod = entrada.lerProximaLinha();
            if(prod != null){
                String[] camposProd = prod.split(",");
                String id = camposProd[0];
                String descricao = camposProd[1];
                double valor = Double.parseDouble(camposProd[2]);
                lista.inserir(new Produto(id, descricao, (double)valor));
            }
            else{
                entrada.fecharArquivo();
                break;
            }
        }
        //Rotina de impressões após cada tipo de ordenação
        Ordenacao ord = new Ordenacao();
        ord.ordemAlfabetica(lista); lista.imprimir(); System.out.println();
        ord.crescente(lista); lista.imprimir(); System.out.println();
        ord.decrescente(lista); lista.imprimir(); System.out.println();
       
        
        /*Trecho de código opcional para escolher a função a ser executada
        com base em um arquivo exec
        
        //Leitura do arquivo de execução
        Reader leitor = new Reader("src/questao02/exec.txt");
        String exec = leitor.lerProximaLinha();
        Ordenacao ord = new Ordenacao();
        
        switch(exec.toLowerCase()){
            case "alfabetica"  -> {
                ord.ordemAlfabetica(lista);
                lista.imprimir();
            }
            case "crescente" -> {
                ord.crescente(lista);
                lista.imprimir();
            }
            case "decrescente" -> {
                ord.decrescente(lista);
                lista.imprimir();
            }
            default -> System.out.println("Comando inválido");
        }
        */
    }
}
