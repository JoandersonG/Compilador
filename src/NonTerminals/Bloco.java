package NonTerminals;

public class Bloco extends No{
    private int comandoBloco = -1;

    @Override
    public void addNo(int posicaoNoArrayTree, String classe) {
        comandoBloco = posicaoNoArrayTree;
    }

    @Override
    public boolean temCampoVazio() {
        return comandoBloco == -1;
    }
}
