package com.microsoft.bing.speech;

import java.util.List;

public class RecognitionResult {
    public RecognitionStatus recognitionStatus;
    public List<RecognitionPhrase> phrases;

    public RecognitionResult(RecognitionStatus recognitionStatus, List<RecognitionPhrase> phrases) {
        this.recognitionStatus = recognitionStatus;
        this.phrases = phrases;
    }
}
