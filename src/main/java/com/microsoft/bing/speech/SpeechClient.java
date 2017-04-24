package com.microsoft.bing.speech;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.microsoft.bing.messaging.MessagingComponent;

public class SpeechClient {
    private Session session = null;
    private Preferences preferences;
    private MessagingComponent messagingComponent = null;
    private List<IRecognitionResultHandler> actionList = new ArrayList<IRecognitionResultHandler>();
	
	public SpeechClient(Preferences preferences){
		try {
			this.preferences = preferences;
			String token = "Bearer " + preferences.authorizationProvider.GetAuthorizationToken();
			
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            SpeechClientEndpointConfigurator clientEndpointConfigurator = new SpeechClientEndpointConfigurator(token);
            ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().configurator(clientEndpointConfigurator).build();
            SpeechClientEndpoint sce = new SpeechClientEndpoint();
            session = container.connectToServer(sce, cec, preferences.serviceUri);
            
            messagingComponent = new MessagingComponent(session, this.preferences);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	private void subscribeTo(IRecognitionResultHandler recognitionHandler){
		this.actionList.add(recognitionHandler);
		
		session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String text) {
          	    //System.out.println("Received message from remote: " + text);
            	RecognitionResult rr = parseResult(text);
				actionList.forEach(action -> action.handleRecognitionResult(rr));
          	}
        });
		
		session.addMessageHandler(new MessageHandler.Whole<ByteBuffer>() {
			public void onMessage(ByteBuffer buffer) {
				byte[] temp = buffer.array();
          	    //System.out.println("Received message from remote: [" + temp.length+"]"+new String(temp));
				RecognitionResult rr = deserializeResult(temp);
				actionList.forEach(action -> action.handleRecognitionResult(rr));
          	}
		});
	}
	
	public void subscribeToPartialResult(){
		
	}
	
    public void subscribeToRecognitionResult(IRecognitionResultHandler recognitionHandler){
		this.subscribeTo(recognitionHandler);
	}
	
	public void recognize(SpeechInput speechInput){
        try {   
        	messagingComponent.sendTextMessage();           
            
		    byte[] buffer = new byte[1024];	
		    boolean firstPayload = true;
		    while ((speechInput.audio.read(buffer, 0, buffer.length)) != -1) {
            	if (firstPayload) {
            		messagingComponent.sendBinaryStart(buffer);
            		firstPayload = false;
            	} else {
            		messagingComponent.sendBinaryBody(buffer);
            	}
            }
            messagingComponent.sendBinaryEnd();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	private RecognitionResult deserializeResult(byte[] data){
		return BondSerializer.Deserialize(data, false);
	}
	
	private RecognitionResult parseResult(String text){
		return RecognitionTextParser.parseResult(text);
	}
}
