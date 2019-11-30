import java.util.ArrayList;

public class CondicionalSenao  extends No {
    private int comando = -1;
    //lambda??
    private boolean lambda;

    public CondicionalSenao(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (comando == -1) {
            comando = posicaoNoArrayTree;
        }
        return false;
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        if (lambda) {
            return;
        }
        Tree.tree.get(comando).updateAsm();
        addAsm(Tree.tree.get(comando).getAsm());
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        return comando == -1;
    }
}
