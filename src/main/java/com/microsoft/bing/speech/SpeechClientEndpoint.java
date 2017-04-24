package com.microsoft.bing.speech;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class SpeechClientEndpoint extends Endpoint {

	@OnOpen
	public void onOpen(Session session, EndpointConfig ec) {
        //System.out.println("Connected to endpoint: " + session.getAsyncRemote());        
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
    
    @OnClose
    public void onClose(Session session, CloseReason closeReason){
    	//System.out.println("The session is closed. Close reason:"+closeReason.getReasonPhrase());
    }
}
