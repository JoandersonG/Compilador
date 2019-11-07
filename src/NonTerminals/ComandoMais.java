package NonTerminals;

public class ComandoMais  extends No{
    private int comando = -1;
    private int comandoMais = -1;
    private boolean lambda;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        if (comando == -1 && classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        else if (comandoMais == -1 && classe.equals("COMANDOMAIS")) {
            comandoMais = posicaoNoArrayTree;
        }
        else {
            lambda = true;//isso está errado.
            return false;
            //todo: como descobrir se é lambda ou outra produção?
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
