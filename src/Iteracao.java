public class Iteracao  extends No {
    private int numero = -1;
    private int comando = -1;
    //todo: = -1 em todas as classes: confere?

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (numero == -1) {
            numero = posicaoNoArrayTree;
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
        return (numero == -1 || comando == -1);
    }
}
