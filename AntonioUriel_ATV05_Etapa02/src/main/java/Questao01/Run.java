
package Questao01;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Uriel Ewerton
 */
public class Run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cria a árvores binárias de exemplo 
        ArvoreBinaria binariaX = new ArvoreBinaria(); 
        ArvoreAVL avl = new ArvoreAVL();      
        
        /**
         * Insere numeros que formam o mesmo formato que seria gerado em uma 
         * árvore avl, quando inseridas numa árvore binária normal. 
         * 
         * Uma boa sequencia para testar é 4, 2, 6, 1, 3, 5 e 7.
         * 
         * Insere também numa árvore avl para comparação. 
         * 
         * Para trocar para sequencia que não fique no mesmo formato,
         * substitua o arquivo abaixo para "DadosArvoreDesbalanceada.txt".
         */
        try (Scanner scannerArquivo = new Scanner(new File("src\\main\\java\\Questao01\\DadosArvoreDesbalanceada.txt"))) {
            while (scannerArquivo.hasNextInt()) {
                int value = scannerArquivo.nextInt();
                binariaX.inserir(value);
                avl.inserir(value);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Impressão para testar o formato
        binariaX.prettyPrint();
        System.out.println();
        avl.prettyPrint();
        
        
        // Verifica se a árvore é AVL
        boolean isAVL = verifyAVL(binariaX);
        boolean converter = false;
        // Imprime o resultado e registra se a árvore será transformada em AVL
        if (isAVL) {
            System.out.println("Provavelmente é árvore é AVL.");
            System.out.println("Você deseja copiar os dados para uma estrutura"
                    + " de árvore AVL? (s/n)");
            boolean valido = false;
            while(valido == false){
                String result = scanner.next().toLowerCase();
                if("s".equals(result)){
                    converter = true;
                    valido = true;
                } else if("n".equals(result)){
                    converter = false;
                    valido = true;
                } else
                    System.out.println("Entrada inválida. Somente 's' ou 'n'.");
            }
        } else {
            System.out.println("A árvore não é AVL.");
        }
        //caso verdadeiro transforma em AVL.
        if(converter){
            ArvoreAVL arvrConvertida = new ArvoreAVL();
            Iterator<NoArvore> iterator = inorder(binariaX);
            //percorre a arvore binária e copia os itens
            while (iterator.hasNext()) {
                NoArvore no = iterator.next();
                //copia de binariaX para arvrConvertida
                arvrConvertida.inserir((int) no.getElemento());
            }
            //Print testando a conversão de estrutura
            arvrConvertida.prettyPrint();
        }
    }
    /**
    * Verifica se uma árvore binária é uma árvore AVL.
    *
    * @param arvore A árvore binária a ser verificada.
    * @return true se a árvore for AVL, false caso contrário.
    */
    public static boolean verifyAVL(ArvoreBinaria arvore) {
        
        Iterator<NoArvore> iterator = inorder(arvore);
        boolean isAVL = true;
        while (iterator.hasNext()) {
            NoArvore no = iterator.next();
            int heightLeft = getHeight(no.getEsquerda());
            int heightRight = getHeight(no.getDireita());
            int difference = heightLeft - heightRight;
            if (difference > 1 || difference < -1) {
                isAVL = false;
                break;
            }
        }

        return isAVL;
    }
    
    /**
    * Retorna a altura de um nó em uma árvore binária.
    *
    * @param no O nó cuja altura será calculada.
    * @return A altura do nó.
    */
    private static int getHeight(NoArvore no) {
        if (no == null) {
            return 0;
        } else {
            return Math.max(getHeight(no.getEsquerda()), getHeight(no.getDireita())) + 1;
        }
    }
    /**
    * Retorna um iterador inorder para uma árvore binária.
    *
    * @param arvore A árvore binária para a qual o iterador será retornado.
    * @return Um iterador inorder para a árvore binária.
    */
    public static Iterator<NoArvore> inorder(ArvoreBinaria arvore) {
        return new InorderIterator(arvore);
    }
    /**
    * Uma classe interna que implementa um iterador inorder para uma árvore binária.
    */
    private static class InorderIterator implements Iterator<NoArvore> {

        private Stack<NoArvore> stack = new Stack<>();

        public InorderIterator(ArvoreBinaria arvore) {
            pushLeft(arvore.getRaiz());
        }
        /**
        * Verifica se há mais elementos na árvore binária para iterar.
        *
        * @return true se houver mais elementos, false caso contrário.
        */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        /**
        * Retorna o próximo elemento na árvore binária.
        *
        * @return O próximo elemento na árvore binária.
        */
        @Override
        public NoArvore next() {
            if (!hasNext()) {
                return null;
            }

            NoArvore no = stack.pop();
            pushLeft(no.getDireita());

            return no;
        }
        /**
        * Adiciona todos os nós à esquerda de um nó dado à pilha.
        *
        * @param no O nó a partir do qual os nós à esquerda serão adicionados à pilha.
        */
        private void pushLeft(NoArvore no) {
            while (no != null) {
                stack.push(no);
                no = no.getEsquerda();
            }
        }
    }
}
