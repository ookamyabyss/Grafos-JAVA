// Uneb. Universidade do Estado da Bahia
// Aluno. Rafael Roberto Coutinho da Cruz

import java.util.ArrayList;

public class Grafo {
    
    // declaration
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    private int quantidadeVertices;
    String [][] matrizAdjacencia = new String[0][0];
    
  
    public Grafo(Vertice vertice) {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.vertices.add(vertice);
    }
    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.quantidadeVertices = 0;        
    }
    // Adicionar Vertice
    public void addVertice(Vertice v) {
        this.vertices.add(v);
        System.out.println("Vertice Adicionado");
        //Amplia a matriz em uma linha e uma coluna, mantendo o conteudo.
            quantidadeVertices++;
            String tempMatrizAdjacencia[][] = new String[quantidadeVertices][quantidadeVertices];
            //zerandoMatriz
            for(int i = 0; i < tempMatrizAdjacencia.length; i++){
              for(int j = 0; j < tempMatrizAdjacencia[i].length; j++){
                tempMatrizAdjacencia[i][j] = "0";
              }  
            }
            //Percorre toda matriz antiga passando os elementos pra nova
            for (int linha = 0; linha < quantidadeVertices - 1; linha++)
                for (int coluna = 0; coluna < quantidadeVertices - 1; coluna++)
                    tempMatrizAdjacencia[linha][coluna] = matrizAdjacencia[linha][coluna];
            matrizAdjacencia = tempMatrizAdjacencia;    
    }
    // Adicionar Aresta
    public void addAresta(Aresta a) {
        this.arestas.add(a);
        
        a.getInicio().addAresta(a);
        a.getFinal().addAresta(a);
        System.out.println("Aresta Adicionada");
        
        int indice1 = a.getInicio().getValor(), indice2 = a.getFinal().getValor();
        if (indice1 != -1 && indice2 != -1) {//Apenas insere se ambas as chaves existirem no grafo
            matrizAdjacencia[indice1 - 1][indice2 - 1] = "1";
            matrizAdjacencia[indice2 - 1][indice1 - 1] = "1";
        }
    }  
    // Deleta Vertice 
    public void deleteVertice(int valor) {
        Vertice verticeDel = this.vertices.stream().filter(vertice -> vertice.getValor() == valor).findFirst().get();

        this.arestas.removeIf(aresta -> aresta.getInicio() == verticeDel || aresta.getFinal() == verticeDel);
        this.vertices.remove(verticeDel);
      System.out.println("Vertice Deletado");
    }
    // Deleta Aresta
    public void deleteAresta(int valorInicio, int valorFinal) {
        this.arestas.removeIf(aresta -> (aresta.getInicio().getValor() == valorInicio
                || aresta.getFinal().getValor() == valorInicio)
                &&
                (aresta.getInicio().getValor() == valorFinal
                        || aresta.getFinal().getValor() == valorFinal));
      System.out.println("Aresta Deletada");
    }
    // Printa todos os Vertices
    public void printVertices() {
        if (this.vertices.isEmpty()) {
            System.out.println("Não há Vertices no Grafo");
        }
        this.vertices.forEach((vertice) -> {
            System.out.print("|" + vertice.getValor());
        });
        System.out.println("\n");
    }
    // Printa todos as Arestas
    public void printConections() {
        if (this.arestas.isEmpty()) {
            System.out.println("Não há Arestas no Grafo");
        }
        this.arestas.forEach((aresta) -> {
            System.out.println("[ Inicio: " + aresta.getInicio().getValor() + " -- " + aresta.getFinal().getValor() + " :Fim]");
        });
        System.out.println("\n");
    }
    public void testConection(Vertice v1, Vertice v2) {
        this.arestas.forEach((aresta) -> {
            boolean testInicio = (aresta.getInicio() == v1
                    || aresta.getInicio() == v2);

            boolean testFim = (aresta.getFinal() == v1
                    || aresta.getFinal() == v2);

            if (testInicio && testFim) {
                System.out.println("Aresta encontrada: ");
                System.out.println(
                        "[" + aresta.getInicio().getValor() + " -> "
                                + aresta.getFinal().getValor() + "]");
            } else {
                System.out.println("Aresta não encontrada: ");
            }
        });
    }
    public void grau() {
        Integer grauMax = this.vertices.get(0).getValor();
        Integer grauMin = this.vertices.get(0).getValor();
        Double grauMed = 0.0;

        for (Vertice vertice : this.vertices) {
            Integer grauV = this.grauVertice(vertice);
            grauMed += grauV;

            if (grauV > grauMax) {
                grauMax = grauV;
            }

            if (grauV < grauMin) {
                grauMin = grauV;
            }

        }
        grauMed /= this.vertices.size();

        System.out.println("GRAU MÍNIMO: " + grauMin);
        System.out.println("GRAU MÉDIO: " + grauMed);
        System.out.println("GRAU MÁXIMO: " + grauMax);
    }
    private Integer grauVertice(Vertice vertice) {

        return vertice.getArestas().size();
    }
    public Integer grauVertice(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        return vertice.getArestas().size();
    }
    public boolean checkConexo() {
        for (Vertice vertice : this.vertices) {
            if (vertice.getArestas().size() < 1) {
                System.out.println("Grafo não Conexo, vertice de valor " + vertice.getValor() + " sem conexão");
                return false;
            }
        }
        System.out.println("Grafo Conexo, todos os vertices tem ao menos uma conexão");
        return true;
    }
    public void adjacencias(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        vertice.getArestas().forEach((aresta) -> {
            System.out.println("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
        });
    }
   
    public void printMatriz() {
        System.out.println("\n\nMatriz de Adjacencia:\n");
        for(int i = 0; i < matrizAdjacencia.length; i++){
          for(int j = 0; j < matrizAdjacencia[i].length; j++){
            System.out.print("\t\t"+matrizAdjacencia[i][j]);
          }
        System.out.println();  
        }
    }
}
