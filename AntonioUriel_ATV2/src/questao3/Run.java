package questao3;

import utilitario.GerenciadorDeArquivos;

/**
 *
 * @author uriel
 */

public class Run {

    public static void main(String[] args) {
        GerenciadorDeArquivos reader = new GerenciadorDeArquivos("src\\entrada\\dadosQ3");
        String entrada; 
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        
        while((entrada = reader.lerProximaLinha()) != null){
            String[] partes = entrada.split(";");
            
            if(partes.length != 3){
                arvore.inserir(Integer.parseInt(entrada));
            }
            else{
                String elemNovo = partes[0].trim();
                String elemPai = partes[1].trim();
                String comando = partes[2].trim();

                if("dir".equals(comando)){
                    arvore.addDirPai(arvore.getRaiz(), Integer.parseInt(elemPai), Integer.parseInt(elemNovo));
                }
                else if("esq".equals(comando)){
                    arvore.addEsqPai(arvore.getRaiz(), Integer.parseInt(elemPai), Integer.parseInt(elemNovo));
                }
            }
         }   
        reader.fecharArquivo();
        System.out.println("Menor profundidade: " + arvore.encontrarMenorProfundidade(arvore.getRaiz()));
        arvore.imprimir();
    }
}
