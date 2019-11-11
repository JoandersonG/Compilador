package NonTerminals;

public class Sentido  extends No{

    private String value = null;

    public Sentido(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean setAtualProibido(String atualProibido) {

        if (value.equals(atualProibido)) {
            //System.out.println("Erro: instrução proibida");
            return false;
        }
        else {
            return super.setAtualProibido(atualProibido);
        }

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
