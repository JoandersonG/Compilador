package NonTerminals;

public class ComandoBloco  extends No{
    private int comando;
    private int comandoBloco;
    private boolean lambda;

    @Override
    public void addNo(int posicaoNoArrayTree, String classe) {
        if (classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        if (classe.equals("COMANDOBLOCO")) {
            comandoBloco = posicaoNoArrayTree;
        }
        else {
            lambda = true;
        }
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
