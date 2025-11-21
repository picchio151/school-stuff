import java.util.ArrayList;
/**
 * Classe che rappresenta la risorsa condivisa fra i due thread
 * da gestire con metodi "synchronized"
 * e con l'uso di wait() e notify()
 * @author frida
 * @version 1.0
 */
public class ListaClienti {
    private ArrayList<Integer> listaNumeri;
    private int ultimoArrivo;
    private int ultimoServito;
    private final int numeroMassimo = 2;
    /**
     * constructor
     * settaggio delle variabili di istanza
     */
    public ListaClienti() {
        listaNumeri = new ArrayList<Integer>();
        ultimoArrivo = 0;
        ultimoServito = 0;
    }

    /*synchronized parola chiave che gestisce il meccanismo del lock ovvero
    * 1) impedisce ad un altro thread l'esecuzione di tale
    * metodo, se un precedente thread lo sta già eseguendo
    * 2) senza di lui wait e notify non possono essere usati
    * si genera l'eccezione : IllegalMonitorStateException,*/

    /**
     * TODO: cosa fa?
     * @return = ultimo servito
     * @return = aggiunge 1 al ultimo servito
     * il wait fa fermare il thread quando ce la situazione elencata //5 /5, e il thread ripartira quando il notify del thraed sotto usera il notify
     *
     */
    public synchronized Integer rimuoviCliente() throws
            InterruptedException {
        while (ultimoServito >= ultimoArrivo) { //5 /5
            System.out.println("non ci sono arrivi dopo l'ultimo servito");
            wait();
        }
        ultimoServito++;
        return ultimoServito;
    }

    /**
     * TODO: cosa fa?
     * il synconized impedisce l accesso multiplo ad una risorsa gestendo il meccanismo di lock (semaforo)
     *      * @return = ritorna l ultimo arrivo come Integer, oppure null quando se gli arrivi saturano
     *      * increment di 1 l ultimo arrivo
     *      * produce un nuovo numero Int aggiungendo 1 al ultimo arrivo fino a quando non si satura
     *      * sensa il syncronized i metodi wait e notify daranno come output un eccezione
     */
    public synchronized Integer addCliente() {
        if (ultimoArrivo < numeroMassimo) {
            ultimoArrivo++;
            listaNumeri.add(ultimoArrivo);
            notify();
            return ultimoArrivo;
        }
        return null;
    }