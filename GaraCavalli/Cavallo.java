public class Cavallo {
    private String nome;
    private int distanzaPercorsa;
    private boolean azzoppato;

    public Cavallo(String nome) {
        this.nome = nome;
        this.distanzaPercorsa = 0;
        this.azzoppato = false;
    }

    public void corri(int passo) {
        if (!azzoppato) distanzaPercorsa += passo;
    }

    public String getNome() {
        return nome;
    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public boolean isAzzoppato() {
        return azzoppato;
    }

    public void setAzzoppato(boolean azzoppato) {
        this.azzoppato = azzoppato;
    }
}
