import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Stack;


public class Tree {

    public static ArrayList<No> tree = new ArrayList<>();
    private Stack<Integer> semanticStack;
    private ArrayList<Integer> stackSemantico;
    public static ArrayList<String> errosSemanticos = new ArrayList<>();
    private static int idUnico = 0;

    public static int getIdUnico() {
        return ++idUnico;
    }

    public Tree() {
        //tree = new ArrayList<>();
        semanticStack = new Stack<>();
        //errosSemanticos = new ArrayList<>();
    }

    public int nextNoVazio() {
        /*for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).temCampoVazio()) {//todo: conferir essa função temCampoVazio()
                return i;
            }
        }*/
        for (int i = tree.size()-2; i >= 0; i--) {
            if (tree.get(i).temCampoVazio()) {//todo: conferir essa função temCampoVazio()
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> addNonTerminal(String nonTerminal, String regra) {
        boolean lambda = regra.equals(" ");
        if(nonTerminal.equals("PROGRAMA")) {
            tree.add(new Programa());

            return gerenciarStackSemantico();
        }
        if(nonTerminal.equals("COMANDOMAIS")) {
            tree.add(new ComandoMais(lambda));
        }
        if(nonTerminal.equals("DECLARACAO")) {
            tree.add(new Declaracao(lambda));
        }
        if(nonTerminal.equals("BLOCO")) {
            tree.add(new Bloco());
        }
        if(nonTerminal.equals("COMANDOBLOCO")) {
            tree.add(new ComandoBloco(lambda));//se a regra é lambda
        }
        if(nonTerminal.equals("COMANDO")) {
            tree.add(new Comando(regra));
        }
        if(nonTerminal.equals("ITERACAO")) {
            tree.add(new Iteracao());
        }
        if(nonTerminal.equals("LACO")) {
            tree.add(new Laco());
        }
        if(nonTerminal.equals("CONDICIONAL")) {
            tree.add(new Condicional());
        }
        if(nonTerminal.equals("CONDICIONALSENAO")) {
            tree.add(new CondicionalSenao(lambda));
        }
        if(nonTerminal.equals("NUMINSTRUCAO")) {
            tree.add(new NumInstrucao(lambda));
        }
        if(nonTerminal.equals("INSTRUCAO")) {
            tree.add(new Instrucao(regra));
        }
        if(nonTerminal.equals("INSTRUCAOPASSOS")) {
            tree.add(new InstrucaoPassos(lambda,regra));
        }
        if(nonTerminal.equals("CONDICAO")) {
            tree.add(new Condicao(regra));
        }
        if(nonTerminal.equals("ESTADOLAMPADA")) {
            tree.add(new EstadoLampada(regra));
        }
        if(nonTerminal.equals("ESTADOROBO")) {
            tree.add(new EstadoRobo(regra));
        }
        if(nonTerminal.equals("LOCALLAMPADA")) {
            tree.add(new LocalLampada(regra));
        }
       /* if(nonTerminal.equals("IDENTIFICADOR")) {
            tree.add(new Identificador(regra));
        }*/
        /*if(nonTerminal.equals("NUMERO")) {
            tree.add(new Numero(regra));
        }*/
        if(nonTerminal.equals("SENTIDO")) {
            tree.add(new Sentido(regra));
        }
        int posicaoOndeFoiAlocado = tree.size()-1;
        tree.get(nextNoVazio()).addNoEmProximaPosicaoVazia(posicaoOndeFoiAlocado,nonTerminal);

        //if(tree.get(tree.size()-1).temCampoVazio())
        return gerenciarStackSemantico();
    }
/*
    private void manageSemanticStack(int iQueAcabeiDeInserir) {
        for (No no : tree) {
            if (no.hasChild(iQueAcabeiDeInserir)) {

            }
        }
        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).hasChild(iQueAcabeiDeInserir)) {
                if(tree.get(i).getNome() != null && tree.get(iQueAcabeiDeInserir).getNome() != null) {
                    if(!tree.get(i).getNome().equals(tree.get(iQueAcabeiDeInserir).getNome())) {
                        //erro!!!
                        System.out.println("Erro: duas declarações com mesmo nome");
                    }
                }
                tree.get(iQueAcabeiDeInserir).setNome(tree.get(i).getNome());
                manageSemanticStack(i);
                return;
            }
        }

    }*/



    private ArrayList<String> updateAsm() {
        tree.get(0).updateAsm();
        return tree.get(0).getAsm();
    }















    private ArrayList<String> gerenciarStackSemantico() {
        ArrayList<String>resultado = new ArrayList<>();
        /*if (semanticStack.isEmpty()) {
            semanticStack.push(tree.size()-1);//coloco na pilha semântica o último elemento da árvore
            return;
        }*/
        int topoAnterior = -1;//todo: se não for null
        if (!semanticStack.isEmpty()) {
            topoAnterior = semanticStack.peek();
        }
        semanticStack.push(tree.size()-1);//coloco na pilha semântica o último elemento da árvore
        if(!semanticStack.isEmpty() && topoAnterior!=-1 && tree.get(topoAnterior).getAtualProibido() != null && tree.get(topoAnterior).getClass() == Sentido.class) {
            //não coloco atualProibido nesse
        }
        else {
            if (!semanticStack.isEmpty() && topoAnterior!=-1 && tree.get(topoAnterior).getProximoProibido() != null) {
                boolean teste = tree.get(semanticStack.peek()).setAtualProibido(tree.get(topoAnterior).getProximoProibido());
                if (!teste) {
                    //System.out.println("1Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                    resultado.add("Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                }
                tree.get(topoAnterior).setProximoProibido(null);
            }
            if (!semanticStack.isEmpty() && topoAnterior!=-1 && tree.get(semanticStack.peek()).getClass() == Sentido.class) {
                if (tree.get(topoAnterior).getAtualProibido() != null && tree.get(topoAnterior).getAtualProibido().matches("VIRE.*")) {
                    String[] vetorDeProibidos = tree.get(topoAnterior).getAtualProibido().split(" ");
                    boolean teste = tree.get(semanticStack.peek()).setAtualProibido(vetorDeProibidos[vetorDeProibidos.length-1]);//pego só a última palavra, que é o sentido
                    if (!teste) {
                        //System.out.println("2Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                        resultado.add("Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                    }
                }
            }
            else if (!semanticStack.isEmpty() && topoAnterior!=-1 && tree.get(topoAnterior).getAtualProibido() != null) {
                boolean teste = tree.get(semanticStack.peek()).setAtualProibido(tree.get(topoAnterior).getAtualProibido());
                if (!teste) {
                    //System.out.println("3Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                    resultado.add("Erro semântico: instrução \"" + tree.get(topoAnterior).getAtualProibido() + "\" não permitida");
                }
                //propaguei o atual proibido
            }
        }

        //teste para nextNecessario:
        if (!semanticStack.isEmpty() && topoAnterior != -1) {
            if (tree.get(topoAnterior).getProximoObrigatorio() != null) {
                //se anterior tem um prox obrigatorio, propago para o filho
                tree.get(semanticStack.peek()).setAtualObrigatorio(tree.get(topoAnterior).getProximoObrigatorio());
                tree.get(topoAnterior).setProximoObrigatorio(null);
            }
            if (tree.get(topoAnterior).getAtualObrigatorio() != null) {
                //se anterior tem atual obrigatório, então propago para o filho
                tree.get(semanticStack.peek()).setAtualObrigatorio(tree.get(topoAnterior).getAtualObrigatorio());
                tree.get(topoAnterior).setAtualObrigatorio(null);
                if (!Tree.errosSemanticos.isEmpty()) {
                    String erro = Tree.errosSemanticos.get(0);
                    Tree.errosSemanticos.remove(0);
                    resultado.add(erro);//todo: refactor isso de errosSemanticos;
                }
            }
            if (tree.get(semanticStack.peek()).getClass() == InstrucaoPassos.class) {
                //se o atual é o não-terminal InstrucaoPassos, então defino o próximo obrigatório como AGUARDE ATE ROBO PRONTO
                tree.get(semanticStack.peek()).setProximoObrigatorio("AGUARDE ATE ROBO PRONTO");
            }
        }
            //testes de erro:
//            if (tree.get(semanticStack.peek()).getAtualObrigatorio()!= null) {
//
//                if (tree.get(semanticStack.peek()).getAtualObrigatorio().equals("AGUARDE ATE ROBO PRONTO") && !(tree.get(semanticStack.peek()).getVal() == null)) {
//                    //erro:
//                    System.out.println("Erro 1 de semantica");
//                }
//                else if (tree.get(semanticStack.peek()).getAtualObrigatorio().equals("ROBO PRONTO") && !(tree.get(semanticStack.peek()).getVal() == null)) {
//
//                }
//
//            }

            /*
            if (tree.get(semanticStack.peek()).getClass() == EstadoRobo.class && tree.get(topoAnterior).getClass() == Condicao.class && tree.get(semanticStack.peek()).getAtualObrigatorio() != null) {
                if (((EstadoRobo)tree.get(semanticStack.peek())).getValor().equals("PRONTO")) {
                    //tudo certo
                    tree.get(semanticStack.peek()).setAtualObrigatorio(null);
                }
                else {
                    System.out.println("ERRO!!!");
                }
            }
            //if (tree.get(semanticStack.peek()).getAtualObrigatorio() != null && tree.get(semanticStack.peek()).get)
            if (tree.get(semanticStack.peek()).getAtualObrigatorio() != null && tree.get(semanticStack.peek()).getAtualProibido() != null && tree.get(semanticStack.peek()).getClass() == Comando.class) {
                //erro!
                System.out.println("Erro.");
                tree.get(topoAnterior).setAtualObrigatorio(null);
            }



            if (tree.get(topoAnterior).getAtualObrigatorio() != null && tree.get(topoAnterior).getAtualObrigatorio().matches("AGUARDE.*")) {
                if (tree.get(semanticStack.peek()).)
            }

            if (tree.get(semanticStack.peek()).getVal() != null && tree.get(semanticStack.peek()).getAtualObrigatorio() != null) {
                if (tree.get(semanticStack.peek()).getVal().matches("AGUARDE.*")) {
                    if (tree.get(semanticStack.peek()).getAtualObrigatorio().matches("AGUARDE.*")) {
                        //tudo certo... continua
                    }
                    else {
                        System.out.println("ERRO semântico...");
                    }
                }
            }
            //se tou no caso de atualObrigatorio e
        }

*/
        //////////////////////////////////



        while(!semanticStack.isEmpty() && !tree.get(semanticStack.peek()).temCampoVazio()) {
            //propaga:
            No noQueAcabeiDeRemover = tree.get(semanticStack.pop());

            //==========================================================================================================//
            //                                           Geração de Código                                              //
            //==========================================================================================================//
            ArrayList<String> asm = new ArrayList<>();

           /* if (noQueAcabeiDeRemover.getClass() == Bloco.class) {
                asm = tree.get(((Bloco)noQueAcabeiDeRemover).getComandoBloco()).getAsm();
                noQueAcabeiDeRemover.setAsm(asm);
            }

            if (noQueAcabeiDeRemover.getClass() == Comando.class) {

            }*/

            if (noQueAcabeiDeRemover.getClass() == Programa.class) {

                try {
                    Path path = Paths.get("/home/joanderson/Coding/Compilador/src/robot.asm");
                    //String textoExemplo = "teste teste!!";
                    //byte[] linha;
                    //byte[] textoEmBytes = textoExemplo.getBytes();
                    //Files.write(path,noQueAcabeiDeRemover.getAsm().get(0).getBytes());
                    //String patha = "/home/joanderson/Coding/Compilador/src/outRobot.txt";
                    //FileWriter arq = new FileWriter(patha);
                    //PrintWriter gravarArq = new PrintWriter(arq);

                    noQueAcabeiDeRemover.updateAsm();


//                    String s = noQueAcabeiDeRemover.getAsm().get(0);
//                    Charset charset = Charset.forName("US-ASCII");
//
//                    BufferedWriter bufferedWriter = Files.newBufferedWriter(path,charset);
//                    bufferedWriter.write(s, 0, s.length());


                    Files.write(path,("").getBytes());
                    for (String s : noQueAcabeiDeRemover.getAsm()) {
                     //   System.out.println(s);
                        String[] split = s.split("\n");
                        for (String sp : split) {
                            Files.write(path,(sp + "\n").getBytes(), StandardOpenOption.APPEND);

                            //arq.write(sp);
                            //Files.
                     //       gravarArq.append(sp);
                        }

//                        linha = s.getBytes();
//                        Files.write(path,linha);
                        //Files.write(path,s.getBytes(), StandardOpenOption.APPEND);
                      //  gravarArq.write("teste teste");
                        //gravarArq.write(s);

                    }
                    //arq.close();
                    //gravarArq.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }


            //==========================================================================================================//

            //==========================================================================================================//

            //teste de atual obrigatório:
            if (noQueAcabeiDeRemover.getVal() == null && noQueAcabeiDeRemover.getAtualObrigatorio()!=null) {
                //se o nó que eu removi (que, portanto, era folha) ainda tinha uma obrigação com seu campo atualObrigatório, então é erro semântico
                //System.out.println("erro semantico 3.3");
                resultado.add("Erro semântico: instrução " + noQueAcabeiDeRemover.getAtualObrigatorio() + " era necessária");
            }
            /////


            if(!semanticStack.isEmpty())    {
                tree.get(semanticStack.peek()).setAtualProibido(null);
            }

            /*
            //então tenho q passar para o próx
            if (!semanticStack.isEmpty() && noQueAcabeiDeRemover.getNome() != null && tree.get(semanticStack.peek()).getNome() != null && tree.get(semanticStack.peek()).getNome().equals(noQueAcabeiDeRemover.getNome())) {
                //então, tem q ser diferente ao do filho, mas nesse caso está igual, então erro!
                System.out.println("Erro: duas declarações com mesmo nome: " + noQueAcabeiDeRemover.getNome());
            }
            else if (!semanticStack.isEmpty()){
                tree.get(semanticStack.peek()).setNome(noQueAcabeiDeRemover.getNome());
            }
            */


            //refatorando anterior:
//            if (!semanticStack.isEmpty()) {
//                if (noQueAcabeiDeRemover.getNome() != null) {
//                    if (tree.get(semanticStack.peek()).getNome() != null) {
//                        //têm de ser diferentes:
//                        if (noQueAcabeiDeRemover.getNome().equals(tree.get(semanticStack.peek()).getNome())) {
//                            //System.out.println("Erro semântico: duas declarações com mesmo nome: " + noQueAcabeiDeRemover.getNome());
//                            //resultado.add("Erro semântico: mútlipals instruções com mesmo nome: " + noQueAcabeiDeRemover.getNome());
//                        }
//                    }
//                    else if (semanticStack.peek() != 0){//não propaga para a raiz da árvore
//                        tree.get(semanticStack.peek()).setNome(noQueAcabeiDeRemover.getNome());
//                    }
//                }
//            }

            if (noQueAcabeiDeRemover.getClass() == Identificador.class) {
                if (tree.get(semanticStack.peek()).getClass() == Instrucao.class) {
                    //testa se não existe tal id na tabela de símbolos
                    if (!MainCompilador.findIdentificadorNaTabelaDeSimbolos(noQueAcabeiDeRemover.getNome())) {
                        //erro:
                        //System.out.println("erro -> de id não encontrado");
                        resultado.add("Erro semântico: procedimento '" + noQueAcabeiDeRemover.getNome() + "' não declarado.");
                    }
                }

                /*a ser excluído:
                * String tokenString = "<identificador, " + lexema.toString() + ">";
                            if (tokenString.matches("<identificador, >")) {
                                continue;//token vazio (apenas com quebras) não inserido
                            }
                            Token token = new Token(lexema.toString(), "ID", linha, coluna);
                            tokenList.add(token);

                            // Verifica se já existe na tabela de símbolos e insere caso contrário
                            if (!tabelaSimbolos.containsKey(tokenString)) {
                                TabelaSimbolosEntry newEntry = new TabelaSimbolosEntry(lexema.toString(), tokenString);
                                tabelaSimbolos.put(tokenString, newEntry);
                            }
                 */
                if (tree.get(semanticStack.peek()).getClass() == Declaracao.class) {
                    //então salvar na tabela de símbolos:
                    String tokenString = "<identificador, " + noQueAcabeiDeRemover.getNome() + ">";
                    if (MainCompilador.findIdentificadorNaTabelaDeSimbolos(noQueAcabeiDeRemover.getNome())) {
                        //erro
                        resultado.add("Erro semântico: múltiplas instruções com mesmo nome: " + noQueAcabeiDeRemover.getNome());
                    }
                    else {
                        //adiciono na tabela de símbolos
                        TabelaSimbolosEntry newEntry = new TabelaSimbolosEntry(noQueAcabeiDeRemover.getNome(),tokenString);
                        MainCompilador.addTabelaSimbolos(tokenString,newEntry);
                    }
                }
            }



            //teste de próximo proibido:

            if (!semanticStack.isEmpty() && noQueAcabeiDeRemover.getClass() == Sentido.class) {
                tree.get(semanticStack.peek()).setVal(noQueAcabeiDeRemover.getVal());
                //sei que é uma instrução do tipo "vire para Sentido"
                if (((Sentido)noQueAcabeiDeRemover).getValue() != null && ((Sentido)noQueAcabeiDeRemover).getValue().equals("ESQUERDA")) {
                    tree.get(semanticStack.peek()).setProximoProibido("VIRE PARA DIREITA");
                }
                else {
                    tree.get(semanticStack.peek()).setProximoProibido("VIRE PARA ESQUERDA");
                }
            }
            if (!semanticStack.isEmpty() && noQueAcabeiDeRemover.getProximoProibido() != null) {

                tree.get(semanticStack.peek()).setProximoProibido(noQueAcabeiDeRemover.getProximoProibido());
            }



            //para teste de nextObrigatório:
            if (noQueAcabeiDeRemover.getProximoObrigatorio() != null) {
                //propago o prox obrigatorio para os pais
            }
            if (!semanticStack.isEmpty()) {
                tree.get(semanticStack.peek()).setProximoObrigatorio(noQueAcabeiDeRemover.getProximoObrigatorio());
            }
            if (noQueAcabeiDeRemover.getAtualObrigatorio() == null) {
                if (!semanticStack.isEmpty()) {
                    tree.get(semanticStack.peek()).setAtualObrigatorio(null);
                }
            }
        }

        return resultado;
    }
    public ArrayList<String> addIdentificador(String id) {
        String regra = id;
        String nonTerminal = "IDENTIFICADOR";
        tree.add(new Identificador(regra));
        int posicaoOndeFoiAlocado = tree.size()-1;
        tree.get(nextNoVazio()).addNoEmProximaPosicaoVazia(posicaoOndeFoiAlocado,nonTerminal);
        return gerenciarStackSemantico();
    }
    public ArrayList<String> addNumero(String num) {
        String regra = num;
        String nonTerminal = "NUMERO";
        tree.add(new Numero(regra));
        int posicaoOndeFoiAlocado = tree.size()-1;
        tree.get(nextNoVazio()).addNoEmProximaPosicaoVazia(posicaoOndeFoiAlocado,nonTerminal);
        return gerenciarStackSemantico();
    }

}
