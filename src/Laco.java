public class Laco  extends No {
    private int condicao = -1;
    private int comando = -1;

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {

        if (condicao == -1) {
            condicao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1) {
            comando = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return (comando == -1 || condicao == -1);
    }
}
