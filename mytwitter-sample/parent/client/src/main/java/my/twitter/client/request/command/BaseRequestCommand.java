package my.twitter.client.request.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

public abstract class BaseRequestCommand implements RequestCommand {
	
	public static final String URL = "http://localhost:8080/core";
	
	protected WebClient client;
	
	public WebClient getWebClient(String path) {
	     List<Object> providers = new ArrayList<Object>();
	     providers.add( new JacksonJaxbJsonProvider() );	   
	     WebClient client = WebClient.create(URL, providers);
	     client.accept("application/json");
	     client = client.path(path);
	     return client;
	 }
	
	public String getBaseUrl() {
		return URL;
	}
	
	//This is used to make the command more testable
	void setClient(WebClient client) {
		this.client = client;
	}
	
}
