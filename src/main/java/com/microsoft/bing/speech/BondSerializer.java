package com.microsoft.bing.speech;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BondSerializer {
	
	private enum MessageType{
		Partial,
		Phrase,
		End,
		None,
	}
	
	public static RecognitionResult Deserialize(byte[] data, boolean unmarshal){
		ByteBuffer bbuffer = ByteBuffer.wrap(data);
		
		byte first = bbuffer.get(0);
		byte second = bbuffer.get(1);		
		BondSerializer.MessageType messageType = checkMessageType(first, second);
		switch(messageType){
		    case Partial:
			    return null;
		    case Phrase:
			    return DeserializePhrases(bbuffer);
			default:
				return null;
		}	
	}
	
	private static BondSerializer.MessageType checkMessageType(byte first, byte second){
		if(first == 0){
			if(second == (char)0xad){
				return BondSerializer.MessageType.Partial;
			}else if(second == (char)0xb6){
				return BondSerializer.MessageType.Phrase;
			}else if(second == (char)0xb3){
				return BondSerializer.MessageType.End;
			}
		}
		
		return BondSerializer.MessageType.None;
	}
	
	private static RecognitionResult DeserializePhrases(ByteBuffer bbuffer){
		RecognitionPhrase recognitionPhease;
		
		ByteBuffer tempbuffer = ByteBuffer.allocate(bbuffer.limit());
		bbuffer.position(2);		
		do{
			try{
			    byte b = bbuffer.get();		
			}catch(BufferUnderflowException be){
				break;
			}
		}while(true);
		
		List<RecognitionPhrase> list = new ArrayList<RecognitionPhrase>();
		return new RecognitionResult(RecognitionStatus.Success, list);
	}
}
