import java.util.HashMap;
import java.util.Map;

public class TabelaReservada {
    private static final Map<String, String> PALAVRAS_RESERVADAS = criarMap();

    private static Map<String, String> criarMap() {
        Map<String, String> tabelaPalavrasReservadas = new HashMap<String, String>();

        tabelaPalavrasReservadas.put("ESQUERDA", "<sentido_esq>");
        tabelaPalavrasReservadas.put("DIREITA", "<sentido_dir>");
        tabelaPalavrasReservadas.put("PROGRAMAINICIO", "<prog_inicio>");
        tabelaPalavrasReservadas.put("FIMPROGRAMA", "<prog_fim>");
        tabelaPalavrasReservadas.put("EXECUCAOINICIO", "<exec_inic>");
        tabelaPalavrasReservadas.put("FIMEXECUCAO", "<exec_fim>");
        tabelaPalavrasReservadas.put("DEFINAINSTRUCAO", "<def_func>");
        tabelaPalavrasReservadas.put("COMO", "<assign_func>");
        tabelaPalavrasReservadas.put("INICIO", "<bloco_inic>");
        tabelaPalavrasReservadas.put("FIM", "<bloco_fim>");
        tabelaPalavrasReservadas.put("REPITA", "<iteracao_inic>");
        tabelaPalavrasReservadas.put("VEZES", "<iteracao_quant>");
        tabelaPalavrasReservadas.put("FIMREPITA", "<iteracao_fim>");
        tabelaPalavrasReservadas.put("ENQUANTO", "<laco_inic>");
        tabelaPalavrasReservadas.put("FACA", "<laco_exec>");
        tabelaPalavrasReservadas.put("FIMPARA", "<laco_fim>");
        tabelaPalavrasReservadas.put("SE", "<condicional_inic>");
        tabelaPalavrasReservadas.put("ENTAO", "<condicional_entao_inic>");
        tabelaPalavrasReservadas.put("FIMSE", "<condicional_entao_fim>");
        tabelaPalavrasReservadas.put("SENAO", "<condicional_senao_inic>");
        tabelaPalavrasReservadas.put("FIMSENAO", "<condicional_senao_fim>");
        tabelaPalavrasReservadas.put("MOVA", "<instrucao_mova_inic>");
        tabelaPalavrasReservadas.put("PASSOS", "<instrucao_mova_passos>");
        tabelaPalavrasReservadas.put("VIRE", "<instrucao_vire_inic>");
        tabelaPalavrasReservadas.put("PARA", "<instrucao_vire_para>");
        tabelaPalavrasReservadas.put("PARE", "<instrucao_pare>");
        tabelaPalavrasReservadas.put("FINALIZE", "<instrucao_finalize>");
        tabelaPalavrasReservadas.put("APAGUE", "<instrucao_apague_lamp>");
        tabelaPalavrasReservadas.put("LAMPADA", "<instrucao_lampada>");
        tabelaPalavrasReservadas.put("ACENDA", "<instrucao_acenda_lamp>");
        tabelaPalavrasReservadas.put("AGUARDE", "<instrucao_aguarde>");
        tabelaPalavrasReservadas.put("ATE", "<instrucao_aguarde_ate>");
        tabelaPalavrasReservadas.put("ROBO", "<tag_condicao_robo>");
        tabelaPalavrasReservadas.put("PRONTO", "<tag_condicao_estado_pronto>");
        tabelaPalavrasReservadas.put("OCUPADO", "<tag_condicao_estado_ocupado>");
        tabelaPalavrasReservadas.put("PARADO", "<tag_condicao_estado_parado>");
        tabelaPalavrasReservadas.put("MOVIMENTANDO", "<tag_condicao_estado_movimentando>");
        tabelaPalavrasReservadas.put("FRENTE", "<tag_condicao_direcao>");
        tabelaPalavrasReservadas.put("BLOQUEADA", "<tag_condicao_bloqueada>");
        tabelaPalavrasReservadas.put("ACESA", "<tag_condicao_acesa>");
        tabelaPalavrasReservadas.put("APAGADA", "<tag_condicao_apagada>");
        tabelaPalavrasReservadas.put("A", "<tag_condicao_a>");

        return tabelaPalavrasReservadas;
    }

    public Map<String, String> getPalavrasReservadas() {
        return PALAVRAS_RESERVADAS;
    }
}