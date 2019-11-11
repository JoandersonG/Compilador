package NonTerminals;

public class InstrucaoPassos  extends No{
    //terminal passos
    //ou
    private boolean lambda;
    private String value;

    public InstrucaoPassos(boolean lambda, String value) {
        this.lambda = lambda;
        this.value = value;
    }


    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
