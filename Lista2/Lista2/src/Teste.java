import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Teste{
    public static void main(String[] args) throws IOException{
        ArrayList<ArvoreSBB> arvores = new ArrayList<ArvoreSBB>();
        
        System.out.println("INICIO EXPERIMENTO 1:");
        System.out.println("");
        System.out.println("");
        
        for(int i=0; i<10; i++)//CRIANDO AS ARVORES
            arvores.add(new ArvoreSBB());

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for(int i=1; i<=500000; i++)//OS NUMEROS QU SERAM INSERIDOS 
            numeros.add(i);
            
        int iterador = 1;
        Item itemAux;
        for(ArvoreSBB arvore : arvores){//PREENCHENDO AS ARVORES
            for(int i = 0; i< iterador*10000; i++){
                itemAux = new Item(numeros.get(i));
                arvore.insere(itemAux);
            }
            iterador++;
        }
        
        itemAux = new Item(150000);
        for(ArvoreSBB arvore : arvores){//PESQUISANDO UM ELEMENTO QUE NÃO EXISTE
            arvore.pesquisa(itemAux);
            System.out.println("A arvore "+arvores.indexOf(arvore)+" percorreu "+ arvore.getNosVisitados()+ " nos");
        }
        
        System.out.println("");
        
        for(ArvoreSBB arvore : arvores)//QUANTIDADE DE NIVEIS NA ARVORE
            System.out.println("Tamanho da arvore "+ arvores.indexOf(arvore) + ": " +arvore.getTamanhoArvore());
        
        
        /*
        *
        *FIM DO EXPERIMENTO 1
        *
        *
        *
        */
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        System.out.println("INICIO EXPERIMENTO 2:");
        System.out.println("");
        System.out.println("");
        
        arvores.clear();
        arvores = new ArrayList<ArvoreSBB>();
        for(int i=0; i<10; i++)//CRIANDO AS ARVORES
            arvores.add(new ArvoreSBB());
        Collections.shuffle(numeros);//NUMEROS ALEATORIOS
        
        iterador = 1;
        for(ArvoreSBB arvore : arvores){//PREENCHENDO AS ARVORES
            for(int i = 0; i< iterador*10000; i++){
                itemAux = new Item(numeros.get(i));
                arvore.insere(itemAux);
            }
            iterador++;
        }
        
        itemAux = new Item(550000);
        for(ArvoreSBB arvore : arvores){//PESQUISANDO UM ELEMENTO QUE NÃO EXISTE
           arvore.pesquisa(itemAux);
            System.out.println("A arvore "+arvores.indexOf(arvore)+" percorreu "+ arvore.getNosVisitados()+ " nos");
        }
        
        System.out.println("");
        
        for(ArvoreSBB arvore : arvores)//QUANTIDADE DE NIVEIS NA ARVORE
            System.out.println("Tamanho da arvore "+ arvores.indexOf(arvore) + ": " +arvore.getTamanhoArvore());
        
        
        
        /*
        *
        *FIM DO EXPERIMENTO 2
        *
        *
        *
        */
        
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        System.out.println("INICIO EXPERIMENTO 3:");
        System.out.println("");
        System.out.println("");
        
        arvores.clear();
        arvores = new ArrayList<ArvoreSBB>();
        for(int i=0; i<10; i++)//CRIANDO AS ARVORES
            arvores.add(new ArvoreSBB());
        
        Collections.shuffle(numeros);//NUMEROS ALEATORIOS
        
        int tamanhos [] = {5,10,50,100,500,1000,5000,10000,50000,100000};
        
        int aux = 0;
        for(ArvoreSBB arvore : arvores){//PREENCHENDO AS ARVORES
            for(int i = 0; i<tamanhos[aux] ; i++){
                itemAux = new Item(numeros.get(i));
                arvore.insere(itemAux);
            }
            aux++;
        }
        
        for(ArvoreSBB arvore : arvores)//QUANTIDADE DE NIVEIS NA ARVORE
            System.out.println("Tamanho da arvore "+ arvores.indexOf(arvore) + ": " +arvore.getTamanhoArvore());
        
        
        /*
        *
        *FIM DO EXPERIMENTO 3
        *
        *
        *
        */
    }
}