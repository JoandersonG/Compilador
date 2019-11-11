package NonTerminals;

public class EstadoLampada  extends No{
    //acesa
    //apagada
    String valor;

    public EstadoLampada(String valor) {
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

}
