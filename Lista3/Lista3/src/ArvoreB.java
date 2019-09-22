/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class ArvoreB {

    /*
    * QUESTÃO 4
    *
    *ATRIBUTOS DA CLASSE ARVORE B
    *
     */
    private Pagina pagRaiz;
    private final int minimoRegistrosPagina, maximoRegistrosPagina;
    private boolean arvoreDesbalanceada;
    private Item regRetorno;
    private int paginasVisitadas;
    private int numeroComparacoes;
    private int maximoRegistros;
    private int minimoRegistros;

    /*
    * QUESTÃO 5
    *
    *CONSTRUTOR DA CLASSE ARVORE
    *
     */
    public ArvoreB(int qtdeminimaRegistros) {
        this.pagRaiz = null;
        this.minimoRegistrosPagina = qtdeminimaRegistros;
        this.maximoRegistrosPagina = 2 * qtdeminimaRegistros;
        arvoreDesbalanceada = false;
        regRetorno = new Item(-1);
        paginasVisitadas = 0;
        numeroComparacoes = 0;
    }

    /*
    * QUESTÃO 5
    *
    *METODO DE INSERÇÃO
    *
     */
    private Pagina insere(Item registro, Pagina paginaAtual) {
        Pagina paginaRetorno = null;
        if (paginaAtual == null) {
            arvoreDesbalanceada = true;
            regRetorno = registro;
        } else {
            int i = 0;
            while ((i < paginaAtual.numeroCorrenteItens - 1) && ((registro.getChave() - paginaAtual.itensPagina[i].getChave()) > 0)) {
                i++;
            }
            if (registro.getChave() == paginaAtual.itensPagina[i].getChave()) {
                System.out.println(" Erro : Registro ja existente ");
                arvoreDesbalanceada = false;
            } else {
                if (registro.getChave() - paginaAtual.itensPagina[i].getChave() > 0) {
                    i++;
                }
                paginaRetorno = insere(registro, paginaAtual.paginasFilhas[i]);
                if (arvoreDesbalanceada) {
                    if (paginaAtual.numeroCorrenteItens < this.maximoRegistrosPagina) {
                        this.insereNaPagina(paginaAtual, regRetorno, paginaRetorno);
                        arvoreDesbalanceada = false;
                        paginaRetorno = paginaAtual;
                    } else {
                        Pagina apTemp = new Pagina(this.maximoRegistrosPagina);
                        apTemp.paginasFilhas[0] = null;
                        if (i <= this.minimoRegistros) {
                            this.insereNaPagina(apTemp, paginaAtual.itensPagina[this.maximoRegistros - 1], paginaAtual.paginasFilhas[this.maximoRegistros]);
                            paginaAtual.numeroCorrenteItens--;
                            this.insereNaPagina(paginaAtual, regRetorno, paginaRetorno);
                        } else {
                            this.insereNaPagina(apTemp, regRetorno, paginaRetorno);
                        }
                        for (int j = this.minimoRegistrosPagina + 1; j < this.maximoRegistrosPagina; j++) {
                            this.insereNaPagina(apTemp, paginaAtual.itensPagina[j], paginaAtual.paginasFilhas[j + 1]);
                            paginaAtual.paginasFilhas[j + 1] = null;
                        }
                        paginaAtual.numeroCorrenteItens = this.minimoRegistrosPagina;
                        apTemp.paginasFilhas[0] = paginaAtual.paginasFilhas[this.minimoRegistros + 1];
                        regRetorno = paginaAtual.itensPagina[this.minimoRegistros];
                        paginaRetorno = apTemp;
                    }
                }
            }
        }
        return (arvoreDesbalanceada ? paginaRetorno : paginaAtual);
    }

    /*
    * QUESTÃO 3
    *
    *METODO INSERE NA PÁGINA 
    *
     */
    private void insereNaPagina(Pagina paginaAux, Item reg, Pagina paginaDireita) {
        int aux = paginaAux.numeroCorrenteItens - 1;
        while ((aux >= 0) && (reg.getChave() < paginaAux.itensPagina[aux].getChave()) ) {
            paginaAux.itensPagina[aux + 1] = paginaAux.itensPagina[aux];
            paginaAux.paginasFilhas[aux + 2] = paginaAux.paginasFilhas[aux + 1];
            aux--;
        }
        paginaAux.itensPagina[aux + 1] = reg;
        paginaAux.paginasFilhas[aux + 2] = paginaDireita;
        paginaAux.numeroCorrenteItens++;
    }

    /*
    * QUESTÃO 2
    *
    *CLASSE PÁGINA
    *
     */
    private static class Pagina {

        // Numero de itens que a pagina contem atualmente
        int numeroCorrenteItens;

        // Vetor que armazena todos os itens da pagina
        Item itensPagina[];

        // Vetor que indica quem sao as paginas filhas ( ele aponta para todas as filhas )
        Pagina paginasFilhas[];

        // Metodo construtor da classe pagina
        public Pagina(int qtdemaximaRegistros) {
            this.numeroCorrenteItens = 0;
            this.itensPagina = new Item[qtdemaximaRegistros];
            this.paginasFilhas = new Pagina[qtdemaximaRegistros + 1];
        }
    }

    /*
    * QUESTÃO 6
    *
    *Implementação dos metodos
    *
     */
    private Item pesquisa(Item itemAux, Pagina ap) {
        if (ap == null){
            return null;
        }else {
            paginasVisitadas++;
            int i = 0;
            while ((i < ap.numeroCorrenteItens - 1) && (itemAux.compara(ap.itensPagina[i]) == 1)){
                i++;
                numeroComparacoes++;
            }
            if (itemAux.compara(ap.itensPagina[i]) == 0){ 
                numeroComparacoes++;
                return ap.itensPagina[i];
            }
            else if (itemAux.compara(ap.itensPagina[i]) < 0){
                numeroComparacoes+=2;
                return pesquisa(itemAux, ap.paginasFilhas[i]);
            }
            else{
                numeroComparacoes+=2;
                return pesquisa(itemAux, ap.paginasFilhas[i + 1]);
            }
        }
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.pagRaiz);
    }
     
    public void insere(Item reg) {
        Pagina apRetorno = insere(reg, this.pagRaiz);
        if (arvoreDesbalanceada) {
            Pagina apTemp = new Pagina(this.maximoRegistrosPagina);
            apTemp.itensPagina[0] = regRetorno;
            apTemp.paginasFilhas[0] = this.pagRaiz;
            apTemp.paginasFilhas[1] = apRetorno;
            this.pagRaiz = apTemp;
            this.pagRaiz.numeroCorrenteItens++;
        } else {
            this.pagRaiz = apRetorno;
        }
    }
 
    public int getPaginasVisitadas(){
        return paginasVisitadas;
    }
    
    public int getNumeroComparacoes(){
        return numeroComparacoes;
    }
}


