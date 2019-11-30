import java.util.ArrayList;

public class Sentido  extends No {

    private String value = null;

    public Sentido(String value) {
        this.value = value;
        this.setVal(value);
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        if (value.equals("ESQUERDA")) {
            addAsm("MOV BL, 0;esquerda");
        }
        else {//direita
            addAsm("MOV BL, 1;direita");
        }
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
