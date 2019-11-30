import java.util.ArrayList;

public class ComandoBloco  extends No {
    private int comando = -1;
    private int comandoBloco = -1;
    private boolean lambda;

    public ComandoBloco(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public void updateAsm() {
        if (lambda) {
            return;
        }
        setAsm(new ArrayList<>());
        Tree.tree.get(comando).updateAsm();
        Tree.tree.get(comandoBloco).updateAsm();
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm(Tree.tree.get(comandoBloco).getAsm());
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        else if (classe.equals("COMANDOBLOCO")) {
            comandoBloco = posicaoNoArrayTree;
        }
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        if (comando == -1) return true;
        if (comandoBloco == -1) return true;
        return false;
    }
}
