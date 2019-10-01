/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo Leite e Savio Cabral
 */

import java.io.*;
public class Teste {
    public static void main(String[] args) throws IOException{
        //GRAFO 1
        Grafo grafo = lerGrafo(args[0]);
        
        AlgoritmosEmGrafos algoritmo = new AlgoritmosEmGrafos(grafo);
        algoritmo.iniciaBuscaEmProfundidade(1);
        
        System.out.println("\nGRAFO 1:\n");
        exibeGrafo(grafo, algoritmo);
        
        
        
        //GRAFO 2
        grafo = lerGrafo(args[1]);

        algoritmo = new AlgoritmosEmGrafos(grafo);
        algoritmo.iniciaBuscaEmProfundidade(1);
        
        System.out.println("\nGRAFO 2:\n");
        exibeGrafo(grafo, algoritmo);
        
        
        
    }
    private static Grafo lerGrafo(String diretorio) throws IOException{
        
        FileReader file = new FileReader(diretorio);
        BufferedReader read = new BufferedReader(file);
        String linha = read.readLine();
        Grafo grafo = new Grafo(Integer.parseInt(linha.split(" ")[0])); // Separo a string quando encontro um espaço, pego o primeiro termo da separação e transformo em inteiro.

        linha = read.readLine();
        while(linha != null){
            String[] splited = linha.split(" ");
            grafo.insereAresta(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), 1);
            linha = read.readLine();
        }
        read.close();
        return grafo;
    }
    private static void exibeGrafo(Grafo grafo, AlgoritmosEmGrafos algoritmo){
        System.out.println("\tNó\t    Distancia\t    Precedente\t");
        System.out.println(" -----------------------------------------------");
        for(int i = 0; i < grafo.getNumeroVertices(); i++){
            System.out.println("|\t"+i+"\t|\t"+algoritmo.getDistanciaProfundidade()[i]+"\t|\t"+algoritmo.getVerticePai()[i]+"\t|");
            System.out.println(" -----------------------------------------------");
        }
    }
}
