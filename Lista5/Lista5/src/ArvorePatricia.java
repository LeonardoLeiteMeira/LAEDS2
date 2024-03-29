/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo Leite e Savio Cabral
 */
public class ArvorePatricia{
    //  patNo  nos  slides
    private  static  abstract  class  NoArvorePatricia {}
    // PatNoInt  nos  slides
    private  static  class  NoInternoArvorePatricia  extends  NoArvorePatricia {
        //  indice  que  remete a posicao  do bit  relativo a um  conjunto  de  letras em um no  interno
        // index  nos  slides
        int  indice;
        // Variaveis  que  indicam  quem  sao os nos  filhos  de um no  interno.
        NoArvorePatricia  filhoEsquerda , filhoDireita;
    }// PatNoExt  nos  slides
    private  static  class  NoExternoArvorePatricia  extends  NoArvorePatricia {
        // Atributos  de um no  externo  da  arvore:
        //um  caractere  que  aparece  no  minimo  uma  vez
        char  chave;
        //  quantidade  de vezes  que a letra  aparece
        int  quantidade;
    }

    private NoArvorePatricia raiz;
    private final int numeroDeBitsNaChave;
    private int nosVisitadosPesquisa;
    private int caracteresDistintos;

    public ArvorePatricia(int nbitsChave){
        this.raiz = null;
        this.numeroDeBitsNaChave = nbitsChave;
    }

    private int pesquisa(char chavePesquisa, NoArvorePatricia raizAtual){
        this.nosVisitadosPesquisa++;
        if(this.verificaNoExterno(raizAtual)){
            NoExternoArvorePatricia noAux = (NoExternoArvorePatricia) raizAtual;
            if(chavePesquisa == noAux.chave)
                return noAux.quantidade;
            return 0;
        } else {
            NoInternoArvorePatricia noAux = (NoInternoArvorePatricia) raizAtual;
            if(this.testaBit(noAux.indice, chavePesquisa) == 0)
                 return this.pesquisa(chavePesquisa, noAux.filhoEsquerda);
            else 
                 return this.pesquisa(chavePesquisa, noAux.filhoDireita);
        }  
        
    }

    public int pesquisa(char chavePesquisa){
        return this.pesquisa(chavePesquisa, this.raiz);
    }

    private int testaBit(int posicaoAtual, char chaveInsercao){
        int chaveInt = (int) chaveInsercao;
        for(int i = 0; i < this.numeroDeBitsNaChave - posicaoAtual; i++)
            chaveInt = chaveInt/2;
        return chaveInt % 2;
    }

    private boolean verificaNoExterno(NoArvorePatricia noAtual){
        return noAtual instanceof NoExternoArvorePatricia;
    }

    private NoArvorePatricia criaNoInterno(int posicaoAtual, NoArvorePatricia filhoEsq,NoArvorePatricia filhoDir){
        NoInternoArvorePatricia no = new NoInternoArvorePatricia();
        no.indice = posicaoAtual;
        no.filhoDireita = filhoDir;
        no.filhoEsquerda = filhoEsq;
        return no;
    }

    private NoArvorePatricia criaNoExterno(char chaveInsercao){
        this.caracteresDistintos++;
        NoExternoArvorePatricia no = new NoExternoArvorePatricia();
        no.chave = chaveInsercao;
        no.quantidade = 1;
        return no;
    }

    private NoArvorePatricia insereEntre(char chaveInsercao, NoArvorePatricia raizAtual,int posicaoAtual){
        NoInternoArvorePatricia aux = null;
        if(!this.verificaNoExterno(raizAtual))
            aux = (NoInternoArvorePatricia)raizAtual;
        if(this.verificaNoExterno(raizAtual) || posicaoAtual < aux.indice){
            NoArvorePatricia no = this.criaNoExterno(chaveInsercao);
            if(this.testaBit(posicaoAtual, chaveInsercao) == 1)
                return this.criaNoInterno(posicaoAtual, raizAtual, no);
            else
                return this.criaNoInterno(posicaoAtual, no, raizAtual);
        } else {
            if(this.testaBit(aux.indice, chaveInsercao) == 1)
                aux.filhoDireita = this.insereEntre(chaveInsercao, aux.filhoDireita, posicaoAtual);
            else
                aux.filhoEsquerda = this.insereEntre(chaveInsercao, aux.filhoEsquerda, posicaoAtual);
            return aux;
        }
    }

    private NoArvorePatricia insere(char chaveInsercao, NoArvorePatricia raizAtual){
        if(raizAtual == null)
            return this.criaNoExterno(chaveInsercao);
         else {
            NoArvorePatricia no = raizAtual;
            while(!this.verificaNoExterno(no)){
                NoInternoArvorePatricia aux = (NoInternoArvorePatricia) no;
                if(this.testaBit(aux.indice, chaveInsercao) == 1)
                    no = aux.filhoDireita;
                else
                    no = aux.filhoEsquerda;
            }  
            NoExternoArvorePatricia aux = (NoExternoArvorePatricia) no;
            int i = 1;
            while((i <= this.numeroDeBitsNaChave) && (this.testaBit(i , chaveInsercao) == this.testaBit(i, aux.chave)))
                i++;
            if(i > this.numeroDeBitsNaChave){
                aux.quantidade++;
                return raizAtual;
            } else 
                return this.insereEntre(chaveInsercao, raizAtual, i);
        }
    }

    public void insere(char chaveInsercao){
        this.raiz = this.insere(chaveInsercao, this.raiz);
    }

    public int getNosVisitadosPesquisa(){
        return nosVisitadosPesquisa;
    }

    public int getCaracteresDistintos(){
        return caracteresDistintos;
    }

}