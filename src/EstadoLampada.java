import java.util.ArrayList;

public class EstadoLampada  extends No {
    //acesa
    //apagada
    String valor;

    public EstadoLampada(String valor) {
        this.valor = valor;
        this.setVal(valor);
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        if (valor.equals("ACESA")) {
            addAsm("mov cl, 0;acesa");
        }
        else {
            addAsm("mov cl, 1;apagada");

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
