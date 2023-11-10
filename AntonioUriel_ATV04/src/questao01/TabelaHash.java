
package questao01;
/**
 *
 * @author uriel
 */
@SuppressWarnings("EqualsAndHashcode")
/**
 * Classe suporte para inserir chave genérica e elemento genérico na tabela.
 */
class Item<K,E>{
    private K chave;
    private E elemento;

    public Item(K chave, E elemento) {
        this.chave = chave;
        this.elemento = elemento;
    }
    
    public K getChave() {
        return chave;
    }

    public E getElemento() {
        return elemento;
    }

    @Override
    public String toString() {
        return "Chave: " + chave 
                + "  Elemento: " + elemento;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return chave.equals(item.chave) &&
               elemento.equals(item.elemento);
    }
}
/**
 * Estrutura abstrata de dados "Tabela hash"
 * <p>Implementa a estrutura sobre um array de listas, utilizando função hash
 * de método multiplicativo para definir buckets para cada inserção. 
 * 
 * <p>Cada posição do array corresponde a um bucket e as colisões são 
 * armazenadas na lista contida na posição.
 * 
 * @author Uriel Ewerton
 * @param <K> Tipo genérico utilizado para chaves.
 * @param <E> Tipo genérico utilizado para elementos.  
 * @param <T> Tipo genérico utilizado em nodos das listas internas.
 */
public class TabelaHash <K,E,T> {
    
    private Lista[] tabela;
    private final int TAMANHO = 11;
    private final double LIMITE_CARGA = 0.7;
    private int tamanhoAtual;
    private int ocupacao;
   
    
    //CONSTRUTOR
    /**
     * Inicia o array de listas com tamanho baseado em constante "TAMANHO" e 
     * registra o tamanho atual da tabela em "tamanhoAtual".
     */
    public TabelaHash() {
        tabela = new Lista[TAMANHO]; 
        this.tamanhoAtual = TAMANHO;
    }
    
    //MÉTODOS
    /**
     * Receve valor genérico como chave e processa retornando uma posição 
     * no vetor. Caso o valor seja numérico, aplica um calculo diretamente.
     * Caso possua caracteres não numéricos, aplica um algoritmo de conversão 
     * antes de aplicar o cálculo.
     * 
     * @param chave valor genérico representando uma chave.
     * @return int posição dentro do escopo do vetor de listas "tabela".
     */
    private int funcaoHash(K chave){
        String str = chave.toString();
        
        int h = tamanhoAtual;
        double A = 0.6180339887;
        int posicao = 0;
        if(str.matches("-?\\d+(\\.\\d+)?")){
            posicao = (int)Math.floor(h * (Long.parseLong(str) * A % 1));
        } else{
            posicao = (int)Math.floor(h * (converteChave(chave) * A % 1));
        }
        return posicao;
    }
    /**
     * Processa uma chave, convertendo cada caractere para seu valor ascii.
     * 
     * @param chave valor genérico com algum caractere não numérico, 
     * representando uma chave.
     * @return chave convertida para Long.
     */
    private long converteChave(K chave){
        StringBuilder sb = new StringBuilder();
        String a = chave.toString();
        
        for (char character : a.toCharArray()) {
            sb.append((int) character);
            if(sb.toString().length() > 10){
                break;
            }
        }    
        return Long.parseLong(sb.toString());
    }
    
    /**
     * Insere item (classe Item) na tabela, usando uma chave nos campos "chave"
     * e "elemento", usando função de inserção da classe Lista.
     * 
     * <p>Uso no caso de objeto igual a chave, por exemplo para inserir uma 
     * sequencia de números dentro da tabela.
     * 
     * <p>Solicita o rehashing, que será aplicado caso necessário.
     * 
     * @param chave valor genérico representando uma chave.
     */
    public void inserir(K chave){
        Item<K,E> item = new Item(chave, chave);
        int posicao = funcaoHash(item.getChave());
        
        if(tabela[posicao]!= null){
            tabela[posicao].inserir( item);
        } else{
            Lista bucket = new Lista();
            bucket.inserir(item);
            this.tabela[posicao] = bucket;
            this.ocupacao++;
        }
        rehashing();
    }
    
    /**
     * Insere item (classe Item) na tabela usando função de inserção da classe
     * Lista.
     * 
     * <p>Uso no caso padrão, onde há chave e elemento.
     * 
     * <p>Solicita o rehashing, que será aplicado caso necessário.
     * 
     * @param chave valor genérico representando uma chave.
     */
    public void inserir(K chave, E elemento){
        Item<K,E> item = new Item(chave, elemento);
        int posicao = funcaoHash(chave);
        
        if(tabela[posicao]!= null){
            tabela[posicao].inserir(item);
        } else{
            Lista bucket = new Lista();
            bucket.inserir(item);
            this.tabela[posicao] = bucket;
            this.ocupacao++;
        }
        rehashing();
        
    }
    
