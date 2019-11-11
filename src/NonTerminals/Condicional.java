package NonTerminals;

public class Condicional  extends No{
    private int condicao = -1;
    private int comando = -1;
    private int condicionalSenao = -1;

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (condicao == -1) {
            condicao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1){
            comando = posicaoNoArrayTree;
            return true;
        }
        if (condicionalSenao == -1) {
            condicionalSenao = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return (condicao == -1 || comando == -1 || condicionalSenao == -1);
    }
}
