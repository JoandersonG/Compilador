import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class Instrucao  extends No {
    private int numInstrucao = -1;
    private int instrucaoPassos = -1;
    private int sentido = -1;
    private int identificador = -1;
    private int condicao = -1;

    public Instrucao(@NotNull String regra) {
        this.setVal(regra);
    }

    @Override
    public void updateAsm() {
        int idUnico = Tree.getIdUnico();
        String[] asm = {""};
        if (numInstrucao != -1) {
            //estou em mova NumInstrucao InstrucaoPassos
            Tree.tree.get(numInstrucao).updateAsm();

            setAsm(new ArrayList<>());
            addAsm(Tree.tree.get(numInstrucao).getAsm());
            asm = new String[] {
                "        PUSHA\n" +
                "        mov CL, 0\n" +
                "        loop_mova_numInstrucao" + idUnico + ":\n" +
                "            CMP CL, BL ;EM BL tem resultado de numinstrucao\n" +
                "            JGE after_loop_mova_numInstrucao" + idUnico + "\n" +
                "\n" +
                "            MOV AL, 1\n" +
                "            OUT 9, AL\n" +
                "            call wait_busy;??\n" +
                "\n" +
                "            INC CL\n" +
                "            JMP loop_mova_numInstrucao" + idUnico + "\n" +
                "        after_loop_mova_numInstrucao" + idUnico + ":\n" +
                "        POPA"
            };
        }
        else if (sentido != -1) {
            //vire para sentido
            Tree.tree.get(sentido).updateAsm();
            addAsm(Tree.tree.get(sentido).getAsm());
            asm = new String[] {
                "        CMP BL, 0\n" +
                "        JE vire_para_esquerda" + idUnico + "\n" +
                "        MOV AL, 3\n" +
                "        JMP vire_para_sentido_fim" + idUnico + "\n" +
                "        vire_para_esquerda" + idUnico + ":\n" +
                "        MOV AL, 2\n" +
                "        vire_para_sentido_fim" + idUnico + ":\n" +
                "        OUT 9, AL\n" +
                "        call wait_busy"
            };
        }
        else if (identificador != -1) {
            addAsm("call " + Tree.tree.get(identificador).getNome());
        }
        else if (getVal().equals("PARE")) {
            asm = new String[] {
                    "mov al, 0\n" +
                    "out 9, al\n" +
                    "call wait_busy"
            };
        }
        else if (getVal().equals("FINALIZE")) {
            addAsm("jmp end_main");//todo: label fim_do_programa
        }
        else if (getVal().equals("APAGUE LAMPADA")) {
            asm = new String[] {
                    "mov al, 6\n" +
                    "out 9, al\n" +
                    "call wait_busy"
            };

        }
        else if (getVal().equals("ACENDA LAMPADA")) {
            asm = new String[] {
                    "mov al, 5\n" +
                    "out 9, al\n" +
                    "call wait_busy"
            };
        }
        else if (condicao != -1) {
            //aguarde ate Condicao
            Tree.tree.get(condicao).updateAsm();
            addAsm("loop_aguarde_condicao" + idUnico + ":");
            addAsm(Tree.tree.get(condicao).getAsm());
            asm = new String[] {
                "            cmp bl, 0\n" +
                "            je after_loop_aguarde_condicao" + idUnico + "\n" +
                "            jmp loop_aguarde_condicao" + idUnico + "\n" +
                "        after_loop_aguarde_condicao" + idUnico + ":"
            };
        }
        else {
            System.out.println("Erro em Instrucao: intrução não reconhecida 1");
        }
        addAsm(asm);
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if(getAtualObrigatorio() != null) {
            //se tem um atual obrigatório, então testa para ver se dá para ser coerente com o atualObrigatório e receber o filho que ele está recebendo:
            if (getAtualObrigatorio().matches("AGUARDE ATE ROBO PRONTO")) {
                if (!this.getVal().matches("AGUARDE.*")) {
                    //erro!
                    //System.out.println("Erro semântico: era esperado AGUARDE ATE ROBO PRONTO em " + getVal());
                }
                else {
                    this.setAtualObrigatorio("ROBO PRONTO");
                }
            }
        }

        if (this.getVal().matches("MOVA.*")) {
            if (numInstrucao == -1){
                numInstrucao = posicaoNoArrayTree;
                return true;
            }
            if (instrucaoPassos == -1) {
                instrucaoPassos = posicaoNoArrayTree;
                return true;
            }
            return false;
        }
        if (this.getVal().matches("VIRE.*")) {
                if (sentido == -1) {
                    String proibido = "VIRE PARA ";
//                    if (regra.ma)
//                    setProximoProibido("VIRE PARA " + );
                    sentido = posicaoNoArrayTree;
                    return true;
                }
                return false;
        }
        if (this.getVal().matches("IDENTIFICADOR.*")) {
            if (identificador == -1) {
                identificador = posicaoNoArrayTree;
                return true;
            }
            return false;
        }
        if (this.getVal().matches("AGUARDE.*")) {
            if (condicao == -1) {
                condicao = posicaoNoArrayTree;
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        if (this.getVal() == null) {
            return true;
        }
        if (this.getVal().matches("MOVA.*")) {
            if (numInstrucao == -1){
                return true;
            }
            if (instrucaoPassos == -1) {
                return true;
            }
            return false;
        }
        if (this.getVal().matches("VIRE.*")) {
            if (sentido == -1) {
                return true;
            }
            return false;
        }
        if (this.getVal().matches("IDENTIFICADOR.*")) {
            if (identificador == -1) {
                return true;
            }
            return false;
        }
        if (this.getVal().matches("AGUARDE.*")) {
            if (condicao == -1) {
                return true;
            }
            return false;
        }
        return false;
    }
}
