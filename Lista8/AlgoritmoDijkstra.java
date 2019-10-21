
import java.util.ArrayList;


public class AlgoritmoDijkstra extends AlgoritmosEmGrafos {

    private int[] verticeAntecessorCMC;//armazena o vertice antecessor de cada no pertencente a pelo menos um dos caminhos encontrados durante a execuc~ao do algoritmo.
    private int[] distanciasCMC;//guarda a distância entre o vertice inicial do algoritmo de Dijkstra e cada um dos outros nos do grafo.

    public AlgoritmoDijkstra(int vertices, Grafo grafo) {
        verticeAntecessorCMC = new int[grafo.getNumeroVertices()];
        distanciasCMC = new int[grafo.getNumeroVertices()];
        this.grafo = grafo;
    }

    public int iniciaCaminhoMaisCurto(int verticeInicial, int verticeFinal) {//calcula o caminho mais curto entre o vertice inicial e todos os outros.
        caminhoMaisCurto(verticeInicial);//Dijkstra
        return distanciasCMC[verticeFinal];
    }

    public int[] iniciaCaminhoMaisCurto(int verticeInicial) { //calcula o caminho mais curto entre o vertice inicial e todos os outros.
        caminhoMaisCurto(verticeInicial);
        return distanciasCMC;
    }

    private void caminhoMaisCurto(int verticeInicial) {//implementa o algoritmo de Dijkstra.
        int numVertiesGrafo = grafo.getNumeroVertices();
        boolean filhosPercorridos[] = new boolean[numVertiesGrafo];
        for (int i = 0; i < numVertiesGrafo; i++) {//PREENCHE OS VERTICES COM DISTANCIA INFINITY E SEM VERTICE ANTECESSOR
            distanciasCMC[i] = Integer.MAX_VALUE;
            verticeAntecessorCMC[i] = -1;
            filhosPercorridos[i] = false;//NENHUM VERTICE AINDA TEVE OS FILHOS PERCORRIDOS
        }
        distanciasCMC[verticeInicial] = 0;//VERTICE INICIAL TEM DISTANCIA 0

        ArrayList<Integer> listaDeVerticesVisitar = new ArrayList();
        listaDeVerticesVisitar.add(verticeInicial);//LISTA DE VERTICES A VISTAR. COMEÇA COM O VERTICE INICIAL

        
        for (int index=0; index<listaDeVerticesVisitar.size(); index++) {//PERCORRE OS VERTICES A VISITAR
            
            if(filhosPercorridos[listaDeVerticesVisitar.get(index)])//SE O VERTICE JA FOI VISITADO E PULADO PARA A PROXIMA ITERAÇÃO
                continue;
            
            ArrayList<Integer> listaAdjacenciaVerticeAtual = grafo.listaDeAdjacencia(listaDeVerticesVisitar.get(index));//PEGA OS ADJACENTES
            listaDeVerticesVisitar.addAll(listaAdjacenciaVerticeAtual);//ADICIONA OS FILHOS DO VERTICE ATUAL A LISTA DE VERTICES A VISITAR
            
            for (Integer vertice : listaAdjacenciaVerticeAtual) {//PERCORRE OS FILHOS DO VERTICE ATUAL
                
                if (distanciasCMC[vertice] > (grafo.getPeso(listaDeVerticesVisitar.get(index), vertice)+ distanciasCMC[listaDeVerticesVisitar.get(index)])){
                    
                    distanciasCMC[vertice] = grafo.getPeso(listaDeVerticesVisitar.get(index), vertice) + distanciasCMC[listaDeVerticesVisitar.get(index)];
                    verticeAntecessorCMC[vertice] = listaDeVerticesVisitar.get(index);
                }
            }
            filhosPercorridos[listaDeVerticesVisitar.get(index)] = true;
        }
    }

    public int[] getVerticeAntecessorCMC() {
        return verticeAntecessorCMC;
    }
    
    public int[] getdistanciasCMC() {
        return distanciasCMC;
    }
}
