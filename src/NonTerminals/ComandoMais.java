package NonTerminals;

public class ComandoMais  extends No{
    private int comando = -1;
    private int comandoMais = -1;
    private boolean lambda;

    public ComandoMais(boolean lambda) {
        this.lambda = lambda;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (lambda) return false;
        if (classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        else if (classe.equals("COMANDOBLOCO")) {
            comandoMais = posicaoNoArrayTree;
        }
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        if (comando == -1) return true;
        if (comandoMais == -1) return true;
        return false;
    }

    public boolean isLambda() {
        return lambda;
    }

    public void setLambda(boolean lambda) {
        this.lambda = lambda;
    }
}
