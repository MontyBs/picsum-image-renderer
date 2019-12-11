package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.concurrent.CompletionStage;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.Result;
import com.typesafe.config.Config;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import models.PicsumImage;


public class ContentAPIService {
	
	private static final String IMAGE_CACHE_TTL_CONF = "picsum.cache";
	private static final String IMAGE_SERVICE_URL_CONF = "picsum.domain";
	private static final String IMAGE_SERVICE_SIZE_CONF = "picsum.size";
	private static final String IMAGE_SERVICE_LIST_CONF = "picsum.list";
	private static final String LOCATION_HEADER = "location";
	
	private final Config config;
    private final WSClient client;
    private final String serviceUrl;
	
	public ContentAPIService(WSClient client, Config config) {
		this.client = client;
		this.config = config;
		this.serviceUrl = config.getString(IMAGE_SERVICE_URL_CONF);
	}
    
	/**
	 * Get URL of a random image from picsum service
	 *
	*/
	public CompletionStage<String> getRandomImageURL(){
		// Get our service url configuration
		String url = getSingleImageUrl();
		
		return client.url(url) // configure the call to url
			.setRequestTimeout(Duration.of(1000, ChronoUnit.MILLIS)) // put a timeout on the request
			.get() // execute the request
			.thenApply((response) -> {
				// Get the redirection to the random photo from the response headers
				Optional<String> location = response.getSingleHeader(LOCATION_HEADER);
				String result = "Error"; // by default, it's an error ...
				if(location.isPresent()){ // ... but if we have the right header ...
					result = serviceUrl + location.get(); // ...we return the given url
				}
				return result;
			});
	}
	
	/**
	 * Get a List of PicsumImage from picsum service
	 *
	*/
	public CompletionStage<List<PicsumImage>> getImageList(){
		// Get our service url configuration
		String url = getMultipleImageUrl();
		
		return client.url(url)
			.setRequestTimeout(Duration.of(1000, ChronoUnit.MILLIS))
			.get()
			.thenApply(response -> {
				String body = response.getBody();
				// Get the object Mapper that will deserialize the Json to our object
				ObjectMapper objectMapper = new ObjectMapper();
				try{
					// Gets the body and creates a List of our PicsumImage object
					List<PicsumImage> listImage = objectMapper.readValue(body, new TypeReference<List<PicsumImage>>(){});
					List<PicsumImage> evenIdImages = new ArrayList<>();
					// Stores the ones with even ids in a list
					for(PicsumImage img : listImage){
						if(img.getId()%2 == 0){
							evenIdImages.add(img);
						}
					}
					
					//Returns the list of objects
					return evenIdImages;
				} catch(IOException e) {
					System.out.println("Error while parsing the body!");
				}
				
				return new ArrayList<PicsumImage>();
			});
	}
	
	/**
	 * Get the url of the multiple image service
	 */
	private String getMultipleImageUrl(){
		String serviceList = config.getString(IMAGE_SERVICE_LIST_CONF);
		
		return serviceUrl + serviceList;
	}
	
	/**
	 * Get the url of the single image service
	 */
	private String getSingleImageUrl(){
		String serviceSize = config.getString(IMAGE_SERVICE_SIZE_CONF);
		
		return serviceUrl + serviceSize;
	}
	
}
