package NonTerminals;

public class Programa extends No{
    private int declaracao = -1;
    private int comando = -1;
    private int comandoMais = -1;


    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String Classe) {
        if (declaracao == -1) {
            declaracao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1) {
            comando = posicaoNoArrayTree;
            return true;
        }
        if (comandoMais == -1) {
            comandoMais = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return (declaracao == -1 || comando == -1 || comandoMais == -1);
    }
}
