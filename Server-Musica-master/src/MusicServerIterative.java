import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MusicServerIterative {

    public static void main(String[] args) {

        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket(port)) {

            System.out.println("Server UDP avviato sulla porta " + port);

            while (true) {

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String request = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength()
                ).trim();

                System.out.println("Richiesta ricevuta: " + request);

                String response;

                if (request.startsWith("SONG:")) {

                    String song = request.substring(5);
                    response = "Invio canzone: " + song;

                } else if (request.startsWith("MASHUP:")) {

                    String data = request.substring(7);
                    String[] songs = data.split(",");
                    response = "Mashup creato: " + songs[0] + " + " + songs[1];

                } else {
                    response = "Richiesta non valida";
                }

                byte[] sendBuffer = response.getBytes();

                DatagramPacket sendPacket =
                        new DatagramPacket(sendBuffer, sendBuffer.length,
                                clientAddress, clientPort);

                socket.send(sendPacket);
                System.out.println("Risposta inviata.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
