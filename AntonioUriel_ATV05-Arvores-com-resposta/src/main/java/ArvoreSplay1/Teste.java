
package ArvoreSplay1;

/**
 *
 * @author Uriel Ewerton
 */
public class Teste {

    public static void main(String[] args) {
        ArvoreSplay tree = new ArvoreSplay();
        tree.inserir(2);
        tree.inserir(3);
        tree.inserir(1);
        tree.inserir(8);
        tree.inserir(9);
        tree.inserir(16);
        tree.inserir(17);
        tree.inserir(15);
        tree.inserir(0);
        tree.inserir(9);
        tree.imprimir();
        tree.deletar(2);
        tree.acessar(16);
        tree.imprimir();
        
        /*
        s.inserir(1);
        s.imprimir();
        System.out.println("----");
        s.inserir(3);
        s.imprimir();
        System.out.println("----");
        s.inserir(2);
        s.imprimir();
        System.out.println("----");
        s.inserir(4);
        s.imprimir();
        System.out.println("----");
        s.deletar(2);
        s.imprimir();
        System.out.println("----");
        s.acessar(3);
        s.imprimir();
        System.out.println("----");
        */
       
    }
}
