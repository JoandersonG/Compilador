public class CondicionalSenao  extends No {
    private int comando = -1;
    //lambda??
    private boolean lambda;

    public CondicionalSenao(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (comando == -1) {
            comando = posicaoNoArrayTree;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        return comando == -1;
    }
}
