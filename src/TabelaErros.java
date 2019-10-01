import java.util.HashMap;
import java.util.Map;

public class TabelaErros {
    private static final Map<String, String> ERRORS = criarMap();

    private static Map<String, String> criarMap() {
        Map<String, String> tabelaErros = new HashMap<String, String>();
        tabelaErros.put("<id_mal_formado>", "Identificador mal formado: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<simb_not_id>", "Símbolo não identificado: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<numero_grande>", "Número muito grande: {lexema}, linha: {linha}, coluna: {coluna}");
        tabelaErros.put("<nmr_mal_formado>", "Número mal formado:  {lexema}, linha: {linha}, coluna: {coluna}");

        return tabelaErros;
    }

    public Map<String, String> getErrors() {
        return ERRORS;
    }
}
