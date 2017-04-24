package com.microsoft.bing.speech;

public class RecognitionPhrase {
    public String displayText;
    public String lexicalForm;
    public Confidence confidence;
 //   public ulong mediaTime;
 //   public uint mediaDuration { get; private set; }

    public RecognitionPhrase(String displayText, String lexicalForm, Confidence confidence) {
    	this.displayText = displayText;
    	this.lexicalForm = lexicalForm;
    	this.confidence = confidence;
    }
}
