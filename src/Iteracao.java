import java.util.ArrayList;

public class Iteracao  extends No {
    private int numero = -1;
    private int comando = -1;
    //todo: = -1 em todas as classes: confere?

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (numero == -1) {
            numero = posicaoNoArrayTree;
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
        Tree.tree.get(numero).updateAsm();
        Tree.tree.get(comando).updateAsm();
        setAsm(new ArrayList<>());
        addAsm(Tree.tree.get(numero).getAsm());
        String[] asm = {
            "    mov al, 0\n" +
            "    loop_iteracao" + idUnico + ":\n" +
            "        cmp al, bl\n" +
            "        jge after_loop_iteracao" + idUnico + "\n" +
            "        \n"};
        addAsm(asm);
        addAsm(Tree.tree.get(comando).getAsm());
        asm = new String[] {
                "INC al\n" +
            "        jmp loop_iteracao" + idUnico + "\n" +
            "    after_loop_iteracao" + idUnico + ":"
        };
        addAsm(asm);
    }

    @Override
    public boolean temCampoVazio() {
        return (numero == -1 || comando == -1);
    }
}
