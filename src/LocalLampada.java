import java.util.ArrayList;

public class LocalLampada  extends No {

    private int sentido = -1;
    private String value;

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        if (sentido == -1) {
            addAsm("MOV BL, 2;frente");
        }
        else {
            Tree.tree.get(sentido).updateAsm();
            addAsm(Tree.tree.get(sentido).getAsm());
        }
    }

    public LocalLampada(String value) {
        this.value = value;
        setVal(value);
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
