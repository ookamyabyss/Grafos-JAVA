// Uneb. Universidade do Estado da Bahia
// Aluno. Rafael Roberto Coutinho da Cruz

import java.text.BreakIterator;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Grafo g = new Grafo();
      
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Aresta a1 = new Aresta(v1, v2);
        Aresta a2 = new Aresta(v1, v3);
        Aresta a4 = new Aresta(v1, v4);
        Aresta a3 = new Aresta(v2, v3);
        
        // ADICIONAR VERTICES 
        g.addVertice(v1);
        g.addVertice(v2);
        g.addVertice(v3);
        g.addVertice(v4);
        // ADICIONAR ARESTAS ENTRE DOIS VERTICES
        g.addAresta(a1);
        g.addAresta(a2);
        g.addAresta(a3);
        g.addAresta(a4);
      
        Scanner input = new Scanner(System.in);
        boolean Menu = true;
        int valor = 0;
      
        while (Menu) {
          System.out.println("0 - Sair do programa");
          System.out.println("1 - Vertices");
          System.out.println("2 - Arestas");
          System.out.println("3 - Matriz Adjacencia");
          System.out.println("4 - Grau Menor, Medio, Maior");
          System.out.println("5 - Grau Conexo ");
          System.out.println("6 - Caminho Euler ");
          System.out.println("7 - Grau do Vertice ");
          System.out.println("8 - Adjacencias Vertice ");
          System.out.println("9 - Deleta Vertice ");
          System.out.println("10 - Deleta Aresta ");

          System.out.print("Escolha uma opção : ");
          int option = input.nextInt();
          System.out.println(); 
          
          switch (option) {
            
            case 1:
              g.printVertices(); // MOSTRAR VERTICES  NO GRAFO         
            break;
            case 2:
              g.printConections(); // MOSTRAR ARESTAS NO GRAFO           
            break;
            case 3:
              g.matrizAdjacencia(); // MATRIZ ADJACENCIAS         
            break;
            case 4:
              g.grau(); // GRAU MINIMO, MEDIO, MAXIMO        
            break;
            case 5:
              boolean conexo = g.checkConexo(false); // CHECAR GRAFO CONEXO
              if(conexo)
                System.out.print("GRAFO CONEXO"); 
              else 
                System.out.print("GRAFO NÃO CONEXO");
              System.out.println("\n");
            break;
            case 6:
              g.caminhodeEuler(); /// CAMINHO DE EULER
            break;
            case 7: 
              System.out.print("DIGITE O VERTICE PARA VÊ O GRAU ");
              valor = input.nextInt();
              System.out.println("GRAU DO VERTICE: "+g.grauVertice(valor)); // GRAU DO VERTICE
              System.out.println("\n");
            break;
            case 8:
              System.out.print("DIGITE O VERTICE PARA VÊ AS ADJACENCIAS ");
              valor = input.nextInt();
              g.adjacencias(valor);
            break;
            case 9:
              System.out.println("Deletando Vertice:");
              valor = input.nextInt();
              g.deleteVertice(valor); // DELETAR VERTICE
            break;
            case 10:
            System.out.println("Deletando Aresta:");
            System.out.print("DIGITE O VERTICE INICIAL: ");
            int vInicio = input.nextInt();
            System.out.print("DIGITE O VERTICE FINAL: ");
            int vFinal = input.nextInt();
            g.deleteAresta(vInicio, vFinal); // DELETAR ARESTA
            break;
            default:
              Menu = false;
          }
      }

 
    }
}
