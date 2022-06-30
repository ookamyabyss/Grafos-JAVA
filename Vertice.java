// Uneb. Universidade do Estado da Bahia
// Aluno. Rafael Roberto Coutinho da Cruz

import java.util.ArrayList;

public class Vertice {

    // declaration
    private int valor;
    private ArrayList<Aresta> arestas;
    
    // constructor method
    public Vertice(int valor) {
        this.valor = valor;
        this.arestas = new ArrayList<Aresta>();
    }
    // getValor
    public int getValor() {
        return valor;
    }
    // getArestas
    public ArrayList<Aresta> getArestas() {
        return this.arestas;
    }
    // setValor
    public void setValor(int valor) {
        this.valor = valor;
    }
    // setAresta - adicionar Aresta
    public void addAresta(Aresta a) {
        this.arestas.add(a);
    }

}
