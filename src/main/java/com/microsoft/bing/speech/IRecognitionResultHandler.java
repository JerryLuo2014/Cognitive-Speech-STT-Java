package com.microsoft.bing.speech;

@FunctionalInterface
public interface IRecognitionResultHandler {

	public void handleRecognitionResult(RecognitionResult rr);
}
