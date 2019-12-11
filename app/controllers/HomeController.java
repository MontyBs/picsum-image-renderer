package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.inject.Inject;
import com.typesafe.config.Config;

import play.cache.SyncCacheApi;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import models.PicsumImage;
import services.ContentAPIService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private static final String IMAGE_SOURCE_CACHE = "image.source";
	private static final String IMAGE_LIST_CACHE = "image.list";
	private static final String IMAGE_CACHE_TTL_CONF = "picsum.cache";
	

    private final Config config;
    private final SyncCacheApi cache;
    private final WSClient wsClient;
	private final ContentAPIService service;

    @Inject
    public HomeController(Config config, SyncCacheApi cache, WSClient wsClient) {
        this.config = config;
        this.cache = cache;
        this.wsClient = wsClient;
		this.service = new ContentAPIService(wsClient, config);
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    /**
     * Returns The view that renders the image matching the url we fetch from picsum
     * @return Result
     */
    public CompletionStage<Result> test() {        
		// Get the cache configuration from config file
		Integer cacheTtl = Integer.decode(this.config.getString(IMAGE_CACHE_TTL_CONF));
		// Get currently cached image
		String cachedUrl = cache.get(IMAGE_SOURCE_CACHE);
		//If the data is not in cache yet or has expired
		if(cachedUrl == null || "".equals(cachedUrl)) {
			// Fetch URL with Service and stores it in cache
			cachedUrl = ((CompletableFuture<String>)service.getRandomImageURL()).join();
			cache.set(IMAGE_SOURCE_CACHE, cachedUrl, cacheTtl);
		}
		
		return CompletableFuture.completedFuture(ok(views.html.image.render(cachedUrl, cache.get(IMAGE_LIST_CACHE))));
    }
	
	/**
     * Returns The view that renders the images matching the urls we fetch from picsum
     * @return Result
     */
    public CompletionStage<Result> testMultiple() {
		// Get the cache configuration from config file
		Integer cacheTtl = Integer.decode(this.config.getString(IMAGE_CACHE_TTL_CONF));
		// Get currently cached images
		List<PicsumImage> cachedImages = cache.get(IMAGE_LIST_CACHE);
		
		//If the data is not in cache yet or has expired
		if(cachedImages == null || cachedImages.isEmpty()) {
			// Fetch it again and stores it
			cachedImages = ((CompletableFuture<List<PicsumImage>>)service.getImageList()).join();
			cache.set(IMAGE_LIST_CACHE, cachedImages, cacheTtl);
		}
		
		return CompletableFuture.completedFuture(ok(views.html.image.render(cache.get(IMAGE_SOURCE_CACHE), cachedImages)));
    }

}
