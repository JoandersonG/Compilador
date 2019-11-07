package NonTerminals;

public class LocalLampada  extends No{
    //frente
    //ou
    private Sentido sentido;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
