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
    
    // ADD VERTICE
    public void addVertice(Vertice v) {
        this.vertices.add(v);
        System.out.println("Vertice Adicionado");       
    }
    
    // ADD ARESTA
    public void addAresta(Aresta a) {
        this.arestas.add(a);
 
        a.getInicio().addAresta(a);
        a.getFinal().addAresta(a);
        System.out.println("Aresta Adicionada");       
    }  
    
    // DELETE DE VERTICE
    public void deleteVertice(int valor) {
        Vertice verticeDel = this.vertices.stream().filter(vertice -> vertice.getValor() == valor).findFirst().get();
        this.arestas.removeIf(aresta -> aresta.getInicio() == verticeDel || aresta.getFinal() == verticeDel);
        this.vertices.remove(verticeDel);
      System.out.println("Vertice Deletado");       
    }
    
    // DELETE DE ARESTA
    public void deleteAresta(int valorInicio, int valorFinal) {
        this.arestas.removeIf(aresta -> (aresta.getInicio().getValor() == valorInicio
                || aresta.getFinal().getValor() == valorInicio)
                &&
                (aresta.getInicio().getValor() == valorFinal
                        || aresta.getFinal().getValor() == valorFinal));
      System.out.println("Aresta Deletada");
    }
    
    // PRINT DE VERTICES
    public void printVertices() {
        if (this.vertices.isEmpty()) {
            System.out.println("Não há Vertices no Grafo");
        } else {
          System.out.println("SEUS VERTICES SÃO: ");
          this.vertices.forEach((vertice) -> {
            System.out.print("-> " + vertice.getValor() + "| ");
          });
        }
        System.out.println("\n");
    }
    
    // PRINT DE ARESTAS
    public void printConections() {
        if (this.arestas.isEmpty()) {
          System.out.println("Não há Arestas no Grafo");
        } else {
          this.arestas.forEach((aresta) -> {
            System.out.println("Inicio: " + aresta.getInicio().getValor() + " | " + aresta.getFinal().getValor() + " :Fim");
          });  
        }
        System.out.println("\n");
    }
  
    public void testConnection(int v1, int v2) {
 
      this.arestas.forEach((aresta) -> {
        boolean testInicio = (aresta.getInicio().getValor() == v1
                      || aresta.getInicio().getValor() == v2);
         boolean testFim = (aresta.getFinal().getValor() == v1
                      || aresta.getFinal().getValor() == v2);
        if (testInicio && testFim) {
          System.out.println("Aresta encontrada: ");
          System.out.println("[" + aresta.getInicio().getValor() + " -> " + aresta.getFinal().getValor() + "]");
        } 
      });     
    }
    
    // DIZ GRAU MINIMO, MEDIO, MAXIMO 
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

    // DIZ O GRAU DO VERTICE ESCOLHIDO
    private Integer grauVertice(Vertice vertice) {
        return vertice.getArestas().size();
    }
    
    public Integer grauVertice(int valor) {
        Vertice vertice = this.vertices.stream().filter(v -> v.getValor() == valor).findFirst().get();

        return vertice.getArestas().size();
    }

    // DIZ SE O GRAFO E CONEXO OU NÃO
    public boolean checkConexo(boolean showPrint) {
      // preenchendo matriz 
      int [][] matrizConexo = new int[vertices.size()][vertices.size()];    
      this.arestas.forEach((aresta) -> {
          matrizConexo[aresta.getInicio().getValor() - 1][aresta.getFinal().getValor() - 1] = 1;
          matrizConexo[aresta.getFinal().getValor() - 1][aresta.getInicio().getValor() - 1] = 1;   
        });
      
      
      // COLOCA NUMERO 2 NA DIAGONAL
      for(int i = 0; i < matrizConexo.length; i++){ 
          for(int j = 0; j < matrizConexo[i].length; j++){
            matrizConexo[i][i] = 2;
          }   
      }

      
      // PECORRE A MATRIZ SE ALGUM INDICE TIVE 0 ENTÃO NÃO CONEXO
      for(int i = 0; i < matrizConexo.length; i++){
        if(matrizConexo[i][i] == 0){
          return false;      
        }

        for(int j = 0; j < matrizConexo[i].length; j++){
          if(matrizConexo[i][j] == 0){
            return false;      
          }
        }
            
      }
    return true;   
    
    }

    // DIZ A ARESTAS INTERLIGADAS A UM VERTICE
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
