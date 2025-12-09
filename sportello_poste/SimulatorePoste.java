import java.time.Duration;
import java.time.LocalTime;

public class SimulatorePoste {
    public static void main(String[] args) {

        LocalTime apertura = LocalTime.now();
        System.out.println("APERTURA POSTA: " + apertura);

        ListaClienti listaClienti = new ListaClienti();
        Thread arriviThread = new Thread(new GestoreArrivi(listaClienti, 1));
        Thread arriviThread2 = new Thread(new GestoreArrivi(listaClienti, 2));
        Thread sportelloThread = new Thread(new Sportello(listaClienti, "Leonardo"));
        Thread sportelloThread2 = new Thread(new Sportello(listaClienti, "Giulio"));

        arriviThread.start();
        arriviThread2.start();
        sportelloThread.start();
        sportelloThread2.start();

        // Aspettiamo che finiscano
        try {
            arriviThread.join();
            arriviThread2.join();
            sportelloThread.join();
            sportelloThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime chiusura = LocalTime.now();
        System.out.println("CHIUSURA POSTA: " + chiusura);

        Duration durata = Duration.between(apertura, chiusura);
        long sec = durata.getSeconds();

        long ore = sec / 3600;
        long minuti = (sec % 3600) / 60;
        long secondi = sec % 60;

        System.out.println("TEMPO TOTALE APERTURA POSTA: "
                + ore + "h "
                + minuti + "m "
                + secondi + "s");
    }
}
