// Uneb. Universidade do Estado da Bahia
// Aluno. Rafael Roberto Coutinho da Cruz

public class Aresta {
    
    // declaration
    private Vertice inicio;
    private Vertice fim;

    // constructor method
    public Aresta(Vertice vIni, Vertice vFim) {
        this.inicio = vIni;
        this.fim = vFim;
    }
    // getInicio
    public Vertice getInicio() {
        return this.inicio;
    }
    // getFinal
    public Vertice getFinal() {
        return this.fim;
    }
    // setInicio
    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }
    // setFinal
    public void setFinal(Vertice fim) {
        this.fim = fim;
    }

}
