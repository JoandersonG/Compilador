import com.sun.istack.internal.NotNull;

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
