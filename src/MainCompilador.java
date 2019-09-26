import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainCompilador {

    public static void main(String[] args) {

        //é necessário um arquivo de entrada com o programa a ser compilado
        try {
            String path = "/home/joanderson/Coding/Compilador/src/inRobot.txt";

            ArrayList<Token> tokenTable = analiseLexica(path);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<Token> analiseLexica(String path) throws IOException {
        char[] line;

        LinhaAutomato[] autReconhecedorCadeias = new LinhaAutomato[5];
        autReconhecedorCadeias[0] = new LinhaAutomato(1,2,4);
        autReconhecedorCadeias[1] = new LinhaAutomato(1,3,4);
        autReconhecedorCadeias[2] = new LinhaAutomato(4,2,4);
        autReconhecedorCadeias[3] = new LinhaAutomato(3,3,4);
        autReconhecedorCadeias[4] = new LinhaAutomato(4,4,4);//4 é estado de erro
        int estadoAtual;
        int linha, coluna;
        StringBuilder lexema = new StringBuilder();

        Scanner sc = new Scanner(new File(path));
        while (sc.hasNextLine()) {
            line = sc.nextLine().toCharArray();
            //começo a procurar por tokens
            estadoAtual = 0;
            //for (char c : line) {
            for (int i = 0; i <= line.length; i++) {

                if (i == line.length){ /*se estou no fim da linha*/
                    if (estadoAtual == 4) {//estado de erro
                        System.out.println("Erro no seguinte lexema:" + lexema);


                        //todo: imprimir erro, lendo o documento original e mostrando onde está o erro e qual erro é
                    }
                    estadoAtual = 0;
                    System.out.println("Lexema: " + lexema);


                    //todo: testar se está na tabela de palavras reservadas, se tá na tabela de simbolos,
                    // salvar token na tabela final e zerá-lo ou guardar um erro


                    lexema = new StringBuilder();//resetei o lexema
                    break;
                }
                char c = line[i];
                if (c == '#' && estadoAtual!=0) {
                    if (estadoAtual == 4) {//estado de erro
                        System.out.println("Erro no seguinte lexema:" + lexema);


                        //todo: imprimir erro, lendo o documento original e mostrando onde está o erro e qual erro é


                    }
                    estadoAtual = 0;
                    System.out.println("Lexema: " + lexema);


                    //todo: testar se está na tabela de palavras reservadas, se tá na tabela de simbolos,
                    // salvar token na tabela final e zerá-lo ou guardar um erro


                    lexema = new StringBuilder();//resetei o lexema
                    break; // ignora restante da linha em caso de comentário
                }
//                if ((c == '\n' || c == ' ' || c == 9) && estadoAtual!=0) {
                if (c <= 32) {
                    if (estadoAtual == 0) continue;
                    if (estadoAtual == 4) {//estado de erro
                        System.out.println("Erro no seguinte lexema:" + lexema);


                        //todo: imprimir erro, lendo o documento original e mostrando onde está o erro e qual erro é
                    }
                    estadoAtual = 0;
                    System.out.println("Lexema: " + lexema);


                    //todo: testar se está na tabela de palavras reservadas, se tá na tabela de simbolos,
                    // salvar token na tabela final e zerá-lo ou guardar um erro


                    lexema = new StringBuilder();//resetei o lexema
                    continue;//finaliza a palavra atual
                }

                if (97 <= c && c <= 122) {//letra minuscula
                    c-=32;//converto em maiúscula
                }

                lexema.append(c);

                //Autômato reconhecedor de cadeias:

                if (65 <= c && c <= 90) {//letra
                    switch (estadoAtual) {
                        case 0://caso eu esteja no estado 0 e leia uma letra,
                            estadoAtual = 1;//muda para o estado 1;
                            break;
                        case 1:
                            //não muda de estado
                            break;
                        case 2:
                            estadoAtual = 4;
                            break;
                        case 3:
                            //não muda de estado
                            break;
                        case 4:
                            //estado de erro. Não muda de estado
                            break;
                    }
                }
                else if (48 <= c && c <= 57) {//número
                    switch (estadoAtual) {
                        case 0://caso eu esteja no estado 0 e leia uma letra,
                            estadoAtual = 2;//muda para o estado 1;
                            break;
                        case 1:
                            estadoAtual = 3;
                            break;
                        case 2:
                            break;
                        case 3:
                            //não muda de estado
                            break;
                        case 4:
                            //estado de erro. Não muda de estado
                            break;
                    }
                }
                else {//é outro símbolo
                    estadoAtual = 4;//erro
                    continue;
                }


            }
        }



        sc.close();
        return new ArrayList<Token>();
    }







}
