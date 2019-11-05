import java.util.HashMap;
import java.util.Map;

public class TabelaReservada {
    private static final Map<String, String> PALAVRAS_RESERVADAS = criarMap();

    private static Map<String, String> criarMap() {
        Map<String, String> tabelaPalavrasReservadas = new HashMap<String, String>();

        tabelaPalavrasReservadas.put("ESQUERDA", "ESQUERDA");
        tabelaPalavrasReservadas.put("DIREITA", "DIREITA");
        tabelaPalavrasReservadas.put("PROGRAMAINICIO", "PROGRAMAINICIO");
        tabelaPalavrasReservadas.put("FIMPROGRAMA", "FIMPROGRAMA");
        tabelaPalavrasReservadas.put("EXECUCAOINICIO", "EXECUCAOINICIO");
        tabelaPalavrasReservadas.put("FIMEXECUCAO", "FIMEXECUCAO");
        tabelaPalavrasReservadas.put("DEFINAINSTRUCAO", "DEFINAINSTRUCAO");
        tabelaPalavrasReservadas.put("COMO", "COMO");
        tabelaPalavrasReservadas.put("INICIO", "INICIO");
        tabelaPalavrasReservadas.put("FIM", "FIM");
        tabelaPalavrasReservadas.put("REPITA", "REPITA");
        tabelaPalavrasReservadas.put("VEZES", "VEZES");
        tabelaPalavrasReservadas.put("FIMREPITA", "FIMREPITA");
        tabelaPalavrasReservadas.put("ENQUANTO", "ENQUANTO");
        tabelaPalavrasReservadas.put("FACA", "FACA");
        tabelaPalavrasReservadas.put("FIMPARA", "FIMPARA");
        tabelaPalavrasReservadas.put("SE", "SE");
        tabelaPalavrasReservadas.put("ENTAO", "ENTAO");
        tabelaPalavrasReservadas.put("FIMSE", "FIMSE");
        tabelaPalavrasReservadas.put("SENAO", "SENAO>");
        tabelaPalavrasReservadas.put("FIMSENAO", "FIMSENAO");
        tabelaPalavrasReservadas.put("MOVA", "MOVA");
        tabelaPalavrasReservadas.put("PASSOS", "PASSOS");
        tabelaPalavrasReservadas.put("VIRE", "VIRE");
        tabelaPalavrasReservadas.put("PARA", "PARA");
        tabelaPalavrasReservadas.put("PARE", "PARE");
        tabelaPalavrasReservadas.put("FINALIZE", "FINALIZE");
        tabelaPalavrasReservadas.put("APAGUE", "APAGUE");
        tabelaPalavrasReservadas.put("LAMPADA", "LAMPADA");
        tabelaPalavrasReservadas.put("ACENDA", "ACENDA");
        tabelaPalavrasReservadas.put("AGUARDE", "AGUARDE");
        tabelaPalavrasReservadas.put("ATE", "ATE");
        tabelaPalavrasReservadas.put("ROBO", "ROBO");
        tabelaPalavrasReservadas.put("PRONTO", "PRONTO");
        tabelaPalavrasReservadas.put("OCUPADO", "OCUPADO");
        tabelaPalavrasReservadas.put("PARADO", "PARADO");
        tabelaPalavrasReservadas.put("MOVIMENTANDO", "MOVIMENTANDO");
        tabelaPalavrasReservadas.put("FRENTE", "FRENTE");
        tabelaPalavrasReservadas.put("BLOQUEADA", "BLOQUEADA");
        tabelaPalavrasReservadas.put("ACESA", "ACESA");
        tabelaPalavrasReservadas.put("APAGADA", "APAGADA");
        tabelaPalavrasReservadas.put("A", "A");

        return tabelaPalavrasReservadas;
    }

    public Map<String, String> getPalavrasReservadas() {
        return PALAVRAS_RESERVADAS;
    }
}