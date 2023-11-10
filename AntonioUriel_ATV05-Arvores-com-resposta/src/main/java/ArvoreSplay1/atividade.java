/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArvoreSplay1;

/**
 *
 * @author uriel
 */
class User implements Comparable<User> {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Implementação do compareTo para ordenar na árvore splay
    @Override
    public int compareTo(User otherUser) {
        return Integer.compare(this.id, otherUser.id);
    }
    
    // Implementação do toString para visualização na árvore
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
    // Restante dos métodos e atributos necessários

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
public class atividade {
    public static void main(String[] args) {
        // Criar uma árvore splay para armazenar usuários
        ArvoreSplay<User> userSplayTree = new ArvoreSplay<>();

        User user1 = new User(1, "Alice", "alice@email.com");
        User user2 = new User(2, "Bob", "bob@email.com");
        User user3 = new User(3, "Charlie", "charlie@email.com");
        User user4 = new User(4, "David", "david@email.com");
        User user5 = new User(5, "Eva", "eva@email.com");
        User user6 = new User(6, "Frank", "frank@email.com");
        User user7 = new User(7, "Grace", "grace@email.com");
        User user8 = new User(8, "Henry", "henry@email.com");
        User user9 = new User(9, "Ivy", "ivy@email.com");
        User user10 = new User(10, "Jack", "jack@email.com");

        userSplayTree.inserir(user1);
        userSplayTree.inserir(user2);
        userSplayTree.inserir(user3);
        userSplayTree.inserir(user4);
        userSplayTree.inserir(user5);
        userSplayTree.inserir(user6);
        userSplayTree.inserir(user7);
        userSplayTree.inserir(user8);
        userSplayTree.inserir(user9);
        userSplayTree.inserir(user10);

        // Acesso desordenado de 10 usuários
        userSplayTree.acessar(user5);
        userSplayTree.acessar(user2);
        userSplayTree.acessar(user7);
        userSplayTree.acessar(user9);
        userSplayTree.acessar(user1);
        userSplayTree.acessar(user9);
        userSplayTree.acessar(user8);
        userSplayTree.acessar(user3);
        userSplayTree.acessar(user10);
        userSplayTree.acessar(user9);
        userSplayTree.acessar(user6);
        userSplayTree.acessar(user4);
        userSplayTree.acessar(user8);
        // Imprimir a árvore splay
        System.out.println("Árvore Splay após operações:");
        userSplayTree.imprimirPorNiveis();
        
    }
}
