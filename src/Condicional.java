import java.util.ArrayList;

public class Condicional  extends No {
    private int condicao = -1;
    private int comando = -1;
    private int condicionalSenao = -1;

    @Override
    public void updateAsm() {
        int idUnico = Tree.getIdUnico();
        setAsm(new ArrayList<>());
        Tree.tree.get(condicao).updateAsm();
        Tree.tree.get(comando).updateAsm();
        Tree.tree.get(condicionalSenao).updateAsm();
        addAsm(Tree.tree.get(condicao).getAsm());
        addAsm("cmp bl, 1");
        addAsm("je condicional_false"+idUnico);
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm("jmp fim_condicional" + idUnico);
        addAsm("condicional_false" + idUnico + ":");
        addAsm(Tree.tree.get(condicionalSenao).getAsm());
        addAsm("fim_condicional" + idUnico + ":");
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (condicao == -1) {
            condicao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1){
            comando = posicaoNoArrayTree;
            return true;
        }
        if (condicionalSenao == -1) {
            condicionalSenao = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return (condicao == -1 || comando == -1 || condicionalSenao == -1);
    }
}
