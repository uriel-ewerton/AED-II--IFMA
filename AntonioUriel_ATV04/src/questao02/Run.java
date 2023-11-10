package questao02;

import questao01.Lista;
import questao01.NoDuplo;
import questao01.TabelaHash;

/**
 *
 * @author Uriel Ewerton
 */
public class Run {
    /**
     * Insere um array int em uma tabela hash (classe TabelaHash) e usa o método
     * "checaDuplicados" para ler a tabela, identificar os itens duplicados e 
     * printar o resultado.
     * @param args (não utilizado)
     */
    public static void main(String[] args) {
        TabelaHash t = new TabelaHash();

        int[] entrada1 = {5, 3, 2, 7, 1, 3, 9};
        int[] entrada2 = {5, 3, 2, 7, 1, 3, 9, 11, 2, 1};
        int[] entrada3 = {14, 75, 2, 5, 42, 99, 14, 8, 7, 5, 2, 14, 89, 3, 47, 65, 92, 1};
        
        /* alteração manual de entrada, substituir "entrada1" */
        int[] entrada = entrada1;

        for (int a : entrada) {
            t.inserir(a);
        }
        
        checaDuplicados(t);
    }

    /**
     * Copia o array de listas (classe Lista) interno da instância da classe
     * TabelaHash para outro array de listas a fim de facilitar a manipulação.
     *
     * <p>Guarda elementos duplicados encontrados ao percorrer o array de listas.
     * 
     * <p>Buckets com mais de um elemento, são vasculhados usando dois nodos
     * auxiliares. (Essa operação é custosa, mas se entende que seja mais
     * eficiente do que testar todos os elementos de um array, tendo em vista
     * que pelo uso da tabela hash, cada duplicado com certeza estará no mesmo 
     * bucket que sua réplica.)
     * 
     * <p>Printa os elementos duplicados.
     *
     * @param <T> generic para conexão entre classe Lista e classe TabelaHash
     * @param t instancia da tabela hash
     */

    public static <T> void checaDuplicados(TabelaHash t) {
        Lista[] tabela = t.getTabela();
        int[] duplicados = new int[t.getTamanhoAtual()/2];
        int contador = 0;
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null && tabela[i].getTamanho() > 1) {
                NoDuplo<T> aux = tabela[i].getPrimeiro();
                while(aux != null){
                    NoDuplo<T> aux2 = aux.getProximo();
                    while(aux2 != null){
                        if(aux.getElemento().equals(aux2.getElemento()) ){
                            if(!estaEmDuplicados(duplicados,aux.getElemento())){
                                String elemento = aux.getElemento().toString();
                                String[] partes = elemento.split("o: ");
                                duplicados[contador] = Integer.parseInt(partes[1]);
                                contador++;
                            }
                        }   
                        aux2 = aux2.getProximo();
                    }
                    aux = aux.getProximo();
                }   
            } 
        }
        System.out.print("Duplicado(s): ");
        for (int x : duplicados) {
            if (x != 0) {
                System.out.print(x + " | ");
            }
        }
    }
    /**
     * Testa se o duplicado já foi encontrado e inserido dentro do array
     * "duplicados".
     * @param <T> tipo genérico que mantem consistencia entre as estruturas
     * @param d   array a ser verificado
     * @param e   item a ser testado
     * @return    verdadeiro caso o item esteja presente em "int[] d".
     */
    public static <T> boolean estaEmDuplicados(int[] d, T e){
        String elemento = e.toString();
        String[] partes = elemento.split("o: ");
        int el = Integer.parseInt(partes[1]);
        for(int a : d){
            if(a == el){
                return true;
            }
        }
        return false;
    }
}
