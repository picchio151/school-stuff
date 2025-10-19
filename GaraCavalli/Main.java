import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input: lunghezza percorso
        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezzaPercorso = input.nextInt();

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

        while (!garaFinita) {
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
        }

        input.close();
    }
}
