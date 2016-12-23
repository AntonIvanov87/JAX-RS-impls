import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientMain {

  private static final int concurrency = 32;
  private static final HttpHost httpHost = new HttpHost("localhost", 8081, "http");
  private static final HttpRequest httpRequest = new HttpGet("/json?str=foo&id=1&id=2&id=3&id=4&id=5");

  public static void main(String[] args) throws InterruptedException {

    Worker[] workers = new Worker[concurrency];
    for (int i = 0; i < concurrency; i++) {
      Worker worker = new Worker();
      workers[i] = worker;
      new Thread(worker).start();
    }

    System.out.println("responses / sec");
    long[] prevWorkersRequests = new long[concurrency];
    while (true) {
      Thread.sleep(1000);
      long requestsRate = 0;
      for (int i = 0; i < concurrency; i++) {
        long curWorkerRequests = workers[i].requests;
        requestsRate += (curWorkerRequests - prevWorkersRequests[i]);
        prevWorkersRequests[i] = curWorkerRequests;
      }
      System.out.println(requestsRate);
    }
  }

  static class Worker implements Runnable {

    volatile long requests = 0;

    @Override
    public void run() {
      try (CloseableHttpClient httpClient = HttpClients.createMinimal()) {
        while (true) {
          HttpResponse response = httpClient.execute(httpHost, httpRequest);

          if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("non 200 response: " + response);
          }
          EntityUtils.consumeQuietly(response.getEntity());

          requests++;
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

}
