import java.util.ArrayList;

public class EstadoRobo  extends No {
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
        this.setVal(valor);
    }

    public EstadoRobo(String valor) {
        this.valor = valor;
        this.setVal(valor);
        if (getAtualObrigatorio()!= null) {
            if(getAtualObrigatorio().matches("ROBO PRONTO") && valor.equals("PRONTO")) {
                //tudo certo
                setAtualObrigatorio(null);
            }
            else {
                //System.out.println("ERRO SEMÂNTICO: 'PRONTO' ERA ESPERADO");
            }
        }
    }

    @Override
    public void updateAsm() {
        setAsm(new ArrayList<>());
        int idUnico = Tree.getIdUnico();
        String[] asm = {""};
        if (valor.equals("PRONTO")) {
            asm = new String[] {
                        "        IN al, 11\n" +
                        "        AND AL, 00000010b\n" +
                        "        CMP AL, 0\n" +
                        "        JE pronto_true" + idUnico + "\n" +
                        "        MOV BL, 1; padrão para falso\n" +
                        "        jmp pronto_end" + idUnico + "\n" +
                        "        pronto_true" + idUnico + ":\n" +
                        "        MOV BL, 0; padrão para verdadeiro\n" +
                        "        pronto_end" + idUnico + ":"
            };
        }
        else if (valor.equals("OCUPADO")) {
            asm = new String[] {
                    "        IN al, 11\n" +
                    "        AND AL, 00000010b\n" +
                    "        CMP AL, 0\n" +
                    "        JNE pronto_true" + idUnico + "\n" +
                    "        MOV BL, 1; padrão para falso\n" +
                    "        jmp pronto_end" + idUnico + "\n" +
                    "        pronto_true" + idUnico + ":\n" +
                    "        MOV BL, 0; padrão para verdadeiro\n" +
                    "        pronto_end" + idUnico + ":"
            };
        }
        else if (valor.equals("PARADO")) {
            asm = new String[]{
                "        IN AL, 9\n" +
                "        CMP AL, 1;ultima instrução foi movimenta para frente\n" +
                "        JNE parado_true" + idUnico + "\n" +
                "        IN AL, 11\n" +
                "        AND AL, 00000010b\n" +
                "        CMP AL, 0;testa se  robo está pronto\n" +
                "        JE parado_true" + idUnico + "\n" +
                "        MOV BL, 1;false\n" +
                "        JMP parado_fim" + idUnico + "\n" +
                "        parado_true" + idUnico + ":\n" +
                "        MOV BL, 0;true\n" +
                "        parado_fim" + idUnico + ":"
            };
        }
        else if (valor.equals("MOVIMENTANDO")) {
            asm = new String[]{
                "        IN AL, 9;ultima instrucao\n" +
                "        CMP AL, 1; == MOVA\n" +
                "        JNE else1\n" +
                "        ;testa se não está pronto ainda:\n" +
                "        IN AL, 11\n" +
                "        AND AL, 00000010b\n" +
                "        CMP AL, 0\n" +
                "        JE else1" + idUnico + "\n" +
                "        ;return true\n" +
                "        MOV BL, 0\n" +
                "        JMP movimentando_fim" + idUnico + "\n" +
                "        else1:\n" +
                "        MOV BL, 1; false\n" +
                "        movimentando_fim" + idUnico + ":"
            };
        }
        else {
            System.out.println("Erro em estadoRobo 1");
        }

        addAsm(asm);
    }

    @Override
    public void setAtualObrigatorio(String atualObrigatorio) {
        if (!valor.equals("PRONTO") && atualObrigatorio.equals("ROBO PRONTO")) {
            //System.out.println("erro ... ");
        }
        else {
            //setAtualObrigatorio(null);
            super.setAtualObrigatorio(atualObrigatorio);
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
    //pronto, ocupado, parado
}
