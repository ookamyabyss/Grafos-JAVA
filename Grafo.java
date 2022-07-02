// Uneb. Universidade do Estado da Bahia
// Aluno. Rafael Roberto Coutinho da Cruz

import java.util.ArrayList;

public class Grafo {
    
    // declaration
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;

 
    public Grafo(Vertice vertice) {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.vertices.add(vertice);
    }
    
    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }
    
    // Adicionar Vertice
    public void addVertice(Vertice v) {
        this.vertices.add(v);
        System.out.println("Vertice Adicionado");       
    }
    
    // Adicionar Aresta
    public void addAresta(Aresta a) {
        this.arestas.add(a);
        
        a.getInicio().addAresta(a);
        a.getFinal().addAresta(a);
        System.out.println("Aresta Adicionada");       
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
        System.out.println("\n");
    }
    
    private Integer grauVertice(Vertice vertice) {

        return vertice.getArestas().size();
    }
    
    public Integer grauVertice(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        return vertice.getArestas().size();
    }
    
    public boolean checkConexo(boolean showPrint) {
        for (Vertice vertice : this.vertices) {
            if (vertice.getArestas().size() < 1) {
                if (showPrint) {
                    System.out.println("Grafo não Conexo, vertice de valor " + vertice.getValor() + " sem conexão");
                }
                return false;
            }
        }
        if (showPrint) {
            System.out.println("Grafo Conexo, todos os vertices tem ao menos uma conexão");
        }
        return true;
    }
    
    public void adjacencias(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        vertice.getArestas().forEach((aresta) -> {
            System.out.println("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
        });
    }

    // Matriz Adjacencia
    public void matrizAdjacencia() {
        //criandoMatriz
        int [][] matrizAdjacencia = new int[vertices.size()][vertices.size()];
              
        System.out.println("\n\nMatriz de Adjacencia:\n");
        
        this.arestas.forEach((aresta) -> {
          matrizAdjacencia[aresta.getInicio().getValor() - 1][aresta.getFinal().getValor() - 1] = 1;
          matrizAdjacencia[aresta.getFinal().getValor() - 1][aresta.getInicio().getValor() - 1] = 1;   
        });
        //printMatriz
        for(int i = 0; i < matrizAdjacencia.length; i++){
          for(int j = 0; j < matrizAdjacencia[i].length; j++){
            System.out.print("\t\t"+matrizAdjacencia[i][j]);
          }
        System.out.println();  
        }
      System.out.println("\n");
    }

    // Caminho de Euler  
    public void caminhodeEuler() {
      int countImpares = 0;
      
      boolean conexo = this.checkConexo(false);
      
      for (Vertice vertice : this.vertices) {
            int grauV= this.grauVertice(vertice);

            if (grauV % 2 != 0) {
              countImpares += 1;
            } 
      }
        
      if(countImpares == 2 && conexo || countImpares == 0 && conexo){
         System.out.print("Existe um caminho de Euler"); 
      } else 
        System.out.print("Não existe um caminho de Euler");      
     System.out.println("\n");   
    } 
  
}
