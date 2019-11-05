import java.io.*;
import java.util.*;

public class MainCompilador {

    private static String[][] tabelaSintatica;
    private static Map<String, String> errosTabela;

    public static void main(String[] args) {

        //é necessário um arquivo de entrada com o programa a ser compilado
        try {
            String path = "/home/joanderson/Coding/Compilador/src/inRobot.txt";

            List<Token> tokenTable = analiseLexica(path);
            MainCompilador.popularTabelaSintatica();
            tabelaSintaticaToUpperCase();
            analiseSintatica(tokenTable);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void tabelaSintaticaToUpperCase() {
        for (int i = 0; i < 46; i++) {
            for (int j = 0; j < 25; j++) {
                if(tabelaSintatica[i][j] != null) {
                    tabelaSintatica[i][j] = tabelaSintatica[i][j].toUpperCase();
                    //System.out.println(tabelaSintatica[i][j]);
                }
            }
        }
    }

    private static boolean isTerminal(String simbolo) {
        for (int i = 0; i < 46; i++) {
            if (tabelaSintatica[i][0]!=null && tabelaSintatica[i][0].equals(simbolo)) {
                return true;
            }
        }
        return false;
    }

    private static String getLadoDireitoRegra(String naoTerminal, String terminal) {
        int linha = -1, coluna = -1;
        for (int i = 1; i < 46; i++) {
            if (tabelaSintatica[i][0].equals(terminal)) {
                linha = i;
                break;
            }
        }
        if (linha == -1) {
            return null;
        }
        for (int j = 1; j < 25; j++) {
            if (tabelaSintatica[0][j].equals(naoTerminal)) {
                coluna = j;
                break;
            }
        }
        if (coluna == -1) {
            return null;
        }
        return tabelaSintatica[linha][coluna];
    }


    private static void analiseSintatica(List<Token> cadeiaTokens) {
        //coloco $ no fim da cadeia:
        cadeiaTokens.add(new Token("$","$",cadeiaTokens.size(),0));
        //crio a pilha:
        Stack<String> pilha = new Stack<>();
        //adiciono $ na pilha:
        pilha.push("$");
        //adiciono o símbolo inicial da gramática:
        pilha.push(tabelaSintatica[0][1]);
        int ponteiroCadeia = 0;

        String topoPilha;
        Token simboloAtual;
        //todo: converter em maiúsculo a tabela sintática
        do {
            topoPilha = pilha.peek();
            simboloAtual = cadeiaTokens.get(ponteiroCadeia);
            //se topoPilha for terminal ou $:
            if (isTerminal(topoPilha) || topoPilha.equals("$")) {
                if (topoPilha.equals(simboloAtual.getToken())) {//lexema ou token?
                    pilha.pop();
                    ponteiroCadeia++;
                }
                else{
                    System.out.println("Erro 1 em " + simboloAtual.getToken() + " comparado com " + topoPilha);
                    System.out.println("Linha: " + simboloAtual.getLinha() + "Coluna: " + simboloAtual.getColuna());
                    return;
                }
            }
            else {//topoPilha é um não-terminal
                //se existe na tabela sintática uma regra de topoPilha::=simboloAtual alguma outra coisa
                String ladoDireito = getLadoDireitoRegra(topoPilha,simboloAtual.getToken());
                if (ladoDireito != null) {
                    pilha.pop();
                    /////empilhar em sentido inverso o ladoDireito da regra:
                    String[] ladoDireitoStrings = ladoDireito.split(" ");
                    for (int i = ladoDireitoStrings.length-1; i >= 0; i--) {
                        pilha.push(ladoDireitoStrings[i]);
                    }
                }
                else {
                    System.out.println("Erro 2 em " + simboloAtual.getToken() + " comparado com " + topoPilha);
                    System.out.println("Linha: " + simboloAtual.getLinha() + " Coluna: " + simboloAtual.getColuna());
                    return;
                }
            }
        }
        while(!(topoPilha.equals(simboloAtual.getToken()) && pilha.peek().equals("$")));
        System.out.println("Análise sintática: tudo certo!!");

    }

    private static void popularTabelaSintatica() {
        tabelaSintatica = new String[46][25];
        /*
        String path = "/home/joanderson/Coding/Compilador/src/inTerminalsSintaticTable.txt";
        Scanner sc = new Scanner(path);
        int i=0;
        while(sc.hasNextLine()) {
            tabelaSintatica[i][0] = sc.nextLine();
            i++;
//            System.out.println(sc.nextLine());
        }
        //salvei todos os terminais
        sc.close();
        path = "/home/joanderson/Coding/Compilador/src/inNonTerminalsSintaticTable.txt";
        sc = new Scanner(path);
        i=0;
        while(sc.hasNextLine()) {
            tabelaSintatica[0][i] = sc.nextLine();
            i++;
            System.out.println("cont" + i);
        }
        sc.close();
        //salvei os não-terminais
         */

        //Não-terminais:
        tabelaSintatica[0][1] = "Programa";
        tabelaSintatica[0][2] = "Declaracao";
        tabelaSintatica[0][3] = "Bloco";
        tabelaSintatica[0][4] = "ComandoMais";
        tabelaSintatica[0][5] = "Comando";
        tabelaSintatica[0][6] = "ComandoBloco";
        tabelaSintatica[0][7] = "Iteracao";
        tabelaSintatica[0][8] = "Laco";
        tabelaSintatica[0][9] = "Condicional";
        tabelaSintatica[0][10] = "CondicionalSenao";
        tabelaSintatica[0][11] = "Instrucao";
        tabelaSintatica[0][12] = "NumInstrucao";
        tabelaSintatica[0][13] = "InstrucaoPassos";
        tabelaSintatica[0][14] = "Condicao";
        tabelaSintatica[0][15] = "EstadoLampada";
        tabelaSintatica[0][16] = "EstadoRobo";
        tabelaSintatica[0][17] = "LocalLampada";
        tabelaSintatica[0][18] = "Identificador";
        tabelaSintatica[0][19] = "LetraDigito";
        tabelaSintatica[0][20] = "Numero";
        tabelaSintatica[0][21] = "DigitoNum";
        tabelaSintatica[0][22] = "Letra";
        tabelaSintatica[0][23] = "Digito";
        tabelaSintatica[0][24] = "Sentido";

        //terminais:
        tabelaSintatica[1][0] = "a";
        tabelaSintatica[2][0] = "acenda";
        tabelaSintatica[3][0] = "acesa";
        tabelaSintatica[4][0] = "aguarde";
        tabelaSintatica[5][0] = "apagada";
        tabelaSintatica[6][0] = "apague";
        tabelaSintatica[7][0] = "ate";
        tabelaSintatica[8][0] = "bloqueada";
        tabelaSintatica[9][0] = "como";
        tabelaSintatica[10][0] = "definainstrucao";
        tabelaSintatica[11][0] = "direita";
        tabelaSintatica[12][0] = "enquanto";
        tabelaSintatica[13][0] = "entao";
        tabelaSintatica[14][0] = "esquerda";
        tabelaSintatica[15][0] = "execucaoinicio";
        tabelaSintatica[16][0] = "faca";
        tabelaSintatica[17][0] = "fim";
        tabelaSintatica[18][0] = "fimexecucao";
        tabelaSintatica[19][0] = "fimpara";
        tabelaSintatica[20][0] = "fimprograma";
        tabelaSintatica[21][0] = "fimrepita";
        tabelaSintatica[22][0] = "fimse";
        tabelaSintatica[23][0] = "fimsenao";
        tabelaSintatica[24][0] = "finalize";
        tabelaSintatica[25][0] = "frente";
        tabelaSintatica[26][0] = "inicio";
        tabelaSintatica[27][0] = "lampada";
        tabelaSintatica[28][0] = "mova";
        tabelaSintatica[29][0] = "movimentando";
        tabelaSintatica[30][0] = "ocupado";
        tabelaSintatica[31][0] = "para";
        tabelaSintatica[32][0] = "parado";
        tabelaSintatica[33][0] = "pare";
        tabelaSintatica[34][0] = "passos";
        tabelaSintatica[35][0] = "programainicio";
        tabelaSintatica[36][0] = "pronto";
        tabelaSintatica[37][0] = "repita";
        tabelaSintatica[38][0] = "robo";
        tabelaSintatica[39][0] = "se";
        tabelaSintatica[40][0] = "senao";
        tabelaSintatica[41][0] = "vezes";
        tabelaSintatica[42][0] = "vire";
        tabelaSintatica[43][0] = "id";
        tabelaSintatica[44][0] = "num";
        tabelaSintatica[45][0] = "$";


        tabelaSintatica[38-3][1] = "programainicio Declaracao execucaoinicio Comando ComandoMais fimexecucao fimprograma";

        tabelaSintatica[13-3][2] = "definainstrucao Identificador como Comando Declaracao";
        tabelaSintatica[18-3][2] = " ";

        tabelaSintatica[29-3][3] = "inicio ComandoBloco fim";

        tabelaSintatica[5-3][4] = "Comando ComandoMais";
        tabelaSintatica[7-3][4] = "Comando ComandoMais";
        tabelaSintatica[9-3][4] = "Comando ComandoMais";
        tabelaSintatica[15-3][4] = "Comando ComandoMais";
        tabelaSintatica[21-3][4] = " ";
        tabelaSintatica[27-3][4] = "Comando ComandoMais";
        tabelaSintatica[29-3][4] = "Comando ComandoMais";
        tabelaSintatica[31-3][4] = "Comando ComandoMais";
        tabelaSintatica[36-3][4] = "Comando ComandoMais";
        tabelaSintatica[40-3][4] = "Comando ComandoMais";
        tabelaSintatica[42-3][4] = "Comando ComandoMais";
        tabelaSintatica[45-3][4] = "Comando ComandoMais";
        tabelaSintatica[46-3][4] = "Comando ComandoMais";

        tabelaSintatica[5-3][5] = "Instrucao";
        tabelaSintatica[7-3][5] = "Instrucao";
        tabelaSintatica[9-3][5] = "Instrucao";
        tabelaSintatica[15-3][5] = "Laco";
        tabelaSintatica[27-3][5] = "Instrucao";
        tabelaSintatica[29-3][5] = "Bloco";
        tabelaSintatica[31-3][5] = "Instrucao";
        tabelaSintatica[36-3][5] = "Instrucao";
        tabelaSintatica[40-3][5] = "Iteracao";
        tabelaSintatica[42-3][5] = "Condicional";
        tabelaSintatica[45-3][5] = "Instrucao";
        tabelaSintatica[46-3][5] = "Instrucao";

        tabelaSintatica[5-3][6] = "Comando ComandoBloco";
        tabelaSintatica[7-3][6] = "Comando ComandoBloco";
        tabelaSintatica[9-3][6] = "Comando ComandoBloco";
        tabelaSintatica[15-3][6] = "Comando ComandoBloco";
        tabelaSintatica[20-3][6] = " ";
        tabelaSintatica[27-3][6] = "Comando ComandoBloco";
        tabelaSintatica[29-3][6] = "Comando ComandoBloco";
        tabelaSintatica[31-3][6] = "Comando ComandoBloco";
        tabelaSintatica[36-3][6] = "Comando ComandoBloco";
        tabelaSintatica[40-3][6] = "Comando ComandoBloco";
        tabelaSintatica[42-3][6] = "Comando ComandoBloco";
        tabelaSintatica[45-3][6] = "Comando ComandoBloco";
        tabelaSintatica[46-3][6] = "Comando ComandoBloco";

        tabelaSintatica[40-3][7] = "repita Numero vezes Comando fimrepita";

        tabelaSintatica[15-3][8] = "enquanto Condicao faca Comando fimpara";

        tabelaSintatica[42-3][9] = "se Condicao faca Comando fimse CondicionalSenao";

        tabelaSintatica[13-3][10] = " ";
        tabelaSintatica[17-3][10] = " ";
        tabelaSintatica[20-3][10] = " ";
        tabelaSintatica[21-3][10] = " ";
        tabelaSintatica[22-3][10] = " ";
        tabelaSintatica[24-3][10] = " ";
        tabelaSintatica[25-3][10] = " ";
        tabelaSintatica[26-3][10] = " ";
        tabelaSintatica[43-3][10] = "senao Comando fimsenao";

        tabelaSintatica[5-3][11] = "acenda lampada";
        tabelaSintatica[7-3][11] = "aguarde ate Condicao";
        tabelaSintatica[9-3][11] = "apague lampada";
        tabelaSintatica[27-3][11] = "finalize";
        tabelaSintatica[31-3][11] = "mova NumInstrucao InstrucaoPassos";
        tabelaSintatica[36-3][11] = "pare";
        tabelaSintatica[45-3][11] = "vire para Sentido";
        tabelaSintatica[46-3][11] = "Identificador";

        tabelaSintatica[5-3][12] = " ";
        tabelaSintatica[7-3][12] = " ";
        tabelaSintatica[9-3][12] = " ";
        tabelaSintatica[13-3][12] = " ";
        tabelaSintatica[15-3][12] = " ";
        tabelaSintatica[18-3][12] = " ";
        tabelaSintatica[20-3][12] = " ";
        tabelaSintatica[21-3][12] = " ";
        tabelaSintatica[22-3][12] = " ";
        tabelaSintatica[24-3][12] = " ";
        tabelaSintatica[25-3][12] = " ";
        tabelaSintatica[27-3][12] = " ";
        tabelaSintatica[29-3][12] = " ";
        tabelaSintatica[31-3][12] = " ";
        tabelaSintatica[36-3][12] = " ";
        tabelaSintatica[37-3][12] = " ";
        tabelaSintatica[40-3][12] = " ";
        tabelaSintatica[42-3][12] = " ";
        tabelaSintatica[45-3][12] = " ";
        tabelaSintatica[46-3][12] = " ";
        tabelaSintatica[47-3][12] = "Numero NumInstrucao";

        tabelaSintatica[5-3][13] = " ";
        tabelaSintatica[7-3][13] = " ";
        tabelaSintatica[9-3][13] = " ";
        tabelaSintatica[13-3][13] = " ";
        tabelaSintatica[15-3][13] = " ";
        tabelaSintatica[18-3][13] = " ";
        tabelaSintatica[20-3][13] = " ";
        tabelaSintatica[21-3][13] = " ";
        tabelaSintatica[22-3][13] = " ";
        tabelaSintatica[24-3][13] = " ";
        tabelaSintatica[25-3][13] = " ";
        tabelaSintatica[27-3][13] = " ";
        tabelaSintatica[29-3][13] = " ";
        tabelaSintatica[31-3][13] = " ";
        tabelaSintatica[36-3][13] = " ";
        tabelaSintatica[37-3][13] = "passos";
        tabelaSintatica[40-3][13] = " ";
        tabelaSintatica[42-3][13] = " ";
        tabelaSintatica[45-3][13] = " ";
        tabelaSintatica[46-3][13] = " ";

        tabelaSintatica[14-3][14] = "direita robo bloqueada";
        tabelaSintatica[17-3][14] = "esquerda robo bloqueada";
        tabelaSintatica[28-3][14] = "frente robo bloqueada";
        tabelaSintatica[30-3][14] = "lampada EstadoLampada a LocalLampada";
        tabelaSintatica[41-3][14] = "robo EstadoRobo";

        tabelaSintatica[6-3][15] = "acesa";
        tabelaSintatica[8-3][15] = "apagada";

        tabelaSintatica[32-3][16] = "movimentando";
        tabelaSintatica[33-3][16] = "ocupado";
        tabelaSintatica[35-3][16] = "parado";
        tabelaSintatica[39-3][16] = "pronto";

        tabelaSintatica[14-3][17] = "Sentido";
        tabelaSintatica[17-3][17] = "Sentido";
        tabelaSintatica[28-3][17] = "frente";

        tabelaSintatica[46-3][18] = "id";

        tabelaSintatica[5-3][19] = " ";
        tabelaSintatica[7-3][19] = " ";
        tabelaSintatica[9-3][19] = " ";
        tabelaSintatica[12-3][19] = " ";
        tabelaSintatica[13-3][19] = " ";
        tabelaSintatica[15-3][19] = " ";
        tabelaSintatica[18-3][19] = " ";
        tabelaSintatica[19-3][19] = " ";
        tabelaSintatica[20-3][19] = " ";
        tabelaSintatica[21-3][19] = " ";
        tabelaSintatica[22-3][19] = " ";
        tabelaSintatica[24-3][19] = " ";
        tabelaSintatica[25-3][19] = " ";
        tabelaSintatica[27-3][19] = " ";
        tabelaSintatica[29-3][19] = " ";
        tabelaSintatica[31-3][19] = " ";
        tabelaSintatica[36-3][19] = " ";
        tabelaSintatica[40-3][19] = " ";
        tabelaSintatica[42-3][19] = " ";
        tabelaSintatica[45-3][19] = " ";
        tabelaSintatica[46-3][19] = "Letra LetraDigito";
        tabelaSintatica[47-3][19] = "Digito LetraDigito";

        tabelaSintatica[5-3][20] = " ";
        tabelaSintatica[7-3][20] = " ";
        tabelaSintatica[9-3][20] = " ";
        tabelaSintatica[13-3][20] = " ";
        tabelaSintatica[15-3][20] = " ";
        tabelaSintatica[18-3][20] = " ";
        tabelaSintatica[19-3][20] = " ";
        tabelaSintatica[20-3][20] = " ";
        tabelaSintatica[21-3][20] = " ";
        tabelaSintatica[22-3][20] = " ";
        tabelaSintatica[24-3][20] = " ";
        tabelaSintatica[25-3][20] = " ";
        tabelaSintatica[27-3][20] = " ";
        tabelaSintatica[29-3][20] = " ";
        tabelaSintatica[31-3][20] = " ";
        tabelaSintatica[36-3][20] = " ";
        tabelaSintatica[37-3][20] = " ";
        tabelaSintatica[40-3][20] = " ";
        tabelaSintatica[42-3][20] = " ";
        tabelaSintatica[44-3][20] = " ";
        tabelaSintatica[45-3][20] = " ";
        tabelaSintatica[46-3][20] = " ";
        tabelaSintatica[47-3][20] = "num";

        tabelaSintatica[5-3][21] = " ";
        tabelaSintatica[7-3][21] = " ";
        tabelaSintatica[9-3][21] = " ";
        tabelaSintatica[13-3][21] = " ";
        tabelaSintatica[15-3][21] = " ";
        tabelaSintatica[18-3][21] = " ";
        tabelaSintatica[19-3][21] = " ";
        tabelaSintatica[20-3][21] = " ";
        tabelaSintatica[21-3][21] = " ";
        tabelaSintatica[22-3][21] = " ";
        tabelaSintatica[24-3][21] = " ";
        tabelaSintatica[25-3][21] = " ";
        tabelaSintatica[27-3][21] = " ";
        tabelaSintatica[29-3][21] = " ";
        tabelaSintatica[31-3][21] = " ";
        tabelaSintatica[36-3][21] = " ";
        tabelaSintatica[37-3][21] = " ";
        tabelaSintatica[40-3][21] = " ";
        tabelaSintatica[42-3][21] = " ";
        tabelaSintatica[44-3][21] = " ";
        tabelaSintatica[45-3][21] = " ";
        tabelaSintatica[46-3][21] = " ";
        tabelaSintatica[47-3][21] = "Digito DigitoNum";

        tabelaSintatica[46-3][22] = "A|a|B|b|...|Z|z";

        tabelaSintatica[46-3][23] = "0|...|9";

        tabelaSintatica[14-3][24] = "direita";
        tabelaSintatica[17-3][24] = "esquerda";

        //testar:
        /*for (int j = 0; j < 46; j++) {
            for (int k = 0; k < 25; k++) {
                System.out.print(tabelaSintatica[j][k] + " | ");
            }
            System.out.println();
        }*/

    }

    private static List<Token> analiseLexica(String path) throws IOException {
        char[] line;

        LinhaAutomato[] autReconhecedorCadeias = new LinhaAutomato[5];
        autReconhecedorCadeias[0] = new LinhaAutomato(1, 2, 4);
        autReconhecedorCadeias[1] = new LinhaAutomato(1, 3, 4);
        autReconhecedorCadeias[2] = new LinhaAutomato(4, 2, 4);
        autReconhecedorCadeias[3] = new LinhaAutomato(3, 3, 4);
        autReconhecedorCadeias[4] = new LinhaAutomato(4, 4, 4);//4 é estado de erro

        // Inicializando tabela de palavras reservadas
        TabelaReservada tabelaReservada = new TabelaReservada();
        Map<String, String> palavrasReservadas = tabelaReservada.getPalavrasReservadas();

        // Inicializando tabela de descrições dos erros
        TabelaErros tabelaErros = new TabelaErros();
        errosTabela = tabelaErros.getErrors();

        // Inicializando lista de erros
        List<Erro> listError = new ArrayList<Erro>();

        // Inicializando lista de tokens resultantes
        List<Token> tokenList = new ArrayList<Token>();

        // Inicializando tabela de símbolos
        Map<String, TabelaSimbolosEntry> tabelaSimbolos = new HashMap<String, TabelaSimbolosEntry>();

        int estadoAtual, estadoAnterior;
        int linha = 0;
        int coluna = 0;
        StringBuilder lexema = new StringBuilder();

        Scanner sc = new Scanner(new File(path));
        while (sc.hasNextLine()) {
            linha++;
            line = sc.nextLine().toCharArray();
            //começo a procurar por tokens
            estadoAtual = 0;
            estadoAnterior = 0;

            for (int i = 0; i <= line.length; i++) {
                // Se ler um espaço em branco atualiza o contador de coluna
                if (lexema.toString().compareTo("") == 0)
                    coluna = i + 1;

                if (i == line.length) { /*se estou no fim da linha*/
                    if (estadoAtual == 4) {//estado de erro
                        switch (estadoAnterior) {
                            // Caso o último estado anterior tenha sido o 1 é um identificador mal formado
                            case 1: {
                                Object erroObj = errosTabela.get("<id_mal_formado>");
                                Erro erro = new Erro("<id_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // Caso o último estado anterior tenha sido o 2 é um número mal formado
                            case 2: {
                                Object erroObj = errosTabela.get("<nmr_mal_formado>");
                                Erro erro = new Erro("<nmr_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // É um símbolo mal formado caso contrário
                            default: {
                                Object erroObj = errosTabela.get("<simb_not_id>");
                                Erro erro = new Erro("<simb_not_id>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                            }
                        }
                    } else {
                        // Verifica se o identificador é uma palavra reservada
                        if (palavrasReservadas.containsKey(lexema.toString())) {
                            Token token = new Token(lexema.toString(), palavrasReservadas.get(lexema.toString()), linha, coluna);
                            tokenList.add(token);
                        }
                        // Verifica se é um número
                        else if (lexema.toString().matches("\\d+")) {
                            Token token = new Token(lexema.toString(), "NUM", linha, coluna);
                            tokenList.add(token);
                        }
                        // Caso contrário é um identificador
                        else {
                            String tokenString = "<identificador, " + lexema.toString() + ">";
                            if (tokenString.matches("<identificador, >")) {
                                continue;//token vazio (apenas com quebras) não inserido
                            }
                            Token token = new Token(lexema.toString(), "ID", linha, coluna);
                            tokenList.add(token);

                            // Verifica se já existe na tabela de símbolos e insere caso contrário
                            if (!tabelaSimbolos.containsKey(tokenString)) {
                                TabelaSimbolosEntry newEntry = new TabelaSimbolosEntry(tokenString, lexema.toString());
                                tabelaSimbolos.put(tokenString, newEntry);
                            }
                        }
                    }

                    estadoAtual = 0;
                    lexema = new StringBuilder();//resetei o lexema
                    break;
                }
                char c = line[i];
                if (c == '#') {
                    if (estadoAtual == 0) {
                        break;//começo de linha tem comentário
                    }
                    if (estadoAtual == 4) {//estado de erro
                        switch (estadoAnterior) {
                            // Caso o último estado anterior tenha sido o 1 é um identificador mal formado
                            case 1: {
                                Object erroObj = errosTabela.get("<id_mal_formado>");

                                Erro erro = new Erro("<id_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // Caso o último estado anterior tenha sido o 2 é um número mal formado
                            case 2: {
                                Object erroObj = errosTabela.get("<nmr_mal_formado>");
                                Erro erro = new Erro("<nmr_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // É um símbolo mal formado caso contrário
                            default: {
                                Object erroObj = errosTabela.get("<simb_not_id>");
                                Erro erro = new Erro("<simb_not_id>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                            }
                        }
                    } else {
                        // Verifica se o identificador é uma palavra reservada
                        if (palavrasReservadas.containsKey(lexema.toString())) {
                            Token token = new Token(lexema.toString(), palavrasReservadas.get(lexema.toString()), linha, coluna);
                            tokenList.add(token);
                        }
                        // Verifica se é um número
                        else if (lexema.toString().matches("\\d+")) {
                            Token token = new Token(lexema.toString(), "NUM", linha, coluna);
                            tokenList.add(token);
                        }
                        // Caso contrário é um identificador
                        else {
                            String tokenString = "<identificador, " + lexema.toString() + ">";
                            Token token = new Token(lexema.toString(), "ID", linha, coluna);
                            tokenList.add(token);

                            // Verifica se já existe na tabela de símbolos e insere caso contrário
                            if (!tabelaSimbolos.containsKey(tokenString)) {
                                TabelaSimbolosEntry newEntry = new TabelaSimbolosEntry(tokenString, lexema.toString());
                                tabelaSimbolos.put(tokenString, newEntry);
                            }
                        }
                    }

                    estadoAtual = 0;
                    lexema = new StringBuilder();//resetei o lexema
                    break; // ignora restante da linha em caso de comentário
                }

                if (c <= 32) {
                    if (estadoAtual == 0) continue;
                    if (estadoAtual == 4) {//estado de erro
                        switch (estadoAnterior) {
                            // Caso o último estado anterior tenha sido o 1 é um identificador mal formado
                            case 1: {
                                Object erroObj = errosTabela.get("<id_mal_formado>");
                                Erro erro = new Erro("<id_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // Caso o último estado anterior tenha sido o 2 é um número mal formado
                            case 2: {
                                Object erroObj = errosTabela.get("<nmr_mal_formado>");
                                Erro erro = new Erro("<nmr_mal_formado>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                                break;
                            }
                            // É um símbolo mal formado caso contrário
                            default: {



                                Object erroObj = errosTabela.get("<simb_not_id>");
                                Erro erro = new Erro("<simb_not_id>", erroObj.toString()
                                        .replace("{lexema}", getLexemaOriginal(path,linha,coluna))
                                        .replace("{linha}", String.valueOf(linha))
                                        .replace("{coluna}", String.valueOf(coluna))
                                );
                                listError.add(erro);
                            }
                        }
                    } else {
                        // Verifica se o identificador é uma palavra reservada
                        if (palavrasReservadas.containsKey(lexema.toString())) {
                            Token token = new Token(lexema.toString(), palavrasReservadas.get(lexema.toString()), linha, coluna);
                            tokenList.add(token);
                        }
                        // Verifica se é um número
                        else if (lexema.toString().matches("\\d+")) {
                            Token token = new Token(lexema.toString(), "NUM", linha, coluna);
                            tokenList.add(token);
                        }
                        // Caso contrário é um identificador
                        else {
                            String tokenString = "<identificador, " + lexema.toString() + ">";
                            Token token = new Token(lexema.toString(), "ID", linha, coluna);
                            tokenList.add(token);

                            // Verifica se já existe na tabela de símbolos e insere caso contrário
                            if (!tabelaSimbolos.containsKey(tokenString)) {
                                TabelaSimbolosEntry newEntry = new TabelaSimbolosEntry(tokenString, lexema.toString());
                                tabelaSimbolos.put(tokenString, newEntry);
                            }
                        }
                    }

                    estadoAtual = 0;
                    lexema = new StringBuilder();//resetei o lexema
                    continue;//finaliza a palavra atual
                }

                if (97 <= c && c <= 122) {//letra minuscula
                    c -= 32;//converto em maiúscula
                }

                lexema.append(c);

                //Autômato reconhecedor de cadeias:
                if (estadoAtual != 4)
                    estadoAnterior = estadoAtual;

                if (65 <= c && c <= 90) {//letra
                    switch (estadoAtual) {
                        case 0://caso eu esteja no estado 0 e leia uma letra,
                            estadoAtual = 1;//muda para o estado 1;
                            break;
                        case 2:
                            estadoAtual = 4;
                            break;
                        case 1:
                        case 3:
                            //não muda de estado
                            break;
                        case 4:
                            //estado de erro. Não muda de estado
                            break;
                    }
                } else if (48 <= c && c <= 57) {//número
                    switch (estadoAtual) {
                        case 0://caso eu esteja no estado 0 e leia uma letra,
                            estadoAtual = 2;//muda para o estado 1;
                            break;
                        case 1:
                            estadoAtual = 3;
                            break;
                        case 2:
                        case 3:
                            //não muda de estado
                            break;
                        case 4:
                            //estado de erro. Não muda de estado
                            break;
                    }
                } else {//é outro símbolo
                    estadoAnterior = 4;
                    estadoAtual = 4;//erro
                    continue;
                }
            }
        }

        sc.close();

        if (!listError.isEmpty()) {
            System.out.println("ERROS");
            for (Erro erro : listError) {
                System.out.println("Code: " + erro.getCodigo() + " Erro: " + erro.getMensagem());
            }
        }

        if (!tokenList.isEmpty()) {
            System.out.println();
            System.out.println("TOKENS");
            for (Token token : tokenList) {
                System.out.println("Lexema: " + token.getLexema()
                        + " Token: " + token.getToken()
                        + " Linha: " + token.getLinha()
                        + " Coluna: " + token.getColuna());
            }
        }

        if (!tabelaSimbolos.isEmpty()) {
            System.out.println();
            System.out.println("SIMBOLOS");
            for (String token : tabelaSimbolos.keySet()) {
                System.out.println("Lexema: " + tabelaSimbolos.get(token).getLexema() + " Token: " + tabelaSimbolos.get(token).getToken());
            }
        }

        return tokenList;
    }

    private static String getLexemaOriginal(String path, int linha, int coluna) throws FileNotFoundException {
        Scanner sc2 = new Scanner(new File(path));
        int cont = 1;
        String aux = sc2.nextLine();
        while (cont != linha){
            aux = sc2.nextLine();
            cont++;
        }//saio na linha certa
        cont = 1;
        char[] auxToChar = aux.toCharArray();
        //char[] lexemaOriginal = new char[];
        StringBuilder sbAux = new StringBuilder();
        for (int j = coluna-1; j < auxToChar.length; j++) {
            sbAux.append(String.valueOf(auxToChar[j]));
            if (auxToChar[j] <= 32) break;//quebra de texto
        }
        sc2.close();
        return sbAux.toString();
    }


}
