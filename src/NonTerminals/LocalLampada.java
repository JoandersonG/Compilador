package NonTerminals;

public class LocalLampada  extends No{

    private int sentido = -1;
    private String value;

    public LocalLampada(String value) {
        this.value = value;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {

        if (value.equals("FRENTE")) {
            return false;
        }
        if (sentido == -1) {
            sentido = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        if (value.equals("FRENTE")) {
            return false;
        }
        return (sentido == -1);
    }
}
