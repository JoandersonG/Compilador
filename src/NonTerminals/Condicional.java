package NonTerminals;

public class Condicional  extends No{
    private Condicao condicao;
    private Comando comando;
    private CondicionalSenao condicionalSenao;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
