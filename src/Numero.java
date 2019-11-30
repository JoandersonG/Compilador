import java.util.ArrayList;

public class Numero  extends No {

    private String num;

    public Numero(String num) {
        this.num = num;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        addAsm("mov bl, " + num);
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
