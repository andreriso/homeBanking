package cursoJava.homeBanking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class COAFService {

	private static String server = "http://2886795288-5555-cykoria01.environments.katacoda.com/movimentacoes";
	
	 public static void notificar(String informacao) throws ExecutionException, InterruptedException {
	        // Criando o HttpClient
	        HttpClient client = HttpClient.newHttpClient();
	        // String no formato Json que irá conter o corpo da requisição POST
	        String body = "{ 'informacao': '"+ informacao + "' }";
	        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
	        HttpRequest request = HttpRequest.newBuilder()
	                .POST(HttpRequest.BodyPublishers.ofString(body))
	                .uri(URI.create(server)).build();

	        // Enviando a requisição de forma assíncrona e armazenando o objeto de resposta em um CompletableFuture
	        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
	        // Utilizando o método thenAccept do CompletableFuture para imprimir o resultado da requisição assim que ela for retornada.
	        future.thenAccept(response -> {
	            System.out.println("Resposta do processamento: ");
	            System.out.println(String.format("Código de resposta: %s", response.statusCode()));
	            System.out.println(String.format("Retorno da requisição: %s", response.body()));
	        });

	        // Lógica para que o método não termine sua execução enquanto a resposta assíncrona não for retornada.
	        // O motivo disso é a possibilidade do método main ser encerrado antes de obter o retorno de processamento da requisição assíncrona.
	        while(!future.isDone()) {
	            System.out.println("Aguardando resposta da requisição!");
	            TimeUnit.MILLISECONDS.sleep(500);
	        } 
	}
	 
	    public static void listAll() throws ExecutionException, InterruptedException {
	        // Criando o HttpClient
	        HttpClient client = HttpClient.newHttpClient();
	        //Criando um HttpRequest do tipo Get e especificando a URI de consulta
	        HttpRequest request = HttpRequest.newBuilder().GET()
	        		.uri(URI.create(server))
	        		.header("Content-Type", "application/json")
	        		.build();
	        // Enviando a requisição de forma assíncrona e armazenando o objeto de resposta em um CompletableFuture
	        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
	        // Extraindo o retorno da requisição
	        String body = future.get().body();
	        // Imprimindo o resultado da mesma
	        System.out.println(body);
	    }	 
	
}
