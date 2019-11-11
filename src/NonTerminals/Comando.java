package NonTerminals;

public class Comando  extends No{

    //somente um desses atributos acontece em cada instância

    private int bloco = -1;
    private int iteracao = -1;
    private int laco = -1;
    private int condicional = -1;
    private int instrucao = -1;
    private String regra;

    public Comando(String regra) {
        this.regra = regra;
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String regra) {
        if (this.regra.equals("BLOCO")) {
            bloco = posicaoNoArrayTree;
        }
        else if (this.regra.equals("ITERACAO")) {
            iteracao = posicaoNoArrayTree;
        }
        else if (this.regra.equals("LACO")) {
            laco = posicaoNoArrayTree;
        }
        else if (this.regra.equals("CONDICIONAL")) {
            condicional = posicaoNoArrayTree;
        }
        else if (this.regra.equals("INSTRUCAO")) {
            instrucao = posicaoNoArrayTree;
        }
        return true;
    }

    @Override
    public boolean temCampoVazio() {
        return (bloco == -1 && iteracao == -1 && laco == -1 && condicional == -1 && instrucao == -1);
    }
}
