package NonTerminals;

public class Comando  extends No{

    //somente um desses atributos acontece em cada instância

    private int bloco = -1;
    private int iteracao = -1;
    private int laco = -1;
    private int condicional = -1;
    private int instrucao = -1;

    @Override
    public void addNo(int posicaoNoArrayTree, String classe) {
        if (classe.equals("BLOCO")) {
            bloco = posicaoNoArrayTree;
        }
        else if (classe.equals("ITERACAO")) {
            iteracao = posicaoNoArrayTree;
        }
        else if (classe.equals("LACO")) {
            laco = posicaoNoArrayTree;
        }
        else if (classe.equals("CONDICIONAL")) {
            condicional = posicaoNoArrayTree;
        }
        else if (classe.equals("INSTRUCAO")) {
            instrucao = posicaoNoArrayTree;
        }
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
