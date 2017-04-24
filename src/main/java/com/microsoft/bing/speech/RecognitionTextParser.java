package com.microsoft.bing.speech;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class RecognitionTextParser {

	public static RecognitionResult parseResult(String text){
		int typePosition;
		typePosition = text.indexOf("X-CU-ResultType:");
		String tempType = text.substring(typePosition);
		typePosition = tempType.indexOf("\r\n");
		String responseType = tempType.substring(16, typePosition);	
		
		int statusCodePosition;
		statusCodePosition = text.indexOf("X-Lobby-ServiceResponseStatusCode:");
		String tempStatusCode = text.substring(statusCodePosition);
		statusCodePosition = tempStatusCode.indexOf("\r\n");
		String statusCode = tempStatusCode.substring(34, statusCodePosition);	
		
		List<RecognitionPhrase> list = new ArrayList<RecognitionPhrase>();		
		String recognitionXML = text.substring(text.indexOf("<?xml"));
		try{
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder(); 
		    Document doc = db.parse(new ByteArrayInputStream(recognitionXML.getBytes()));
		    
		    //Should handle returned recognition error
		    if("IntermediateResult".equals(responseType)){
		        String displayText = doc.getElementsByTagName("Entry").item(1).getFirstChild().getTextContent();
		        RecognitionPhrase recognitionPhrase = new RecognitionPhrase(displayText, null, null);
		        list.add(recognitionPhrase);
		    }
		    if("PhraseResult".equals(responseType)){
		    	if("200".equals(statusCode)){
		    		String displayText = doc.getElementsByTagName("DisplayText").item(0).getTextContent();
			        String lexicalForm = doc.getElementsByTagName("LexicalForm").item(0).getTextContent();
			        RecognitionPhrase recognitionPhrase = new RecognitionPhrase(displayText, lexicalForm, null);
			        list.add(recognitionPhrase);
		    	}else{
		    		String displayText = doc.getElementsByTagName("StackTrace").item(0).getTextContent();
			        RecognitionPhrase recognitionPhrase = new RecognitionPhrase(displayText, null, null);
			        list.add(recognitionPhrase);
		    	}
		        
		    }		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return new RecognitionResult(RecognitionStatus.Success, list);
	}
}
