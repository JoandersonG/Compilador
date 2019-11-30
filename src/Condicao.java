public class Condicao  extends No {

    private int estadoRobo = -1;
    //prod 2,3,4 não têm não-term
    //prod 5
    private int estadoLampada = -1;
    private int localLampada = -1;
    private String regra;

    public Condicao(String regra) {
        this.regra = regra;

    }

    @Override
    public void updateAsm() {

        int idUnico = Tree.getIdUnico();
        String[] asm;

        addAsm("MOV AL, 4");
        addAsm("OUT 9, AL;examina");
        addAsm("call wait_exam");
        addAsm("in al, 10;resultado do exame");
        if (regra.equals("FRENTE ROBO BLOQUEADA")) {
            addAsm("CMP AL, 00000000b");
            addAsm("je frente_robo_true"+ idUnico);
            addAsm("mov bl, 1;false");
            addAsm("jmp frente_robo_fim" + idUnico);
            addAsm("frente_robo_true"+ idUnico + ":");
            addAsm("mov bl, 0;true");
            addAsm("frente_robo_fim"+idUnico+":");
        }

        else if (regra.equals("DIREITA ROBO BLOQUEADA")) {

            asm = new String[] {
                    "        CMP AL, 11\n" +
                    "        JE direita_robo_true"+ idUnico + "\n" +
                    "        CMP AL, 12\n" +
                    "        JE direita_robo_true"+ idUnico + "\n" +
                    "        CMP AL, 15\n" +
                    "        JE direita_robo_true"+ idUnico + "\n" +
                    "        MOV BL, 1;false\n" +
                    "        JMP direita_robo_fim"+ idUnico + "\n" +
                    "        direita_robo_true:"+ idUnico + "\n" +
                    "        MOV BL, 0;true\n" +
                    "        direita_robo_fim"+ idUnico + ":"
            };
        }
        else if (regra.equals("ESQUERDA ROBO BLOQUEADA")) {
            asm = new String[]{
                    "        MOV AL, 4\n" +
                    "        OUT 9, AL; examina\n" +
                    "        call wait_exam\n" +
                    "        IN AL, 10; resultado do exame\n" +
                    "        CMP AL, 9\n" +
                    "        JE esquerda_robo_true"+ idUnico + "\n" +
                    "        CMP AL, 10\n" +
                    "        JE esquerda_robo_true"+ idUnico + "\n" +
                    "        CMP AL, 240\n" +
                    "        JE esquerda_robo_true"+ idUnico + "\n" +
                    "        MOV BL, 1;false\n" +
                    "        JMP esquerda_robo_fim"+ idUnico + "\n" +
                    "        esquerda_robo_true"+ idUnico + ":\n" +
                    "        MOV BL, 0;true\n" +
                    "        esquerda_robo_fim"+ idUnico + ":"
            };
        }
        else if (regra.matches("LAMPADA.*")) {

            if (Tree.tree.get(estadoLampada).getVal().equals("ACESA")) {
                Tree.tree.get(localLampada).updateAsm();
                addAsm(Tree.tree.get(localLampada).getAsm());
                asm = new String[]{
                    "cmp bl, 2;frente\n" +
                    "je frente" + idUnico + "\n" +
                    "cmp bl, 1;direita\n" +
                    "je direita" + idUnico + "\n" +
                    "jmp esquerda" + idUnico + ";else: esquerda\n" +
                    "frente" + idUnico + ":\n" +
                    "cmp al, 7\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "esquerda" + idUnico + ":\n" +
                    "cmp al, 9\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "direita" + idUnico + ":\n" +
                    "cmp al, 11\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "fimcondicaolampada" + idUnico + ":\n" +
                    "        JE condicao_true" + idUnico + "\n" +
                    "        MOV BL, 1; false\n" +
                    "        JMP condicao_fim" + idUnico + "\n" +
                    "        condicao_true" + idUnico + ":\n" +
                    "        MOV BL, 0; true\n" +
                    "        condicao_fim" + idUnico + ":"
                };

            }
            else if (Tree.tree.get(estadoLampada).getVal().equals("APAGADA")) {
                Tree.tree.get(localLampada).updateAsm();
                addAsm(Tree.tree.get(localLampada).getAsm());
                asm = new String[]{
                    "cmp bl, 2;frente\n" +
                    "je frente" + idUnico + "\n" +
                    "cmp bl, 1;direita\n" +
                    "je direita" + idUnico + "\n" +
                    "jmp esquerda" + idUnico + ";else: esquerda\n" +
                    "frente" + idUnico + ":\n" +
                    "cmp al, 8\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "esquerda" + idUnico + ":\n" +
                    "cmp al, 10\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "direita" + idUnico + ":\n" +
                    "cmp al, 12\n" +
                    "jmp fimcondicaolampada" + idUnico + "\n" +
                    "fimcondicaolampada" + idUnico + ":\n" +
                    "        JE lampada_condicao_true" + idUnico + "\n" +
                    "        MOV BL, 1; false\n" +
                    "        JMP lampada_condicao_fim" + idUnico + "\n" +
                    "        lampada_condicao_true" + idUnico + ":\n" +
                    "        MOV BL, 0; true\n" +
                    "        lampada_condicao_fim" + idUnico + ":"
                };
            }
            else {
                System.out.println("Erro: condição não encontrada 1");
            }
        }
        else if (regra.matches("ROBO.*")) {
            Tree.tree.get(estadoRobo).updateAsm();
            addAsm(Tree.tree.get(estadoRobo).getAsm());
        }

    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {

        if (regra.equals("FRENTE ROBO BLOQUEADA") || regra.equals("DIREITA ROBO BLOQUEADA") || regra.equals("ESQUERDA ROBO BLOQUEADA")) {
            //não pode adicionar nenhum
            return false;
        }
        if (regra.matches("ROBO.*")) {
            estadoRobo = posicaoNoArrayTree;
            return true;
        }
        if (regra.matches("LAMPADA.*")) {
            if (classe.equals("ESTADOLAMPADA")) {
                estadoLampada = posicaoNoArrayTree;
            }
            else if (classe.equals("LOCALLAMPADA")) {
                estadoLampada = posicaoNoArrayTree;
            }
            return true;
        }

        //todo: salvar a produção, mas como saber a produção de sí terminais? ex: "aguarde ate robo pronto"? ::: resolvi?
        return false;

    }

    @Override
    public boolean temCampoVazio() {
        if (regra.matches("FRENTE.*") || regra.matches("ESQUERDA.*") || regra.matches("")) {
            return false;
        }
        if (regra.matches("ROBO.*")) {
            return estadoRobo == -1;
        }
        return (estadoLampada == -1 || localLampada == -1);
    }
}
