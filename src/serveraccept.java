import java.io.IOException;
import java.net.ServerSocket;

public class serveraccept {
    public static void main(String[] args) throws Exception {


        try(ServerSocket serverSocket=new ServerSocket(5007)) {
            while (true)
            {
                new MyServer(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
