package NonTerminals;

public class NumInstrucao  extends No{
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
    public boolean temCampoVazio() {
        if (lambda) return false;
        return (numero == -1 || numInstrucao == -1);
    }
}
