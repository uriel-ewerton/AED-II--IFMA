 
package atv01;

import util.GerenciadorDeArquivos;
import util.EstruturasDeDados;
import static util.TestaNumero.isDouble;
import static util.TestaNumero.isInt;

/**
 *
 * @author uriel
 */
public class Run {
    public static void main(String[] args) {
        //- arquivos - para modificar os aquivos lidos, basta modificar nesse ponto
       
        GerenciadorDeArquivos leitorEntrada = new GerenciadorDeArquivos("src/arquivos/entradaInt.txt"); 
        GerenciadorDeArquivos leitorComandos = new GerenciadorDeArquivos("src/arquivos/exec.txt"); 
        
        //- Lê o nome da estrutura e cria uma instância - 
        
        String nomeDaEstrutura = leitorComandos.lerProximaLinha(); //absorve o nome do tipo retirado do arquivo
        EstruturasDeDados estrutura = null; //auxiliar para mecanismo de instanciação de estrutura
        System.out.println(nomeDaEstrutura);
        
        //sintaxe sugerida pela ide
        switch(nomeDaEstrutura.toLowerCase()){
            case "lista" -> estrutura = new ListaDuplamenteEncadeada<>();
            case "fila" -> estrutura = new FilaDinamica();
            case "pilha" -> estrutura = new PilhaDinamica();
            case "arvore" -> estrutura = new ArvoreBinaria();
            default -> throw new IllegalArgumentException("Entrada inválida");
        }
        EstruturasDeDados tipoEstrutura = estrutura.criarEstrutura();
        
        // - Adiciona dados de entrada à estrutura instanciada - 
        
        String entrada = leitorEntrada.lerProximaLinha();
        if(isInt(entrada)){
            tipoEstrutura.inserir(Integer.parseInt(entrada));
            while((entrada = leitorEntrada.lerProximaLinha()) != null){
                tipoEstrutura.inserir(Integer.parseInt(entrada));
            }
        }
        else if(isDouble(entrada)){
            tipoEstrutura.inserir(Double.parseDouble(entrada));
            while((entrada = leitorEntrada.lerProximaLinha()) != null){
                tipoEstrutura.inserir(Double.parseDouble(entrada));
                }
        }
        else{
            tipoEstrutura.inserir(entrada);
            while((entrada = leitorEntrada.lerProximaLinha()) != null){
                tipoEstrutura.inserir(entrada);
            }
        }
        leitorEntrada.fecharArquivo();
        
        // - Lê e executa os comandos do arquivo de execução
        String linha;
        while((linha = leitorComandos.lerProximaLinha()) != null){
             String[] partes = linha.split(";");
                
                if (partes.length == 2) {
                    String comando = partes[0].trim();
                    String objeto = partes[1].trim();
                    
                    System.out.printf("--%s: %s\n",comando,objeto);
                    
                    //trecho possivelmente refatorável com uso de Object
                    if("INSERIR".equals(comando)){
                        if(isInt(objeto)){
                            tipoEstrutura.inserir(Integer.parseInt(objeto));
                        } else if(isDouble(objeto)){
                            tipoEstrutura.inserir(Double.parseDouble(objeto));
                        }else{
                            tipoEstrutura.inserir(objeto);
                        }
                    }
                    else if("REMOVER".equals(comando)){
                        if(isInt(objeto)){
                            tipoEstrutura.remover(Integer.parseInt(objeto));
                        } else if(isDouble(objeto)){
                            tipoEstrutura.remover(Double.parseDouble(objeto));
                        }else{
                            tipoEstrutura.remover(objeto);
                        }
                    }
                    else if("BUSCAR".equals(comando)){
                        if(isInt(objeto)){
                            if(tipoEstrutura.buscar(Integer.parseInt(objeto)) != null){
                                System.out.println("Item encontrado");
                            }
                            else{
                                System.out.println("Item não encontrado");
                            }
                        }else if(isDouble(objeto)){
                            if(tipoEstrutura.buscar(Double.parseDouble(objeto)) != null){
                                System.out.println("Item encontrado");
                            }
                            else{
                                System.out.println("Item não encontrado");
                            }
                        }else{
                            if(tipoEstrutura.buscar(objeto)!= null){
                                System.out.println("Item encontrado");
                            }
                            else{
                                System.out.println("Item não encontrado");
                            }
                        }
                    }
                }   
                else if(partes.length == 1){
                    String comando = partes[0];
                    System.out.printf("--%s--\n",comando);
                    if("REMOVER".equals(comando)){
                        tipoEstrutura.remover();
                    }
                    else if("IMPRIMIR".equals(comando)){
                        tipoEstrutura.imprimir();
                    }
                    else if("GETTAMANHO".equals(comando)){
                        tipoEstrutura.getTamanho();
                    }
                }
        }
        leitorComandos.fecharArquivo();
    }
}