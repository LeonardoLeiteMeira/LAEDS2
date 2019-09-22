
import java.util.ArrayList;

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
        System.out.println("INICIO EXPERIMENTO 1");
        System.out.println("");
        experimento(2);
        System.out.println("");
        System.out.println("FIM EXPERIMENTO 1");
        
        System.out.println("");
        System.out.println("");
        
        System.out.println("INICIO EXPERIMENTO 2");
        System.out.println("");
        experimento(4);
        System.out.println("");
        System.out.println("FIM EXPERIMENTO 2");
        
        System.out.println("");
        System.out.println("");
        
        System.out.println("INICIO EXPERIMENTO 3");
        System.out.println("");
        experimento(8);
        System.out.println("");
        System.out.println("FIM EXPERIMENTO 3");
        
        System.out.println("");
        System.out.println("");
        
        System.out.println("INICIO EXPERIMENTO 4");
        System.out.println("");
        experimento(16);
        System.out.println("");
        System.out.println("FIM EXPERIMENTO 4");
        /*
        
        ArrayList<Integer> numeros = new ArrayList<>();
        for(int i=0; i<100000; i++) numeros.add(i);//LISTA COM VALORES DE 1 A 100.000*/
    }
    
    public static void experimento(int ordem){        
        ArrayList<ArvoreB> arvores = new ArrayList<>();

        for(int i=0; i<10; i++)arvores.add(new ArvoreB(ordem));//CRIANDO AS 10 ARVORES
     
        int multiplicador=1;
        Item itemAux;
        for(ArvoreB arvore: arvores){//PREENCHENDO AS ARVORES
            for(int i=0; i<10000*multiplicador; i++){
                itemAux = new Item(i);
                arvore.insere(itemAux);
            }
            multiplicador++;
        }
        
        //PESQUISANDO UM ELEMENTO NÃO EXISTENTE
        itemAux = new Item(1000050);
        for(ArvoreB arvore: arvores) arvore.pesquisa(itemAux);
        arvores.forEach((arvore) -> { 
            System.out.println("Paginas: "+arvore.getPaginasVisitadas()+" && Comparações: "+arvore.getNumeroComparacoes());
        });
    }
   
}
