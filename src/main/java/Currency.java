import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by A&M on 07.02.2019.
 */
public class Currency {
    public static void main(String[] args) {

        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://api.nbp.pl/api/exchangerates/rates/a/usd/");
            ClientResponse webResponse = webResource.accept("application/json").get(ClientResponse.class);

            if (webResponse.getStatus() != 200) {
                throw new RuntimeException("Error http.. " + webResponse.getStatus());
            }

            String getJsonResponse = webResponse.getEntity(String.class);
            System.out.println(getJsonResponse);

            ObjectMapper mapper = new ObjectMapper();
            Kursy kursy = mapper.readValue(getJsonResponse, Kursy.class);

            System.out.println(kursy.getCurrency());
            System.out.println(kursy.getTable());
            System.out.println(kursy.getCode());
            System.out.println(kursy.getRates().get(0).getNo());
            System.out.println(kursy.getRates().get(0).getEffectiveDate());
            System.out.println(kursy.getRates().get(0).getMid());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
