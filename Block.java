import java.security.MessageDigest;

/**
 * Rappresenta un singolo blocco della Blockchain Minimalista.
 * Ogni blocco contiene:
 * - hash corrente
 * - hash del blocco precedente
 * - payload
 * - timestamp
 * - nonce per Proof of Work
 * Implementa hashing SHA-256 e mining tramite Proof of Work.
 */
public class Block {
    /** Hash corrente del blocco */
    public String hash;
    /** Hash del blocco precedente */
    public String previousHash;
    /** Dati contenuti nel blocco */
    public String data;
    /** Timestamp di creazione */
    public long timeStamp;
    /** Numero utilizzato per il mining */
    public int nonce;

    /**
     * Costruttore del blocco.
     * @param data contenuto informativo
     * @param previousHash hash del blocco precedente
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    /**
     * Calcola l'hash SHA-256 del blocco.
     * inputHash = previousHash + timeStamp + nonce + data
     * @return hash crittografico in formato esadecimale
     */
    public String calculateHash() {
        String input = previousHash + timeStamp + nonce + data;
        return applySha256(input);
    }

    /**
     * Esegue il mining del blocco.
     * L'hash deve iniziare con un numero di zeri pari alla difficulty.
     * @param difficulty numero di zeri richiesti
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Blocco minato: " + hash);
    }

    /**
     * Applica l'algoritmo SHA-256 ad una stringa.
     * @param input stringa di input
     * @return hash SHA-256
     */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}