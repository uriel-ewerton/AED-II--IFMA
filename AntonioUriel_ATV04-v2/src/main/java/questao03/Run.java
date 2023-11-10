package questao03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import questao01.TabelaHash;

/**
 *
 * @author Uriel Ewerton
 */
public class Run {

    /**
     * Armazena em tabela hash e fornece operações para lidar com uma classe
     * Cliente, com id, nome, email e cidade.
     *
     * <p>
     * Insere dados a partir de um arquivo de entrada(entradaQ3.txt) em uma
     * tabela hash, da classe TabelaHash. Em seguida lê e executa operações a
     * partir de outro arquivo(exec.txt).
     *
     * <p>
     * Os dados de entrada seguem o padrão "id;nome;email;cidade", sempre
     * separado por ";". Exemplo: 12345678; João Silva; joaosilva@email.com; São
     * Paulo
     *
     * <p>
     * Os dados dos arquivos foram retirados dos sites "https://mockaroo.com/" e
     * "https://www.invertexto.com/numeros-aleatorios", para criar uma situação
     * sem entradas repetitivas. "entradaQ3.txt" possui 50 entradas e
     * "entradaQ3_1.txt" possui 950.
     *
     * <p>
     * As operações sobre os dados armazenados, feitas a partir do arquivo
     * "exec.txt" podem ser alteradas livremente, seguindo o padrão:
     * "função;id;nome;email;cidade", de forma semelhante ao arquivo de entrada.
     *
     * <p>
     * As funções testadas até o momento são: "inserir", "remover", "buscar" e
     * "imprimir". Somente "imprimir", não necessita o uso do padrão.
     *
     * @param args (não utilizado)
     */
    public static void main(String[] args) {
        TabelaHash t = new TabelaHash();

        //inserção de dados do arquivo de entrada
        try ( BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\entradaQ3.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String[] campos = linha.split(";");
                Cliente cliente = new Cliente(campos[0].trim(), campos[1].trim(), campos[2].trim(), campos[3].trim());
                t.inserir(cliente.getId(), cliente);
            }

        } catch (IOException e) {
            System.out.println("Erro de leitura: " + e);
        }
        //Operações a partir do arquivo de execução (exec.txt)
        try ( BufferedReader arquivo = new BufferedReader(new FileReader("src\\main\\java\\entrada\\exec.txt"))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                String[] campos = linha.split(";");
                System.out.println("\n__Função solicidada: " + campos[0].trim().toLowerCase());
                switch (campos.length) {
                    case 1:
                        if ("imprimir".equals(campos[0].trim().toLowerCase())) {
                            t.imprimir();
                        }
                        break;
                    case 2:
                        if ("buscar".equals(campos[0].trim().toLowerCase())) {
                            t.buscar(campos[1].trim());
                        }
                        if ("remover".equals(campos[0].trim().toLowerCase())) {
                            t.remover(campos[1].trim());
                        }
                        break;
                    case 5:
                        Cliente cliente = new Cliente(campos[1].trim(), campos[2].trim(), campos[3].trim(), campos[4].trim());
                        switch (campos[0].trim().toLowerCase()) {
                            case "inserir" ->
                                t.inserir(cliente.getId(), cliente);
                            case "remover" ->
                                t.remover(cliente.getId(), cliente);
                            case "buscar" ->
                                t.buscar(cliente.getId(), cliente);
                        }
                        break;
                    default:
                        System.out.println("__Erro na solicitação. ");
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
    }

}

@SuppressWarnings("EqualsAndHashcode")
class Cliente<T> {

    private T id;
    private String nome;
    private String email;
    private String cidade;

    public Cliente(T id, String nome, String email, String cidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", cidade=" + cidade + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente<?> other = (Cliente<?>) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

}
