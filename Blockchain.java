import java.util.ArrayList;

/**
 * Rappresenta la Blockchain Minimalista.
 * Gestisce:
 * - Creazione Genesis Block
 * - Aggiunta nuovi blocchi
 * - Validazione integrità della catena
 */
public class Blockchain {
    /** Lista sequenziale dei blocchi */
    public ArrayList<Block> chain;
    /** Livello di difficoltà per il mining */
    public int difficulty;
    /**
     * Costruttore della Blockchain.
     * @param difficulty numero di zeri richiesti per il Proof of Work
     */
    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        chain.add(createGenesisBlock());
    }

    /**
     * Crea il Genesis Block.
     * @return primo blocco della catena
     */
    private Block createGenesisBlock() {
        Block genesis = new Block("Genesis Block", "0");
        genesis.mineBlock(difficulty);
        return genesis;
    }

    /**
     * Restituisce l'ultimo blocco della catena.
     * @return ultimo blocco
     */
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * Aggiunge un nuovo blocco alla blockchain.
     * @param data contenuto del blocco
     */
    public void addBlock(String data) {
        Block newBlock = new Block(data, getLatestBlock().hash);
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    /**
     * Verifica l'integrità dell'intera blockchain.
     * @return true se valida, false altrimenti
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            if (!current.hash.equals(current.calculateHash())) {
                return false;
            }
            if (!current.previousHash.equals(previous.hash)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Stampa la blockchain su console.
     */
    public void printChain() {
        for (Block block : chain) {
            System.out.println("Data: " + block.data);
            System.out.println("Hash: " + block.hash);
            System.out.println("Previous: " + block.previousHash);
            System.out.println("Nonce: " + block.nonce);
        }
    }
}