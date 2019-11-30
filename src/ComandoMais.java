import java.util.ArrayList;

public class ComandoMais  extends No {
    private int comando = -1;
    private int comandoMais = -1;
    private boolean lambda;

    public ComandoMais(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public void updateAsm() {
        if (lambda) {
            return;
        }
        setAsm(new ArrayList<>());
        Tree.tree.get(comando).updateAsm();
        Tree.tree.get(comandoMais).updateAsm();
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm(Tree.tree.get(comandoMais).getAsm());
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        else if (classe.equals("COMANDOMAIS")) {
            comandoMais = posicaoNoArrayTree;
        }
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        if (comando == -1) return true;
        return comandoMais == -1;
    }

    public boolean isLambda() {
        return lambda;
    }

    public void setLambda(boolean lambda) {
        this.lambda = lambda;
    }
}
