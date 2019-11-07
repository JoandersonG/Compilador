package NonTerminals;

public class NumInstrucao  extends No{
    private Numero numero;
    private NumInstrucao numInstrucao;
    //ou
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
