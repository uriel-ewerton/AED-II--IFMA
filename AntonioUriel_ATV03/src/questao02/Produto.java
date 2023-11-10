
package questao02;

/**
 *
 * @author uriel
 */
public class Produto {
    private String id;
    private String descricao;
    private double valor;

    public Produto(String id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto: " + "id = " + id + ", descricao = " + descricao + ", valor = R$ " + valor;
    }
    
}
