package NonTerminals;

public class EstadoRobo  extends No{
    private String valor;

    public EstadoRobo(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
    //pronto, ocupado, parado
}
