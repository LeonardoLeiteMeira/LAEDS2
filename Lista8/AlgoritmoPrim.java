
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Leonardo
 */

public class AlgoritmoPrim extends AlgoritmosEmGrafos{
    private ArrayList <Pair<Integer,Integer>> arestasArvoreGeradoraMinima;
    private int[] verticeAntecessorAGM;

    public AlgoritmoPrim(Grafo grafo){
        this.grafo = grafo;
    }

    public int iniciaArvoreGeradoraMinima(int vertice){
        this.inicial = vertice;

        verticeAntecessorAGM = new int[grafo.getNumeroVertices()];
        for(int i=0; i<grafo.getNumeroVertices(); i++){
            verticeAntecessorAGM[i] = -1;
        }

        return arvoreGeradoraMinima(vertice);
    }

    private int arvoreGeradoraMinima(int vertice){//Implementa Primm
        int primeiro, dest=0, menorPeso=0, pesoTotal=0;
        int [][] matrizAdj = grafo.getMatrizAdj();
        verticeAntecessorAGM[vertice] = vertice;//O PAI DO VERTICE INICIAL E ELE MESMO
        while(true){
            primeiro = 1;
            for(int i=0; i<grafo.getNumeroVertices(); i++){//PERCORRE TODOS OS VERTICES DO GRAFO
                if(verticeAntecessorAGM[i]!=-1){//ENCONTRA UM VERTICE QUE JA TEM UM PAI DEFINIDO
                    int numVisinhos = grafo.listaDeAdjacencia(i).size();
                    ArrayList<Integer> listaAdj = grafo.listaDeAdjacencia(i);
                    for(int j=0; j<numVisinhos; j++){//PERCORRE OS VIZINHOS DO VERTICE
                        if(verticeAntecessorAGM[ listaAdj.get(j) ] == -1){
                            if(primeiro==1){//DENTRE OS VISINHOS ENCONTRA AQUELE COM MENOR PESO
                                menorPeso =  matrizAdj[i][listaAdj.get(j)];//SALVA O VALOR DO MENOR PESO
                                vertice = i;
                                dest = listaAdj.get(j);//SALVA O VISINHO DE MENOR PESO
                                primeiro = 0;
                            }else{
                                if(menorPeso > matrizAdj[i][listaAdj.get(j)]){//DENTRE OS VISINHOS ENCONTRA AQUELE COM MENOR PESO
                                    menorPeso = matrizAdj[i][listaAdj.get(j)];//SALVA O VALOR DO MENOR PESO
                                    vertice = i;
                                    dest = listaAdj.get(j);//SALVA O VISINHO DE MENOR PESO
                                }
                            }
                        }
                    }
                }

            }
            if(primeiro==1)//SE NENHUMA ALTERAÇÃO FOR FEITA, A FLORESTA FOI FINALIZADA
                break;

            verticeAntecessorAGM[dest] = vertice;
        }
        for(int i=0; i<verticeAntecessorAGM.length; i++){//SOMA OS PESOS 
            if(i!=verticeAntecessorAGM[i])//NÃO PUXA A DISTANCIA DO VERTICE RAIZ PARA ELE MESMO, POIS ISSO RETORNA -1
                pesoTotal += grafo.getPeso(i, verticeAntecessorAGM[i]);
        }

        return pesoTotal; //RETORNAR O PESO TOTAL 
    }

    public ArrayList < Pair < Integer, Integer >> getArestasArvore(){//retorna arestasArvoreGeradoraMinima
        return arestasArvoreGeradoraMinima;
    }

    public int[] getVerticeAntecessorAGM(){//retorna verticeAntecessorAGM
        return verticeAntecessorAGM;
    }





}