import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezzaPercorso = input.nextInt();
        input.nextLine(); // pulisce il buffer

        // ArrayList di cavalli
        ArrayList<Cavallo> cavalli = new ArrayList<>();
        cavalli.add(new Cavallo("caaanta "));
        cavalli.add(new Cavallo("sannntoni"));
        cavalli.add(new Cavallo("giuuulio"));
        cavalli.add(new Cavallo("aaalex"));
        cavalli.add(new Cavallo("piciooo"));

        boolean garaFinita = false;
        int passo = 5; // metri per ciclo

        try (FileWriter writer = new FileWriter("risultato_gara.txt")) {
            writer.write("--- INIZIO GARA ---\n\n");
            System.out.println("\n--- INIZIO GARA ---");

            while (!garaFinita) {
                for (Cavallo c : cavalli) {
                    if (c.isAzzoppato()) continue; // salta se è azzoppato

                    // Possibilità 10% che si azzoppi
                    if (random.nextInt(100) < 10) {
                        c.setAzzoppato(true);
                        String msg = "❌ " + c.getNome() + " si è azzoppato e non può continuare!\n";
                        System.out.print(msg);
                        writer.write(msg);
                        continue;
                    }

                    c.corri(passo);
                    String msg = c.getNome() + " ha percorso " + c.getDistanzaPercorsa() + " metri.\n";
                    System.out.print(msg);
                    writer.write(msg);

                    if (c.getDistanzaPercorsa() >= lunghezzaPercorso) {
                        String vincitore = "\n🏆 Il vincitore è " + c.getNome() + "!\n";
                        System.out.println(vincitore);
                        writer.write(vincitore);
                        garaFinita = true;
                        break;
                    }
                }

                writer.write("---------------------------\n");
                System.out.println("---------------------------");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }

            writer.write("\n--- GARA TERMINATA ---\n");
            System.out.println("\n--- GARA TERMINATA ---");
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }

        input.close();
    }
}