    /**
     * Remove item (classe Item) da tabela usando função de remoção da classe
     * Lista, especificando pela chave.
     * 
     * @param chave valor genérico representando uma chave.
     * @return verdadeiro caso o item tenha sido encontrado.
     */
    public boolean remover(K chave){
        Item<K,E> item = new Item(null, null);
        int posicao = funcaoHash(chave);
        
        if(tabela[posicao] != null){
            NoDuplo<T> aux = this.tabela[posicao].getPrimeiro();
            while(aux != null){
                item = (Item<K, E>) aux.getElemento();
                if(item.getChave().equals(chave) ){
                    tabela[posicao].remover(item);
                    return true;
                }
                aux = aux.getProximo();
            }   
        } 
        return false;
    }
    
    /**
     * Remove item (classe Item) da tabela usando função de remoção da classe
     * Lista, especificando pela chave e pelo elemento.
     * 
     * @param chave valor genérico representando uma chave.
     * @param elemento valor genérico representando um elemento.
     * @return verdadeiro caso o item tenha sido encontrado.
     */
    public boolean remover(K chave, E elemento){
        Item<K,E> item = new Item(chave, elemento);
        int posicao = funcaoHash(chave);
        
        if(tabela[posicao]!= null){
            tabela[posicao].remover(item);
            if(tabela[posicao].getTamanho()==0){
                tabela[posicao] = null;
            }
            return true;
        } else
            return false;
    }
    
    /**
     * Busca por item (classe Item) da tabela, especificando pela chave.
     * 
     * @param chave valor genérico representando uma chave.
     */
    public void buscar(K chave){
        Item<K,E> item = new Item(null, null);
        boolean encontrado = false;
        int posicao = funcaoHash(chave);
        
        if(tabela[posicao] != null){
            NoDuplo<T> aux = this.tabela[posicao].getPrimeiro();
            while(aux != null){
                item = (Item<K, E>) aux.getElemento();
                if(item.getChave().equals(chave) ){
                    System.out.println("Encontrado: \n  " + item.toString());
                    encontrado = true;
                    break;
                }
                aux = aux.getProximo();
            }    
            
        } 
        if(!encontrado){
            System.out.println("Não encontrado.");
        }
    }
    /**
     * Busca por item (classe Item) da tabela, especificando pela chave e pelo
     * elemento.
     * 
     * @param chave valor genérico representando uma chave.
     * @param elemento valor genérico representando um elemento.
     */
    public void buscar(K chave, E elemento){
        Item<K,E> item = new Item(chave, elemento);
        int posicao = funcaoHash(chave);
        
        if(tabela[posicao] != null && tabela[posicao].buscarInterno(item)!= null){
            System.out.println(item.toString());
        } else
            System.out.println("Não encontrado.");
    }
    
    /**
     * Imprime todos os buckets e tudo que esta contido em cada um.
     */
    public void imprimir(){
        System.out.println("Todos os itens da tabela: ");
        for(int i = 0; i < this.tamanhoAtual; i++){
            if(tabela[i] != null){
                NoDuplo<T> aux = tabela[i].getPrimeiro();
                System.out.println("-> Bucket " + i);
                while(aux != null){
                    System.out.println("   " + aux.getElemento().toString());
                    aux = aux.getProximo();
                }
            }else
                System.out.println("-> Bucket " + i + "\n" + "   null");
           
        }
    }
    
    /**
     * Caso haja sobrecarga, baseada no valor da constante "LIMITE_CARGA",
     * executa o rehashing, ou seja, cria outra tabela hash e insere todos 
     * os elementos da tabela anterior na nova, passando cada um novamente
     * pela função de hash.
     * 
     * <p>Garante que o tamanho seja um número primo usando a função "isPrime".
     */
    private void rehashing (){
        if(verificaCarga() == true){
            int tamanhoAntigo = this.tamanhoAtual;
            this.tamanhoAtual *= 2;
            while(!isPrime(this.tamanhoAtual)){
                this.tamanhoAtual++;
            }
            Lista<T>[] tabelaNova =  new Lista[this.tamanhoAtual];
            this.ocupacao = 0;
            for(int i=0; i < tamanhoAntigo; i++){
                if(this.tabela[i]!=null){
                    NoDuplo<T> aux = this.tabela[i].getPrimeiro();
                    while(aux !=null){
                        Item<K,E> item = (Item<K,E>) aux.getElemento();
                        int posicaoNova = funcaoHash(item.getChave());
                        
                        if(tabelaNova[posicaoNova] != null){
                            tabelaNova[posicaoNova].inserir((T) item);
                        } else{
                            Lista bucket = new Lista();
                            bucket.inserir(item);
                            tabelaNova[posicaoNova] = bucket;
                            this.ocupacao++;
                        }
                        aux = aux.getProximo();
                    }
                }
            }
            this.tabela = tabelaNova;
        }
    }
    /**
     * Executa a verificação de sobrecarga da tabela, em relação à quantidade
     * de buckets ocupados, registrados pela variável "ocupacao".
     * @return verdadeiro em caso de sobrecarga.
     */
    private boolean verificaCarga(){
        double carga = (double) this.ocupacao / (double) this.tamanhoAtual ;
        return carga > this.LIMITE_CARGA ;
    }
    /**
     * Testa se determinado valor é número primo.
     * @param numero int a ser testado.
     * @return verdadeiro caso o valor seja número primo.
     */
    public static boolean isPrime(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getTamanhoAtual() {
        return tamanhoAtual;
    }

    public Lista[] getTabela() {
        return tabela;
    }
}



