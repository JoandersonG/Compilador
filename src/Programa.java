import java.util.ArrayList;

public class Programa extends No {
    private int declaracao = -1;
    private int comando = -1;
    private int comandoMais = -1;


    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String Classe) {
        if (declaracao == -1) {
            declaracao = posicaoNoArrayTree;
            return true;
        }
        if (comando == -1) {
            comando = posicaoNoArrayTree;
            return true;
        }
        if (comandoMais == -1) {
            comandoMais = posicaoNoArrayTree;
            return true;
        }
        return false;
    }

    @Override
    public void updateAsm() {
        int idUnico = Tree.getIdUnico();
        setAsm(new ArrayList<>());
        Tree.tree.get(comando).updateAsm();
        Tree.tree.get(comandoMais).updateAsm();
        Tree.tree.get(declaracao).updateAsm();

        String[] asm = {
                "\n" +
                ";===================================\n" +
                ";código copiado do arquivo exemplo:\n" +
                ";===================================\n" +
                "#start=robot.exe#\n" +
                "\n" +
                "name \"robot\"\n" +
                "\n" +
                "#make_bin#\n" +
                "#cs = 500#\n" +
                "#ds = 500#\n" +
                "#ss = 500#    ; stack\n" +
                "#sp = ffff#\n" +
                "#ip = 0#\n" +
                "        \n" +
                "; robot base i/o port:\n" +
                "r_port equ 9\n" +
                "\n" +
                ";===================================\n" +
                "main:\n" +
                "loop_aguarde_robo_pronto_inicio:\n" +
                "        IN AL, 11\n" +
                "        AND AL, 00000010b\n" +
                "        CMP AL, 0\n" +
                "        JE pronto_end\n" +
                "        JE loop_aguarde_robo_pronto_inicio\n" +
                "pronto_end:"
        };
        addAsm(asm);
        addAsm(Tree.tree.get(comando).getAsm());
        addAsm(Tree.tree.get(comandoMais).getAsm());
        asm = new String[] {
                "end_main:\n" +
                "jmp fim_de_tudo\n" +
                ";===================================\n" +
                "wait_exam proc\n" +
                "; check if has new data:\n" +
                "busy2: in al, r_port+2\n" +
                "       test al, 00000001b\n" +
                "       jz busy2 ; no new data, so wait.\n" +
                "ret    \n" +
                "wait_exam endp\n" +
                ";===================================\n" +
                "wait_busy proc\n" +
                        ";check if robot is still doing something\n" +
                        "wait:\n" +
                        "    in al, 11\n" +
                        "    test al, 00000010b\n" +
                        "    jz wait_busy_end\n" +
                        "    jmp wait\n" +
                        "wait_busy_end: \n" +
                        "ret\n" +
                        "wait_busy endp\n" +
                        ";==================================="
        };
        addAsm(asm);
        addAsm(Tree.tree.get(declaracao).getAsm());
        addAsm("fim_de_tudo:");
        addAsm("mov ah, 0");
        addAsm("int 16h");
        addAsm("ret");
    }

    @Override
    public boolean temCampoVazio() {
        return (declaracao == -1 || comando == -1 || comandoMais == -1);
    }
}
