package NonTerminals;

public class Instrucao  extends No{
    //produção 1:
    private NumInstrucao numInstrucao;
    private InstrucaoPassos instrucaoPassos;
    //prod 2:
    private Sentido sentido;
    //prod 3:
    private Identificador identificador;
    // prod 4,5,6,7 não têm não terminais
    //prod 8:
    private Condicao condicao;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
