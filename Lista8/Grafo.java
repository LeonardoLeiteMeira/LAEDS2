
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leonardo Leite e Savio Cabral
 */
public class Grafo {

    private final int numeroVertices;
    private final int[][] matrizAdjacencia;

    public Grafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        int[][] matrizAdjacenciaAux = new int[numeroVertices][numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                matrizAdjacenciaAux[i][j] = -1;
            }
        }

        matrizAdjacencia = matrizAdjacenciaAux;
    }

    public void insereAresta(int vertice1, int vertice2, int peso) {//insere uma aresta no grafo.
        matrizAdjacencia[vertice1][vertice2] = peso;
    }

    public void insereArestaNaoOrientada(int vertice1, int vertice2, int peso){
        matrizAdjacencia[vertice1][vertice2] = peso;
        matrizAdjacencia[vertice2][vertice1] = peso;
    }

    public boolean existeAresta(int vertice1, int vertice2) { //verifica se uma aresta existe no grafo.
        return matrizAdjacencia[vertice1][vertice2] > 0;
    }

    public ArrayList<Integer> listaDeAdjacencia(int vertice1) {//retorna a lista de vertices adjacentes a um determinado vertice v. Ou seja, todos os vertices u cujos quais existe uma aresta que sai de v e chega em u.
        ArrayList<Integer> listaAdjacencia = new ArrayList<>();

        for (int i = 0; i < numeroVertices; i++) {
            if (matrizAdjacencia[vertice1][i] != -1) {
                listaAdjacencia.add(i);
            }
        }

        return listaAdjacencia;
    }

    public int getPeso(int vertice1, int vertice2) { //devolve o peso de uma aresta.
        return matrizAdjacencia[vertice1][vertice2];
    }

    public int getNumeroVertices() { //devolve o numero de vertices
        return numeroVertices;
    }
    
    public void exibeMatriz(){
        for(int i=0; i<numeroVertices; i++){
            for(int j=0; j<numeroVertices; j++)
                System.out.print(matrizAdjacencia[i][j]+" ");
            System.out.println();
        }    
    }
    
    public int[][] getMatrizAdj(){
        return matrizAdjacencia;
    }
}
