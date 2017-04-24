package com.microsoft.bing.messaging;

import java.nio.ByteBuffer;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import com.microsoft.bing.speech.Preferences;

public class MessagingComponent {
	private Preferences preferences;
	RemoteEndpoint.Async asyncRemote = null;
	byte[] streamStart = null;
    byte[] bodyStart = null;
    byte[] streamEnd = null;
	
	public MessagingComponent(Session session, Preferences preferences){
		this.preferences = preferences;
		asyncRemote = session.getAsyncRemote();
		
		streamStart = compileStreamStart().getBytes();
		bodyStart = compileBodyStart().getBytes();
		streamEnd = compileStreamEnd().getBytes();
	}
	
	public void sendTextMessage(){        	
    	asyncRemote.sendText(compileHeaders());
	}
	
	public void sendBinaryStart(byte[] buffer){
		byte[] sendBuffer = new byte[streamStart.length + buffer.length];
		System.arraycopy(streamStart, 0, sendBuffer, 0, streamStart.length);
		System.arraycopy(buffer, 0, sendBuffer, streamStart.length, buffer.length);   
		asyncRemote.sendBinary(ByteBuffer.wrap(sendBuffer,0,sendBuffer.length));
	}
	
	public void sendBinaryBody(byte[] buffer){
		byte[] sendBuffer = new byte[bodyStart.length + buffer.length];
		System.arraycopy(bodyStart, 0, sendBuffer, 0, bodyStart.length);
		System.arraycopy(buffer, 0, sendBuffer, bodyStart.length, buffer.length);   
		asyncRemote.sendBinary(ByteBuffer.wrap(sendBuffer,0,sendBuffer.length));
	}
	
	public void sendBinaryEnd(){
		asyncRemote.sendBinary(ByteBuffer.wrap(streamEnd,0,streamEnd.length));
	}
	
	private String compileHeaders(){
		String headers = "X-CU-ClientVersion: 4.0.150429\r\n"
				+ "X-CU-ConversationId: 7b3afda4a04c4a839975feef6feaec37\r\n"
				+ "X-CU-Locale: " + preferences.speechLanguage + "\r\n"
				+ "X-CU-LogLevel: 1\r\n"
				+ "X-CU-RequestId: 5877a7fe-2f27-41fa-949d-4886d1cecf27\r\n"
				+ "X-LOBBY-MESSAGE-TYPE: connection.context\r\n"
				+ "X-Search-IG: 0803341e1dbc41848a9dca958f179bcf\r\n";
		headers += "\r\n\r\n";
		//headers += "{'Groups':{'ConversationContext':{'Id':'ConversationContext','Info':{'PreferClientReco':false,'SystemAction':'Unknown','TurnId':'0'}},'LocalProperties':{'Id':'LocalProperties','Info':{'AudioSourceType':'SpeechApp','CurrentTime':'2017-03-09T11:04:39+0800','DrivingModeActive':false,'GeoLocation':'{\"Accuracy\":10.000000,\"Latitude\":0.000000,\"Longitude\":0.000000,\"Uri\":\"entity://GeoCoordinates\",\"Version\":\"1.0\"}','InCall':false,'InvocationSourceType':'SpeechApp','IsTextInput':false,'LockState':'Unlocked','ModeOfTravel':'Undefined','ProximitySensorState':'Uncovered','SpeechAppInitiatedRequest':false,'SystemInfo':'{\"Branch\":\"Windows\",\"CortanaEnabled\":false,\"DefaultOperatorName\":\"\",\"DeviceMake\":\"Microsoft\",\"DeviceModel\":\"PC\",\"LanguageCode\":\"1033\",\"Mkt\":\"en-US\",\"OsName\":\"Windows\",\"OsVersion\":\"10\",\"RegionalFormatCode\":\"en-US\",\"TimeZone\":\"China Standard Time\"}'},'Items':[]},'RecoProperties':{'Info':{'OptIn':false,'Scenario':'Unknown'}}},'OnScreenItems':[]}";
		
		return headers;
	}
	
	private String compileBodyStart(){
		StringBuffer sb = new StringBuffer();
		sb.append((char)0x00);
		sb.append(")X-LOBBY-MESSAGE-TYPE: audio.stream.body\r\n");
		
		sb.trimToSize();
		return sb.toString();
	}
	
	private String compileStreamStart(){
		StringBuffer sb = new StringBuffer();
		sb.append((char)0x00);
		sb.append((char)0xAB);
		sb.append("EncodingFormat: 1\r\n");
		sb.append("X-CU-RequestId: f5300e9e-56a9-45e0-a703-3fd0d1dc9ec2\r\n");
		sb.append("X-CU-UtteranceId: 7783db29-6bd2-4349-b6f4-640e60570ab4\r\n");
		sb.append("X-LOBBY-MESSAGE-TYPE: audio.stream.start\r\n");
			
		sb.trimToSize();
		return sb.toString();
	}
	
	private String compileStreamEnd(){
		StringBuffer sb = new StringBuffer();
		sb.append((char)0x00);
		sb.append("(X-LOBBY-MESSAGE-TYPE: audio.stream.end\r\n");
		
		sb.trimToSize();
		return sb.toString();
	}
}
