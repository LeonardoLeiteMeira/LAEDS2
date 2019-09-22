/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Teste {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        experimento1(new FileInputStream(args[0]));
    }

    public static void experimento1(InputStream diretorio)throws IOException {
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(diretorio));
        int caracter = 0;
        
        ArrayList<ArvorePatricia> arvores = new ArrayList();//CRIANDO UM ARRAY DE ARVORES
        for (int i = 1; i < 9; i++)arvores.add(new ArvorePatricia(i));//COLOCANDO AS ARVORES NO ARRAY
        
        for(ArvorePatricia arvore: arvores){//LENDO O ARQUIVO E PREENCHENDO AS ARVORES
            
            while ((caracter = bf.read()) != -1) {
                arvore.insere((char)caracter);
            }
            
        }
        bf.close();
        
        //MOSTRANDO O NUMERO DE CHAVES DISTINTAS EM CADA ARVORE
        for(ArvorePatricia arvore: arvores){
            System.out.println("Chaves distintas "+ arvore.getCaracteresDistintos());
        }
        
        char a='a';//CARACTER QUE PERTENCE AO TEXTO
        for(ArvorePatricia arvore : arvores){
            arvore.pesquisa(a);
            System.out.println("Quantidade de nos visitados: "+ arvore.getNosVisitadosPesquisa());
        }
        
    }
    
    public static void experimento2(InputStream diretorio)throws IOException {
        
        ArvorePatricia arvore1 = new ArvorePatricia(9);
        ArvorePatricia arvore2 = new ArvorePatricia(10);
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(diretorio));
        int caracter = 0;
        
        while ((caracter = bf.read()) != -1) {
            arvore1.insere((char)caracter);
            arvore2.insere((char)caracter);
        }
        
        System.out.println("Caracteres distintos arvore 1 "+ arvore1.getCaracteresDistintos());
        System.out.println("Caracteres distintos arvore 2 "+ arvore2.getCaracteresDistintos());
        
        char a='a';//CARACTER A SER BUSCADO
        arvore1.pesquisa(a);
        arvore2.pesquisa(a);
        
        System.out.println("Numero de nos visitados arvore 1 "+ arvore1.getNosVisitadosPesquisa());
        System.out.println("Numero de nos visitados arvore 2 "+ arvore2.getNosVisitadosPesquisa());
    }
}
