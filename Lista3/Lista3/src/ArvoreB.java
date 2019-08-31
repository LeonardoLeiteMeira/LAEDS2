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
    
    private Pagina pagRaiz;
    private int minimoRegistrosPagina;
    private int maximoRegistrosPagina;
    private boolean arvoreDesbalanceada;
    private int regRetorno;
    private int paginasVisitadas;
    private int numeroComparacoes;
    
    public ArvoreB(int qtdeminimaRegistros) {
        this.pagRaiz = null;
        this.minimoRegistrosPagina = qtdeminimaRegistros;
        this.maximoRegistrosPagina = 2 * qtdeminimaRegistros;
        arvoreDesbalanceada = false;
        regRetorno = -1;
        paginasVisitadas = 0;
        numeroComparacoes = 0;
    }
    private void insereNaPagina(Pagina paginaAux, int reg, Pagina paginaDireita) {
        int aux = paginaAux.numeroCorrenteItens - 1;
        while ((aux >= 0) && (reg < paginaAux.itensPagina[aux])){
            paginaAux.itensPagina[aux + 1] = paginaAux.itensPagina[aux];
            paginaAux.paginasFilhas[aux + 2] = paginaAux.paginasFilhas[aux + 1];
            aux--;
        }
        paginaAux.itensPagina[aux + 1] = reg;
        paginaAux.paginasFilhas[aux + 2] = paginaDireita;
        paginaAux.numeroCorrenteItens++;
    }
    
    private Pagina insere(int registro, Pagina paginaAtual) {
        Pagina paginaRetorno = null;
        if (paginaAtual == null) {
            arvoreDesbalanceada = true;
            regRetorno = registro;
        } else {
            int i = 0;
            while ((i < paginaAtual.numeroCorrenteItens - 1) && (registro
                    - paginaAtual.itensPagina[i] > 0)) {
                i++;
            }
            if (registro == paginaAtual.itensPagina[i]) {
                System.out.println(" Erro : Registro ja existente ");
                arvoreDesbalanceada = false;
            } else {
                if (registro - paginaAtual.itensPagina[i] > 0) {
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
                            this.insereNaPagina(paginaAtual, regRetorno,
                                    paginaRetorno);
                        } else {
                            this.insereNaPagina(apTemp, regRetorno, paginaRetorno);
                        }
                        for (int j = this.minimoRegistrosPagina + 1; j < this.maximoRegistrosPagina; j++) {
                            this.insereNaPagina(apTemp, paginaAtual.itensPagina[j],
                                    paginaAtual.paginasFilhas[j + 1]);
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

    
    
    private static class Pagina {
        
        // Numero de itens que a pagina contem atualmente
        int numeroCorrenteItens;
        
        // Vetor que armazena todos os itens da pagina
        int itensPagina[];
        
        // Vetor que indica quem sao as paginas filhas ( ele aponta para todas as filhas )
        Pagina paginasFilhas[];
        
        // Metodo construtor da classe pagina
        public Pagina(int qtdemaximaRegistros) {
            this.numeroCorrenteItens = 0;
            this.itensPagina = new int[qtdemaximaRegistros];
            this.paginasFilhas = new Pagina[qtdemaximaRegistros + 1];
        }
        
        private void insereNaPagina(Pagina paginaAtual, int registro, Pagina filhaDireita) {
            
            int k = paginaAtual.numeroCorrenteItens - 1;
            while (k >= 0 && (registro - paginaAtual.itensPagina[k]) < 0) {
                paginaAtual.itensPagina[k + 1] = paginaAtual.itensPagina[k];
                paginaAtual.paginasFilhas[k + 2] = paginaAtual.paginasFilhas[k + 1];
                k--;
            }
            paginaAtual.itensPagina 
            [k + 1] = registro ;
            paginaAtual.paginasFilhas 
            [k + 2] = filhaDireita ;
            paginaAtual.numeroCorrenteItens ++;
        }
    }
}
