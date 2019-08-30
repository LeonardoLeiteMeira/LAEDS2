/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista1;

/**
 *
 * @author Leonardo
 */
public class ArvoreBinaria {
    
    private No raiz;
    private long comparacoes;
    private long tempoBusca;
    private int numElementos;
    
    private static class No {
        Item reg;
        No esq , dir ;
    }
    
    public ArvoreBinaria(){//Instancia a arvore binaria vazia
        this.raiz = null;
        comparacoes = 0;
        tempoBusca = 0;
        numElementos = 0;
    }
    
    private No insere(Item reg, No p){//insere um novo item na arvore quando encontra um no vazio 
        if(p==null){
            p=new No(); p.reg=reg;
            p.esq=null; p.dir=null;
            numElementos++;
        }
        else if(reg.compara(p.reg)<0){
            p.esq = insere (reg, p.esq);
        }
        else if(reg.compara (p.reg)>0){
            p.dir = insere (reg, p.dir);
        }
        else System.out.println("Erro: Registro já existente");
        return p;
    }
    
    private Item pesquisa(Item reg, No p){//pesquisa o item passado como parametro no no
        comparacoes++;
        if(p==null)return null;//Registro não encontrado
        else if(reg.compara(p.reg)<0)
            return pesquisa(reg,p.esq);
        else if(reg.compara(p.reg)>0)
            return pesquisa(reg, p.dir);
        else return p.reg;
    }
    
    public void insere(Item reg){//primeira chamada para a inserção
        this.raiz = this.insere(reg, this.raiz);
    }
    
    public void pesquisa(Item reg){//primeira chamada para a pesquisa
        comparacoes = 0;
        Item itemAux = this.pesquisa(reg, this.raiz);
        if(itemAux==null){
            //System.out.println("Item não encontrado");
        }else
            System.out.println("Item encontrado: "+itemAux.getChave());
    }
    
    public long getComparacoes(){ //retorna o numero de comparação da ultima pesquisa 
        return comparacoes;
    }
    
    public void SetTempoBusca(long tempo){
        tempoBusca = tempo;
    }
    
    public long getTempoBusca(){
        return tempoBusca;
    }
    
    public int getNumElementos(){
        return numElementos;
    }
}
