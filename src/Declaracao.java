import java.util.ArrayList;

public class Declaracao  extends No {
    private int identificador = -1;
    private int comando = -1;
    private int declaracao = -1;
    private boolean lambda;

    public Declaracao(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        if (lambda) {
            return;
        }
        Tree.tree.get(comando).updateAsm();
        Tree.tree.get(declaracao).updateAsm();
        String identif = Tree.tree.get(identificador).getNome();
        addAsm(identif + " proc");
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm("ret");
        addAsm(identif + " endp");
        addAsm(Tree.tree.get(declaracao).getAsm());
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (identificador == -1) {
            identificador = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1) {
            comando = posicaoNoArrayTree;
            return true;
        }
        if (declaracao == -1) {
            declaracao = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        return (identificador == -1 || comando == -1 || declaracao == -1);
    }
}
