import java.util.ArrayList;

public class Arvore {

    //private No raiz;
    private int proximoNoComCampoVazio = -1;
    private ArrayList<No> tree = new ArrayList<>();
    //pressupõe-se que sintática está correta

    public void addNo(String classe) {
/*
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
    */
    }

}
