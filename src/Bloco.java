import java.util.ArrayList;

public class Bloco extends No {
    private int comandoBloco = -1;

    public int getComandoBloco() {
        return comandoBloco;
    }

    public void setComandoBloco(int comandoBloco) {
        this.comandoBloco = comandoBloco;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        comandoBloco = posicaoNoArrayTree;
        //setAsm(getArray[posicaoNoArrayTree].getAsm);
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        return comandoBloco == -1;
    }

    @Override
    public ArrayList<String> getAsm() {
        if (comandoBloco == -1) {
            return null;
        }
        return Tree.tree.get(comandoBloco).getAsm();
    }

    @Override
    public void updateAsm() {
        if (comandoBloco == -1) {
            return;
        }
        Tree.tree.get(comandoBloco).updateAsm();
        this.setAsm(Tree.tree.get(comandoBloco).getAsm());
    }
}
