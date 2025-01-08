import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        while (true){
            System.out.println("Escriba la cantidad a comvertir: ");
            var conversion = lectura.nextInt();
            if (conversion == 9){
                break;
            }
            String direccion = "https://v6.exchangerate-api.com/v6/f374f2b6943319cebd94ef3a/latest/USD";

            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = client
                        .send(request,
                                HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
