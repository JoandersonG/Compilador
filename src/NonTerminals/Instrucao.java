package NonTerminals;

public class Instrucao {
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
}