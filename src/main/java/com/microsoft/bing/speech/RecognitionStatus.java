package com.microsoft.bing.speech;

public enum RecognitionStatus {
    None(0),
    Success(200),
    NoMatch(201),
	InitialSilenceTimeout(202),
    PhraseSilenceTimeout(203),
	BabbleTimeout(204),
    Cancelled(205);
	
	RecognitionStatus(int i){
		
	}
}
