public class GestoreArrivi implements Runnable {

    private ListaClienti listaClienti;
    private final int attesaArrivi = 2000;
    private int IDtotem;

    public GestoreArrivi(ListaClienti listaClienti, int IDtotem) {
        this.IDtotem = IDtotem;
        this.listaClienti = listaClienti;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);

                Integer clienteArrivato = listaClienti.addCliente();
                System.out.println("Arrivo Cliente Numero " + clienteArrivato +
                        " dal totem " + IDtotem);
            }
        } catch (InterruptedException e) {
            System.out.println("Totem " + IDtotem + " interrotto");
        } finally {
            System.out.println("Posta Chiusa lato totem " + IDtotem);
        }
    }
}
