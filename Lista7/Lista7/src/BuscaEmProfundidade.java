/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo Leite e Savio Cabral
 */
public class BuscaEmProfundidade extends AlgoritmosEmGrafos {
    protected int[] distanciaProfundidade; //guarda a distancia, em vertices, entre o no inicial da busca em profundidade e cada um dos outros vertices do grafo
    protected int[] verticePredecessor; //armazena o vertice predecessor de cada no descoberto durante a busca em produndidade
    
    
    public BuscaEmProfundidade(Grafo grafo) { // metodo construtor, define os valores iniciais dos vetores
        this.distanciaProfundidade = new int[grafo.getNumeroVertices()];
        this.verticePredecessor = new int[grafo.getNumeroVertices()];
        this.grafo = grafo;
    }

    public void iniciaBuscaEmProfundidade(int vertice){ // inicia o algoritmo de busca em profundidade
        this.inicializaVetores(this.grafo.getNumeroVertices(), vertice);
        this.inicial = vertice;
        int peso = 1;
        this.buscaProfundidade(vertice, peso);
    }

    private void buscaProfundidade(int vertice, int peso){ // implementa a busca em profundidade
        for(Integer verticeAdj : this.grafo.listaDeAdjacencia(vertice)){
            if(this.verticePredecessor[verticeAdj] < 0 && verticeAdj != inicial){
                this.verticePredecessor[verticeAdj] = vertice;
                this.distanciaProfundidade[verticeAdj] = peso;
                this.buscaProfundidade(verticeAdj, peso + 1);
            }
        }
    }

    public int[] getDistanciaProfundidade(){
        return distanciaProfundidade;
    }

    public int[] getVerticePai(){
        return verticePredecessor;
    }

    private void inicializaVetores(int numeroVertices, int vertice){ // define os valores inciais dos vetores
        for(int i = 0; i < numeroVertices; i ++){
            this.distanciaProfundidade[i] = numeroVertices + 1;
            this.verticePredecessor[i] = -1;
            if(i == vertice)
                this.distanciaProfundidade[i] = 0;
        }
    }
}
