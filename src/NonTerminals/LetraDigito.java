package NonTerminals;

public class LetraDigito  extends No{
    private Letra letra;
    private Digito digito;
    private LetraDigito letraDigito;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }

    private boolean lambda;
}
