import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public static void main(String[] args) throws IOException {

        /*Grafo grafo = new Grafo(6);
        grafo.insereArestaNaoOrientada(0, 1, 6);
        grafo.insereArestaNaoOrientada(0, 2, 1);
        grafo.insereArestaNaoOrientada(0, 3, 5);
        grafo.insereArestaNaoOrientada(1, 2, 2);
        grafo.insereArestaNaoOrientada(1, 4, 5);
        grafo.insereArestaNaoOrientada(2, 3, 2);
        grafo.insereArestaNaoOrientada(2, 4, 6);
        grafo.insereArestaNaoOrientada(2, 5, 4);
        grafo.insereArestaNaoOrientada(3, 5, 4);
        grafo.insereArestaNaoOrientada(5, 4, 3);

        AlgoritmoPrim teste = new AlgoritmoPrim(grafo);
        System.out.println(teste.iniciaArvoreGeradoraMinima(0)); */

       

        System.out.println("Exp: 2");
        FileReader file = new FileReader("grafoagm1.txt");
        BufferedReader read = new BufferedReader(file);
        String linha = read.readLine();
        int numVertices = Integer.parseInt(linha.split(" ")[0]);
        Grafo grafo = new Grafo(numVertices);

        linha = read.readLine();
        while(linha != null){
            String[] splited = linha.split(" ");
            grafo.insereAresta(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
            linha = read.readLine();
        }
        read.close();
        
        AlgoritmoDijkstra algoritmo = new AlgoritmoDijkstra(numVertices, grafo);
        algoritmo.iniciaCaminhoMaisCurto(0);
        
        System.out.println("\nGRAFO 1:\n");
        System.out.println("\tNó\t    Distancia\t    Precedente\t");
        System.out.println(" -----------------------------------------------");
        for(int i = 0; i < grafo.getNumeroVertices(); i++){
            System.out.printf("|\t"+i+"\t|\t"+algoritmo.getdistanciasCMC()[i]+"\t|\t"+algoritmo.getVerticeAntecessorCMC()[i]+"\t| ");
            int antecessor = i;
            System.out.printf("Caminho: "+antecessor);
            for(int j = 0; antecessor != 0; j++){
                antecessor = algoritmo.getVerticeAntecessorCMC()[antecessor];
                System.out.printf(" -> "+antecessor);
                if(antecessor == -1)
                    break;
            }
            System.out.println();
            System.out.println(" -----------------------------------------------");
        }

        

        file = new FileReader("grafoagm2.txt");
        read = new BufferedReader(file);
        linha = read.readLine();
        numVertices = Integer.parseInt(linha.split(" ")[0]);
        grafo = new Grafo(numVertices); // Separo a string quando encontro um espaço, pego o primeiro termo da separação e transformo em inteiro.

        linha = read.readLine();
        while(linha != null){
            String[] splited = linha.split(" ");
            grafo.insereAresta(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
            linha = read.readLine();
        }
        read.close();

        algoritmo = new AlgoritmoDijkstra(numVertices,grafo);
        algoritmo.iniciaCaminhoMaisCurto(0);
        System.out.println("\nGRAFO 2:\n");
        System.out.println("\tNó\t    Distancia\t    Precedente\t");
        System.out.println(" -----------------------------------------------");
        for(int i = 0; i < grafo.getNumeroVertices(); i++){
            if(algoritmo.getdistanciasCMC()[i] == Integer.MAX_VALUE)
            System.out.printf("|\t"+i+"\t|  "+algoritmo.getdistanciasCMC()[i]+" |   \t"+algoritmo.getVerticeAntecessorCMC()[i]+"\t|");
            else
                System.out.printf("|\t"+i+"\t|\t"+algoritmo.getdistanciasCMC()[i]+"\t|\t"+algoritmo.getVerticeAntecessorCMC()[i]+"\t|");
            int antecessor = i;
            System.out.printf("Caminho: "+antecessor);
            for(int j = 0; antecessor != 0; j++){
                antecessor = algoritmo.getVerticeAntecessorCMC()[antecessor];
                System.out.printf(" -> "+antecessor);
                if(antecessor == -1)
                    break;
            }
            System.out.println();
            System.out.println(" -----------------------------------------------");
        }
        
    }
}
