package NonTerminals;

public class Declaracao  extends No{
    private Identificador identificador;
    private Comando comando;
    private Declaracao declaracao;
    private boolean lambda;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
