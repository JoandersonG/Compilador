package NonTerminals;

public class Identificador  extends No{
    private Letra letra;
    private LetraDigito letraDigito;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
