/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class Teste {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(6);
        
        grafo.insereAresta(0, 1, 2);
        grafo.insereAresta(0, 2, 4);
        grafo.insereAresta(1, 2, 1);
        grafo.insereAresta(1, 3, 1);
        grafo.insereAresta(1, 4, 4);
        grafo.insereAresta(2, 4, 4);
        grafo.insereAresta(2, 5, 5);
        grafo.insereAresta(4, 5, 2);
        grafo.insereAresta(3, 4, 1);
        grafo.insereAresta(5, 3, 2);
        
        //grafo.exibeMatriz();
        
        AlgoritmoDijkstra teste = new AlgoritmoDijkstra(6, grafo);
        teste.iniciaCaminhoMaisCurto(0);
        
        for(int i=0; i<grafo.getNumeroVertices(); i++){
            System.out.println("Vertice antecessor a "+i+": "+teste.getVerticeAntecessorCMC()[i]);
            System.out.println("Distancia do vertice "+i+": "+teste.getdistanciasCMC()[i]);
            System.out.println("");
        }
    }
}
