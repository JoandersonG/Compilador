package NonTerminals;

public class Instrucao  extends No{
    private int numInstrucao = -1;
    private int instrucaoPassos = -1;
    private int sentido = -1;
    private int identificador = -1;
    private int condicao = -1;
    private String regra;

    public Instrucao(String regra) {
        this.regra = regra;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        if (regra.matches("MOVA.*")) {
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
        if (regra.matches("VIRE.*")) {
                if (sentido == -1) {
                    String proibido = "VIRE PARA ";
//                    if (regra.ma)
//                    setProximoProibido("VIRE PARA " + );
                    sentido = posicaoNoArrayTree;
                    return true;
                }
                return false;
        }
        if (regra.matches("IDENTIFICADOR.*")) {
            if (identificador == -1) {
                identificador = posicaoNoArrayTree;
                return true;
            }
            return false;
        }
        if (regra.matches("AGUARDE.*")) {
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
        if (regra.matches("MOVA.*")) {
            if (numInstrucao == -1){
                return true;
            }
            if (instrucaoPassos == -1) {
                return true;
            }
            return false;
        }
        if (regra.matches("VIRE.*")) {
            if (sentido == -1) {
                return true;
            }
            return false;
        }
        if (regra.matches("IDENTIFICADOR.*")) {
            if (identificador == -1) {
                return true;
            }
            return false;
        }
        if (regra.matches("AGUARDE.*")) {
            if (condicao == -1) {
                return true;
            }
            return false;
        }
        return false;
    }
}
