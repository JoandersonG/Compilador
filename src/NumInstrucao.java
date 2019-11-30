import java.util.ArrayList;

public class NumInstrucao  extends No {
    private int numero = -1;
    private int numInstrucao = -1;
    //ou
    private boolean lambda;

    public NumInstrucao(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (numero == -1) {
            numero = posicaoNoArrayTree;
            return true;
        }
        if (numInstrucao == -1) {
            numInstrucao = posicaoNoArrayTree;
            return true;
        }
        return false;

    }

    @Override
    public void updateAsm() {
        if (lambda) return;

        Tree.tree.get(numero).updateAsm();
        Tree.tree.get(numInstrucao).updateAsm();

        setAsm(new ArrayList<>());
        addAsm(Tree.tree.get(numero).getAsm());
        addAsm(Tree.tree.get(numInstrucao).getAsm());

    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        return (numero == -1 || numInstrucao == -1);
    }
}
