package NonTerminals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Tree {

    private ArrayList<No> tree;
    private Stack<Integer> semanticStack;
    private ArrayList<Integer> stackSemantico;

    public Tree() {
        tree = new ArrayList<>();
        semanticStack = new Stack<>();
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



        while(!semanticStack.isEmpty() && !tree.get(semanticStack.peek()).temCampoVazio()) {
            //propaga:
            No noQueAcabeiDeRemover = tree.get(semanticStack.pop());
            if(!semanticStack.isEmpty())    tree.get(semanticStack.peek()).setAtualProibido(null);
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
            if (!semanticStack.isEmpty()) {
                if (noQueAcabeiDeRemover.getNome() != null) {
                    if (tree.get(semanticStack.peek()).getNome() != null) {
                        //têm de ser diferentes:
                        if (noQueAcabeiDeRemover.getNome().equals(tree.get(semanticStack.peek()).getNome())) {
                            //System.out.println("Erro semântico: duas declarações com mesmo nome: " + noQueAcabeiDeRemover.getNome());
                            resultado.add("Erro semântico: duas intruções com mesmo nome: " + noQueAcabeiDeRemover.getNome());
                        }
                    }
                    else if (semanticStack.peek() != 0){//não propaga para raiz
                        tree.get(semanticStack.peek()).setNome(noQueAcabeiDeRemover.getNome());
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
                if (tree.get(semanticStack.peek()) != null) {
                    //erro!
                    //System.out.println("Isto é um erro? ou depende do proxProibido ser diferente?");
                }
                tree.get(semanticStack.peek()).setProximoProibido(noQueAcabeiDeRemover.getProximoProibido());
            }


/*
            if (!semanticStack.isEmpty() && noQueAcabeiDeRemover.getProximoProibido() != null) {
                //propago pro pai:
                if (tree.get(semanticStack.peek()).getProximoProibido() == null) {
                    tree.get(semanticStack.peek()).setProximoProibido(noQueAcabeiDeRemover.getProximoProibido());
                }

                if (!noQueAcabeiDeRemover.getProximoProibido().equals(tree.get(semanticStack.peek()).getProximoProibido())) {
                    //ERRO!!
                    System.out.println("Erro: instrução proibida");
                }
            }
*/
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