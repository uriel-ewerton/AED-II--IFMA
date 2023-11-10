package Questao01;

public class ArvoreAVL {
    private Nodo raiz;

    private int altura(Nodo nodo) {
        if (nodo == null) return 0;
        return nodo.getAltura();
    }

    private int maximo(int a, int b) {
        return (a > b) ? a : b;
    }

    private Nodo rotacaoDireita(Nodo y) {
        Nodo x = y.getEsquerda();
        Nodo T2 = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(T2);
        
        y.setAltura(maximo(altura(y.getEsquerda()), altura(y.getDireita())) + 1);
        x.setAltura(maximo(altura(x.getEsquerda()), altura(x.getDireita())) + 1);

        return x;
    }

    private Nodo rotacaoEsquerda(Nodo x) {
        Nodo y = x.getDireita();
        Nodo T2 = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(T2);

        x.setAltura(maximo(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        y.setAltura(maximo(altura(y.getEsquerda()), altura(y.getDireita())) + 1);

        return y;
    }

    private int fatorBalanceamento(Nodo nodo) {
        if (nodo == null) return 0;
        return altura(nodo.getEsquerda()) - altura(nodo.getDireita());
    }

    public Nodo inserir(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);

        if (valor < nodo.getValor()) {
            nodo.setEsquerda(inserir(nodo.getEsquerda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDireita(inserir(nodo.getDireita(), valor));
        } else {
            return nodo; 
        }

        nodo.setAltura(1 + maximo(altura(nodo.getEsquerda()), altura(nodo.getDireita())));

        
        int balanceamento = fatorBalanceamento(nodo);

        // Caso esquerda-esquerda
        if (balanceamento > 1 && valor < nodo.getEsquerda().getValor()) {
            return rotacaoDireita(nodo);
        }
        // Caso direita-direita
        if (balanceamento < -1 && valor > nodo.getDireita().getValor()) {
            return rotacaoEsquerda(nodo);
        }
        // Caso esquerda-direita
        if (balanceamento > 1 && valor > nodo.getEsquerda().getValor()) {
            nodo.setEsquerda(rotacaoEsquerda(nodo.getEsquerda()));
            return rotacaoDireita(nodo);
        }
        // Caso direita-esquerda
        if (balanceamento < -1 && valor < nodo.getDireita().getValor()) {
            nodo.setDireita(rotacaoDireita(nodo.getDireita()));
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    private void imprimirEmOrdem(Nodo nodo) {
        if (nodo != null) {
            imprimirEmOrdem(nodo.getEsquerda());
            System.out.print(nodo.getValor() + " ");
            imprimirEmOrdem(nodo.getDireita());
        }
    }

    public void imprimirArvore() {
        imprimirEmOrdem(raiz);
    }
    public void prettyPrint() {
        printHelper(this.raiz, "", true);
    }
    private void printHelper(Nodo currPtr, String indent, boolean last) {
            // print the tree structure on the screen
            if (currPtr != null) {
                System.out.print(indent);
                if (last) {
                    System.out.print("R----");
                    indent += "     ";
                } else {
                    System.out.print("L----");
                    indent += "|    ";
                }

                System.out.println(currPtr.getValor());

                printHelper(currPtr.getEsquerda(), indent, false);
                printHelper(currPtr.getDireita(), indent, true);
            }
        }
    
    private Nodo encontrarMenorValor(Nodo nodo) {
        Nodo atual = nodo;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public Nodo remover(Nodo nodo, int valor) {
        if (nodo == null) return nodo;

        if (valor < nodo.getValor()) {
            nodo.setEsquerda(remover(nodo.getEsquerda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDireita(remover(nodo.getDireita(), valor));
        } else {

            // Nó com apenas um filho ou nenhum filho
            if (nodo.getEsquerda() == null || nodo.getDireita() == null) {
                Nodo temp = null;
                if (nodo.getEsquerda() == null) {
                    temp = nodo.getDireita();
                } else {
                    temp = nodo.getEsquerda();
                }

                // Caso de nenhum filho
                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else { // Caso de um filho
                    nodo = temp; // Copia o conteúdo do filho para o nó
                }
            } else {
                // Nó com dois filhos: obtemos o sucessor e o substituímos pelo nó atual
                Nodo temp = encontrarMenorValor(nodo.getDireita());
                nodo.setValor(temp.getValor());
                nodo.setDireita(remover(nodo.getDireita(), temp.getValor()));
            }
        }

        
        if (nodo == null) return nodo;

        nodo.setAltura(1 + maximo(altura(nodo.getEsquerda()), altura(nodo.getDireita())));

        int balanceamento = fatorBalanceamento(nodo);

        // Caso esquerda-esquerda
        if (balanceamento > 1 && fatorBalanceamento(nodo.getEsquerda()) >= 0) {
            return rotacaoDireita(nodo);
        }
        // Caso direita-direita
        if (balanceamento < -1 && fatorBalanceamento(nodo.getDireita()) <= 0) {
            return rotacaoEsquerda(nodo);
        }
        // Caso esquerda-direita
        if (balanceamento > 1 && fatorBalanceamento(nodo.getEsquerda()) < 0) {
            nodo.setEsquerda(rotacaoEsquerda(nodo.getEsquerda()));
            return rotacaoDireita(nodo);
        }
        // Caso direita-esquerda
        if (balanceamento < -1 && fatorBalanceamento(nodo.getDireita()) > 0) {
            nodo.setDireita(rotacaoDireita(nodo.getDireita()));
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }
}
