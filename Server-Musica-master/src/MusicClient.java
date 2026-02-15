import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MusicClient {

    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setSoTimeout(5000);

            // RICHIESTA VALIDA
            // "SONG:rock.mp3"
            // "MASHUP:rock.mp3,pop.mp3"
            String request = "MASHUP:rock.mp3,pop.mp3";

            byte[] sendBuffer = request.getBytes();
            InetAddress address = InetAddress.getByName(hostname);

            DatagramPacket packetToSend =
                    new DatagramPacket(sendBuffer, sendBuffer.length, address, port);

            socket.send(packetToSend);
            System.out.println("Richiesta inviata.");

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket packetReceived =
                    new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(packetReceived);

            String response = new String(
                    packetReceived.getData(),
                    0,
                    packetReceived.getLength()
            );

            System.out.println("Server dice: " + response);

        } catch (IOException e) {
            System.err.println("Errore o Timeout: " + e.getMessage());
        }
    }
}
