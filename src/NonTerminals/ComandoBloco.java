package NonTerminals;

public class ComandoBloco  extends No{
    private int comando = -1;
    private int comandoBloco = -1;
    private boolean lambda;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        if (classe.equals("COMANDO")) {
            comando = posicaoNoArrayTree;
        }
        else if (classe.equals("COMANDOBLOCO")) {
            comandoBloco = posicaoNoArrayTree;
        }
        else {
            lambda = true;//é isso mesmo? nops... :/
            return false;
        }
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        if (lambda) return false;
        if (comando == -1) return true;
        if (comandoBloco == -1) return true;
        return false;
    }
}
