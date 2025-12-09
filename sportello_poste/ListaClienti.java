import java.util.ArrayList;

public class ListaClienti {
    private ArrayList<Integer> listaNumeri;
    private int ultimoArrivo;
    private int ultimoServito;
    private final int numeroMassimo = 10;

    public ListaClienti() {
        listaNumeri = new ArrayList<>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }

    public synchronized Integer rimuoviCliente() throws InterruptedException {
        while (ultimoServito >= ultimoArrivo) {
            System.out.println("Non ci sono clienti in attesa, sportello in pausa...");
            wait();
        }
        ultimoServito++;
        notifyAll();
        return ultimoServito;
    }

    public synchronized Integer addCliente() throws InterruptedException {
        while (ultimoArrivo - ultimoServito >= numeroMassimo) {
            System.out.println("Totem in attesa: troppi clienti in coda");
            wait();
        }
        ultimoArrivo++;
        listaNumeri.add(ultimoArrivo);
        notifyAll();
        return ultimoArrivo;
    }
}
