package NonTerminals;

public class Bloco extends No{
    private int comandoBloco = -1;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        comandoBloco = posicaoNoArrayTree;
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        return comandoBloco == -1;
    }
}
