public class Cavallo {
    private String nome;
    private int distanzaPercorsa;

    public Cavallo(String nome) {
        this.nome = nome;
        this.distanzaPercorsa = 0;
    }

    public void corri(int passo) {
        distanzaPercorsa += passo;
    }

    public String getNome() {
        return nome;
    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }
}
