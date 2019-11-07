package NonTerminals;

import java.util.ArrayList;
import java.util.Map;

public class Arvore {

    //private No raiz;
    private int proximoNoComCampoVazio = -1;
    private ArrayList<No> tree = new ArrayList<>();
    //pressupõe-se que sintática está correta

    public void addNo(String classe) {

        if (classe.equals("PROGRAMA")) {
            //árvore vazia, inicia
            tree.add(new Programa());
            proximoNoComCampoVazio = 0;
        }
        boolean result = tree.get(proximoNoComCampoVazio).addNo(tree.size(),classe);
        if (!result) {
            //não adicionou pq result é lambda.
            while (!tree.get(proximoNoComCampoVazio).temCampoVazio()){
                proximoNoComCampoVazio++;
            }
        }
        if(!tree.get(proximoNoComCampoVazio).temCampoVazio()) {
            proximoNoComCampoVazio++; //isso está certo?
        }
        if(classe.equals("COMANDOMAIS")) {
            tree.add(new ComandoMais());
        }
        if(classe.equals("DECLARACAO")) {
            tree.add(new Declaracao());
        }
        if(classe.equals("BLOCO")) {
            tree.add(new Bloco());
        }
        if(classe.equals("COMANDOBLOCO")) {
            tree.add(new ComandoBloco());
        }
        if(classe.equals("COMANDO")) {
            tree.add(new Comando());
        }
        if(classe.equals("ITERACAO")) {
            tree.add(new Iteracao());
        }
        if(classe.equals("LACO")) {
            tree.add(new Laco());
        }
        if(classe.equals("CONDICIONAL")) {
            tree.add(new Condicional());
        }
        if(classe.equals("CONDICIONALSENAO")) {
            tree.add(new CondicionalSenao());
        }
        if(classe.equals("NUMINSTRUCAO")) {
            tree.add(new NumInstrucao());
        }
        if(classe.equals("INSTRUCAO")) {
            tree.add(new Instrucao());
        }
        if(classe.equals("INSTRUCAOPASSOS")) {
            tree.add(new InstrucaoPassos());
        }
        if(classe.equals("CONDICAO")) {
            tree.add(new Condicao());
        }
        if(classe.equals("ESTADOLAMPADA")) {
            tree.add(new EstadoLampada());
        }
        if(classe.equals("ESTADOROBO")) {
            tree.add(new EstadoRobo());
        }
        if(classe.equals("LOCALLAMPADA")) {
            tree.add(new LocalLampada());
        }
        if(classe.equals("IDENTIFICADOR")) {
            tree.add(new Identificador());
        }
        if(classe.equals("NUMERO")) {
            tree.add(new Numero());
        }

        //todo: implementar addNo() e temCampoVazio() em todas as classes filhas de No
    }
/*
    public Elemento proxNo(No pai) {

        ArrayList<No> tree = new ArrayList<>();




























































        if (pai == null) return null;
        if (pai.getClass() == Programa.class) {
            //testa os filhos na ordem
           Elemento retorno = proxNo(((Programa)pai).getDeclaracao());
            if (retorno != null) {
                retorno.posicaoProxVazio = 0;
                return retorno;
            }
            retorno = proxNo(((Programa)pai).getComando());
            if (retorno != null) {
                retorno.posicaoProxVazio = 1;
                return retorno;
            }
            retorno = proxNo(((Programa)pai).getComandoMais());
            if (retorno != null) {
                retorno.posicaoProxVazio = 2;
                return retorno;
            }
            return new Elemento(pai,0,"Programa");
        }
        if (pai.getClass() == ComandoMais.class) {
            //testa os filhos na ordem
            if (((ComandoMais)pai).isLambda()) {
                return null;
            }
            Elemento retorno = proxNo(((ComandoMais)pai).getComando());
            if (retorno != null) {
                retorno.posicaoProxVazio = 0;
                return retorno;
            }
            retorno = proxNo(((ComandoMais)pai).getComandoMais());
            if (retorno != null) {
                retorno.posicaoProxVazio = 1;
                return retorno;
            }
            return new Elemento(pai,0,"ComandoMais");
        }


/*


        No aux = pai;
        int i=0;
        while(true) {
            while ( aux.hasIesimoChild(i)) {
                aux = aux.iesimoChild(i);

            }
            if (!aux.hasEmptyChildLocation()) {
                continue;
            }

        }

        i++;
*/
        //return null;
  //  }


/*
    private No getProxPai(No noAtual) {

        if (noAtual.getClass() == Programa.class) {
            if (((Programa)noAtual).isVazia()){
                return noAtual;//noAtual é pai doq vai ser add
            }
            else{
                No retorno = getProxPai(((Programa)noAtual).getDeclaracao());
                if (retorno == null) {
                    retorno = getProxPai(((Programa)noAtual).getComando());
                }
                if (retorno == null) {
                    retorno = getProxPai(((Programa)noAtual).getComandoMais());
                }
                if (retorno == null) {
                    return null;
                }
                return retorno;
            }
        }
    }

    private No proxVazio (No atual) {
        if (atual.primeiroAtributo())
    }
    public void inserirNo(String naoTerminal) {
        if (naoTerminal.equals("PROGRAMA")) {
            raiz = new Programa();
        }
        if (naoTerminal.equals("COMANDOMAIS")) {
            nodePaiDoASerColocado = getProxPai();
        }
        if (naoTerminal.equals("PROGRAMA")) {

        }
        if (naoTerminal.equals("PROGRAMA")) {

        }
        if (naoTerminal.equals("PROGRAMA")) {

        }
        if (naoTerminal.equals("PROGRAMA")) {

        }

    }







    /*
    private static ArrayList<No> arvore;

    public Arvore() {
        arvore = new ArrayList<>();
    }
    private void setProxVazio(Integer posicao) {
        for ( No no : arvore) {
            if (no.)
        }
    }
    private int nextVazio(int atual) {
        No noAtual = arvore.get(atual);
        if (!arvore.get(atual).isCompleta()) {
            //vai para o mais a esquerda possível, se tiver vazio, é esse, senão, recursivo

        }
    }
    public void addNo(String no) {
        //procura próxima lacuna vazia, de cima para baixo, da esq para direita, na árvore
        if (no.equals("PROGRAMA")) {
            arvore.add(new Programa());
            return;
        }
        if (no.equals("COMANDOMAIS")) {
            arvore.add(new ComandoMais());
            setProxVazio(arvore.size()-1);
        }
    }

    /*
    Programa raiz;

    private Object proxLacunaVazia(No noAtual){
        //encontra próxima lacuna vazia, esq para direita, cima para baixo
        if (raiz == null) {
            return raiz;
        }
        while (noFuturo!=null) {
            noAtual = noFuturo;
            noFuturo =
        }
    }

    public void addNo(String naoTerminal) {
        if (naoTerminal.equals("PROGRAMA")) {
            //árvore está vazia
            raiz = new Programa();
            return;
        }
        if (naoTerminal.equals("COMANDOMAIS")) {

        }
        if (naoTerminal.equals("COMANDOMAIS")) {

        }
        if (naoTerminal.equals("COMANDOMAIS")) {

        }
        if (naoTerminal.equals("COMANDOMAIS")) {

        }
        if (naoTerminal.equals("COMANDOMAIS")) {

        }

    }

    public Programa getRaiz() {
        return raiz;
    }

    public void setRaiz(Programa raiz) {
        this.raiz = raiz;
    }

     */
}
