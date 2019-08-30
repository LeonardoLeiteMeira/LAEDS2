/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Integer;
import java.util.Collections;

/**
 *
 * @author Leonardo
 */
public class Teste {
    public static void main(String[] args) throws IOException {
        
        Random gerador = new Random();
        
        FileWriter arq = new FileWriter("Dados.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        ArrayList<ArvoreBinaria> arvores = new ArrayList<ArvoreBinaria>();
        for(int i=0; i<9; i++){
            arvores.add(new ArvoreBinaria());
        }
        
        Item itemAux;
        
        System.out.println("Experimento 1: ");
        
        long startTime;
            
        int multiplicador = 1000, iterador = 1;   
        for(ArvoreBinaria arvore : arvores){
            for(int j = 0; j<iterador*multiplicador; j++){
                itemAux = new Item(j);
                arvore.insere(itemAux);
            }
            iterador++;
        }
        iterador=1;

        itemAux = new Item(12000);//Definindo um item que não está em nenhuma arvore

        for(ArvoreBinaria arvore : arvores){//fazendo a busca em todas as arvores               
            startTime = System.nanoTime();
            arvore.pesquisa(itemAux);
            arvore.SetTempoBusca(System.nanoTime() - startTime);
        }

        System.out.println("Número de comparações e tempo de cada arvore");
        arvores.forEach((arvore) -> {
            System.out.println("Comparações: "+arvore.getComparacoes()+" >> Tempo em ns: "+ arvore.getTempoBusca());
        });
        
        System.out.println();
        System.out.println();
        System.out.println("Experimento 2: ");
        
        ArrayList<ArvoreBinaria> arvores2 = new ArrayList<ArvoreBinaria>();
        for(int i=0; i<9; i++){
            arvores2.add(new ArvoreBinaria());
        }
        
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for(int i=0; i< 10000; i++){
            numeros.add(i);
        }
        Collections.shuffle(numeros);//seguencia de numeros aleatorios
        
        for(ArvoreBinaria arvore : arvores2){
            for(int j = 0; j<iterador*multiplicador; j++){
                itemAux = new Item(numeros.get(j));
                arvore.insere(itemAux);
            }
            iterador++;
            Collections.shuffle(numeros);
        }
        
        itemAux = new Item(12000);//Definindo um item que não está em nenhuma arvore

        for(ArvoreBinaria arvore : arvores2){//fazendo a busca em todas as arvores
            startTime = System.nanoTime();
            arvore.pesquisa(itemAux);
            arvore.SetTempoBusca(System.nanoTime() - startTime);
        }
            
        System.out.println("Número de comparações e tempo de cada arvore");
        arvores2.forEach((arvore) -> {
            System.out.println("Comparações: "+arvore.getComparacoes()+" >> Tempo em ns: "+ arvore.getTempoBusca());
        });
        


        //GRAFICO 1 NUMERO ELEMENTOS X GRAFICO COMPARAÇÕES
        arvores.forEach((arvore) -> {
            gravarArq.println(arvore.getNumElementos());
            gravarArq.println(arvore.getComparacoes());
        });
        
        gravarArq.println("");
        
        arvores2.forEach((arvore) -> {
            gravarArq.println(arvore.getNumElementos());
            gravarArq.println(arvore.getComparacoes());
        });
        
        gravarArq.println("");
        gravarArq.println("");
        
        
        //GRAFICO 2 NUMERO ELEMENTOS X TEMPO
        arvores.forEach((arvore) -> {
            gravarArq.println(arvore.getNumElementos());
            gravarArq.println(arvore.getTempoBusca());
        });
        
        gravarArq.println("");
        
        arvores2.forEach((arvore)->{
            gravarArq.println(arvore.getNumElementos());
            gravarArq.println(arvore.getTempoBusca());
        });
        
        arq.close();
    }
}
