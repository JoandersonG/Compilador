import java.util.ArrayList;

public class Laco  extends No {
    private int condicao = -1;
    private int comando = -1;

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {

        if (condicao == -1) {
            condicao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1) {
            comando = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public void updateAsm() {
        int idUnico = Tree.getIdUnico();

        Tree.tree.get(condicao).updateAsm();
        Tree.tree.get(comando).updateAsm();

        setAsm(new ArrayList<>());
        addAsm("loop_laco" + idUnico + ":");
        addAsm(Tree.tree.get(condicao).getAsm());
        addAsm("cmp bl, 1");
        addAsm("je after_loop_laco" + idUnico);
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm("jmp loop_laco" + idUnico);
        addAsm("after_loop_laco" + idUnico + ":");
    }

    @Override
    public boolean temCampoVazio() {
        return (comando == -1 || condicao == -1);
    }
}
