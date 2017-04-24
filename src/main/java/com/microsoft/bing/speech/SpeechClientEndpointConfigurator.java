package com.microsoft.bing.speech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.HandshakeResponse;
import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class SpeechClientEndpointConfigurator extends Configurator {
    private String token;
	
	public SpeechClientEndpointConfigurator(String token){
		this.token = token;
	}
	
	public void afterResponse(HandshakeResponse hr){
		
	}
	
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
    	 
    }
    
    public void beforeRequest(Map<String, List<String>> headers) {
    	List<String> tokenList = new ArrayList<String>();
    	tokenList.add(token);
    	headers.put("Authorization", tokenList);
    }
}
