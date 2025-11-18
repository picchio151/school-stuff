import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezzaPercorso = input.nextInt();
        input.nextLine(); // pulisce buffer

        // FileChooser per scegliere dove salvare il file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Scegli dove salvare il risultato della gara");
        chooser.setSelectedFile(new java.io.File("risultato_gara.txt"));
        chooser.setFileFilter(new FileNameExtensionFilter("File di testo", "txt"));

        int scelta = chooser.showSaveDialog(null);
        if (scelta != JFileChooser.APPROVE_OPTION) {
            System.out.println("Salvataggio annullato. Programma terminato.");
            return;
        }

        String filePath = chooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".txt")) {
            filePath += ".txt";
        }

        // ArrayList cavalli
        ArrayList<Cavallo> cavalli = new ArrayList<>();
        cavalli.add(new Cavallo("caaanta "));
        cavalli.add(new Cavallo("sannntoni"));
        cavalli.add(new Cavallo("giuuulio"));
        cavalli.add(new Cavallo("aaalex"));
        cavalli.add(new Cavallo("piciooo"));

        boolean garaFinita = false;
        int passo = 5;

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("--- INIZIO GARA ---\n\n");
            System.out.println("\n--- INIZIO GARA ---");

            while (!garaFinita) {
                for (Cavallo c : cavalli) {
                    if (c.isAzzoppato()) continue;

                    // 10% di probabilità di azzoppamento

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
