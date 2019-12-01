import java.util.HashMap;
import java.util.Map;

public class TabelaErros {
    private static final Map<String, String> ERRORS = criarMap();

    private static Map<String, String> criarMap() {
        Map<String, String> tabelaErros = new HashMap<String, String>();
        tabelaErros.put("<lex_id_mal_formado>", "Identificador mal formado: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<lex_simb_not_id>", "Símbolo não identificado: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<lex_numero_grande>", "Número muito grande: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<lex_nmr_mal_formado>", "Número mal formado:  {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<sint_err>", "Era esperado \"{esperado}\" na linha: {linha}, coluna {coluna}");
        tabelaErros.put("<sem_id_nao_dec>", "Procedimento {identificador} não declarado na linha: {linha}");
        tabelaErros.put("<sem_instr_mesmo_nome>", " Mútiplos procedimentos com mesmo nome \"{identificador}\" na linha: {linha}");
        tabelaErros.put("<sem_instr_proibida>", "Instrução {identificador} não permitida na linha {linha}");
        tabelaErros.put("<sem_instr_necessaria>", "Instrução {identificador} necessária na linha {linha}");

        return tabelaErros;
    }

    public Map<String, String> getErrors() {
        return ERRORS;
    }
}
