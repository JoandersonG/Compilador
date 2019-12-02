public class Identificador  extends No {

    private String id;

    public Identificador(String id) {
        this.id = id;
        this.setNome(id);
    }

    @Override
    public void updateAsm() {

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
