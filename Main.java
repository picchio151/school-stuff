public class Main {

    public static void main(String[] args) {

        int difficulty = 4; // numero di zeri richiesti
        Blockchain blockchain = new Blockchain(difficulty);

        blockchain.addBlock("Transazione A -> B : 10€");
        blockchain.addBlock("Transazione B -> C : 5€");
        blockchain.addBlock("Transazione C -> D : 2€");

        System.out.println("\nStato della Blockchain:");
        blockchain.printChain();

        System.out.println("\nVerifica integrità:");
        System.out.println("Blockchain valida? " + blockchain.isChainValid());

        // Simulazione manomissione
        System.out.println("\nManomissione dati...");
        blockchain.chain.get(1).data = "Transazione modificata!!!";

        System.out.println("Blockchain valida dopo modifica? "
                + blockchain.isChainValid());
    }
}