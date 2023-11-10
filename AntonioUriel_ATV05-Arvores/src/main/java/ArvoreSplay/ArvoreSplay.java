package ArvoreSplay;
/**
 *
 * @author Uriel Ewerton
 */
class No<T extends Comparable<T>> {
    private T info;
    private No<T> filhoEsq;
    private No<T> filhoDir;
    private No<T> pai;
    
    public No(T info) {
        this.info = info;
        this.filhoEsq = this.filhoDir = this.pai = null;
    }
    
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public No<T> getFilhoEsq() {
        return filhoEsq;
    }

    public void setFilhoEsq(No<T> filhoEsq) {
        this.filhoEsq = filhoEsq;
    }

    public No<T> getFilhoDir() {
        return filhoDir;
    }

    public void setFilhoDir(No<T> filhoDir) {
        this.filhoDir = filhoDir;
    }

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }
}

public class ArvoreSplay<T extends Comparable<T>> {
    private No<T> raiz;
    
    private void rotacaoEsq(No<T> x) {
        No<T> y = x.getFilhoDir();
        x.setFilhoDir(y.getFilhoEsq());
        if (y.getFilhoEsq() != null) {
            y.getFilhoEsq().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            raiz = y;
        } else if (x == x.getPai().getFilhoEsq()) {
            x.getPai().setFilhoEsq(y);
        } else {
            x.getPai().setFilhoDir(y);
        }
        y.setFilhoEsq(x);
        x.setPai(y);
    }

    private void rotacaoDir(No<T> x) {
        No<T> y = x.getFilhoEsq();
        x.setFilhoEsq(y.getFilhoDir());
        if (y.getFilhoDir() != null) {
            y.getFilhoDir().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai() == null) {
            raiz = y;
        } else if (x == x.getPai().getFilhoEsq()) {
            x.getPai().setFilhoEsq(y);
        } else {
            x.getPai().setFilhoDir(y);
        }
        y.setFilhoDir(x);
        x.setPai(y);
    }

    private void splay(No<T> x) {
        while (x.getPai() != null) {
            if (x.getPai().getPai() == null) {
                if (x == x.getPai().getFilhoEsq()) {
                    rotacaoDir(x.getPai());
                } else {
                    
                    rotacaoEsq(x.getPai());
                }
            } else if (x == x.getPai().getFilhoEsq() && x.getPai() == x.getPai().getPai().getFilhoEsq()) {
                rotacaoDir(x.getPai().getPai());
                rotacaoDir(x.getPai());
            } else if (x == x.getPai().getFilhoDir() && x.getPai() == x.getPai().getPai().getFilhoDir()) {
                
                rotacaoEsq(x.getPai().getPai());
                
                rotacaoEsq(x.getPai());
            } else if (x == x.getPai().getFilhoDir() && x.getPai() == x.getPai().getPai().getFilhoEsq()) {
                
                rotacaoEsq(x.getPai());
                rotacaoDir(x.getPai());
            } else {
                rotacaoDir(x.getPai());
                rotacaoEsq(x.getPai());
            }
        }
    }

    public void inserir(T info) {
        No<T> novoNo = new No<>(info);
        if (raiz == null) {
            raiz = novoNo;
            return;
        }

        No<T> atual = raiz;
        No<T> pai = null;
        while (atual != null) {
            pai = atual;
            if (info.compareTo(atual.getInfo()) < 0) {
                atual = atual.getFilhoEsq();
            } else if (info.compareTo(atual.getInfo()) >= 0) {
                atual = atual.getFilhoDir();
            } else {
                // Não permite chaves repetidas;
                // Para permitir, basta alterar adicionar ou remover a igualdade
                // no else if acima;
                // Ultima config: permitindo repetidas.
                // obs: não devidamente testado kkkk
                return;
            }
        }

        if (info.compareTo(pai.getInfo()) < 0) {
            pai.setFilhoEsq(novoNo);
        } else {
            pai.setFilhoDir(novoNo);
        }
        novoNo.setPai(pai);

        // Aplica Splay, levando o nó recém inserido até a raiz
        splay(novoNo);
    }
    
    
    public T acessar(T info) {
        No<T> noAux = buscar(info);
        if (noAux != null) {
            splay(noAux);
            return info;
        }
        return null;
    }

    
    public void deletar(T info) {
        No<T> noAux = buscar(info);
        if (noAux == null) {
            // chave não encontrada
            return;
        }
        splay(noAux);
        if (noAux.getFilhoEsq() == null) {
            raiz = noAux.getFilhoDir();
            if (raiz != null) {
                raiz.setPai(null);
            }
        } else {
            No<T> ultimaEsquerda = noMaisAlto(noAux.getFilhoEsq());
            splay(ultimaEsquerda);
            ultimaEsquerda.setFilhoDir(noAux.getFilhoDir());
            if (noAux.getFilhoDir() != null) {
                noAux.getFilhoDir().setPai(ultimaEsquerda);
            }
            raiz = ultimaEsquerda;
            ultimaEsquerda.setPai(null);
        }
    }

    
    private No<T> buscar(T info) {
        No<T> atual = raiz;
        while (atual != null) {
            if (info.compareTo(atual.getInfo()) < 0 ) {
                atual = atual.getFilhoEsq();
            } else if (info.compareTo(atual.getInfo()) > 0) {
                atual = atual.getFilhoDir();
            } else {
                return atual; // Chave encontrada
            }
        }
        return null; // Chave não encontrada
    }

    private No<T> noMaisAlto(No<T> noAux) {
        while (noAux.getFilhoDir() != null) {
            noAux = noAux.getFilhoDir();
        }
        return noAux;
    }
    
    public void imprimir() {
        inOrder(raiz);
    }

    private void inOrder(No<T> noAux) {
        if (noAux != null) {
            inOrder(noAux.getFilhoEsq());
            System.out.println(noAux.getInfo());
            inOrder(noAux.getFilhoDir());
        }
    }
    
}
