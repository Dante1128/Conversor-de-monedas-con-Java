import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
            JsonObject rates = gson.fromJson(json, JsonObject.class).getAsJsonObject("conversion_rates");
            while (true){
                System.out.println("*******************************************");
                System.out.println("Sea bienvenido/a l Convertidor de Moneda =]");
                System.out.println("1) Dolar =>> Peso argentino");
                System.out.println("2) Peso argentino ==> Dolar");
                System.out.println("3) Dolar =>> Real brasileno");
                System.out.println("4) Real brasileno =>> Dolar");
                System.out.println("5) Dolar =>> Peso colombiano");
                System.out.println("6) Peso colombiano =>> Dolar");
                System.out.println("7) Salir");
                System.out.println("**********************************************");

                System.out.println("Seleccione una opcion: ");
                int opcion = lectura.nextInt();

                if (opcion == 7){
                    break;
                }

                System.out.println("Escriba la cantidad a convertit: ");
                double cantidad = lectura.nextDouble();

                double resultado = 0;
                switch (opcion){
                    case 1:
                        resultado = cantidad * rates.get("ARS").getAsDouble();
                        break;
                    case 2:
                        resultado = cantidad / rates.get("ARS").getAsDouble();
                        break;
                    case 3:
                        resultado =  cantidad * rates.get("BRL").getAsDouble();
                        break;
                    case 4:
                        resultado = cantidad / rates.get("BRL").getAsDouble();
                        break;
                    case 5:
                        resultado = cantidad / rates.get("COP").getAsDouble();
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        continue;
                }
                System.out.println("Resultado :\n " + resultado );


            }
        } catch (IOException  | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
