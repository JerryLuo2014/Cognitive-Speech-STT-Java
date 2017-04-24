package com.microsoft.bing.speech;

public class RecognitionPartialResult extends RecognitionPhrase {

	public RecognitionPartialResult(String displayText, String lexicalForm, Confidence confidence){
		super(displayText, lexicalForm, confidence);
	}
}
