import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input: lunghezza percorso
        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezzaPercorso = input.nextInt();
        input.nextLine(); // pulisce il buffer

        // Creazione cavalli
        Cavallo[] cavalli = {
                new Cavallo("Fulmine"),
                new Cavallo("Tempesta"),
                new Cavallo("Saetta"),
                new Cavallo("Vento"),
                new Cavallo("Tuono")
        };

        boolean garaFinita = false;
        int passo = 5; // metri per ciclo

        System.out.println("\n--- INIZIO GARA ---");
        System.out.println("Digita 'stop' per interrompere la gara.\n");

        // Thread separato per gestire l'interruzione
        Thread interruptThread = new Thread(() -> {
            Scanner stopInput = new Scanner(System.in);
            while (true) {
                String comando = stopInput.nextLine();
                if (comando.equalsIgnoreCase("stop")) {
                    Thread.currentThread().interrupt(); // segna l'interruzione
                    break;
                }
            }
            stopInput.close();
        });

        interruptThread.start(); // Avvia il thread che ascolta l'interruzione

        while (!garaFinita) {
            // Controllo se il thread è stato interrotto
            if (interruptThread.isInterrupted()) {
                System.out.println("\n🚫 Gara interrotta manualmente!");
                break;
            }

            for (Cavallo c : cavalli) {
                c.corri(passo);
                System.out.println(c.getNome() + " ha percorso " + c.getDistanzaPercorsa() + " metri.");

                if (c.getDistanzaPercorsa() >= lunghezzaPercorso) {
                    System.out.println("\n🏆 Il vincitore è " + c.getNome() + "!");
                    garaFinita = true;
                    break;
                }
            }

            System.out.println("---------------------------");

            // Sleep per rallentare il ciclo (simula il tempo della corsa)
            try {
                Thread.sleep(1000); // 1 secondo
            } catch (InterruptedException e) {
                System.out.println("\n⚠️ Gara interrotta durante l'attesa!");
                break;
            }
        }

        System.out.println("\n--- GARA TERMINATA ---");
        input.close();
    }
}
