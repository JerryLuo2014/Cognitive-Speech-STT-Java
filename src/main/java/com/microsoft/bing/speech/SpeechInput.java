package com.microsoft.bing.speech;

import java.io.InputStream;

public class SpeechInput {

	public InputStream audio;

    public RequestMetadata requestMetadata;

    public SpeechInput(InputStream audio, RequestMetadata requestMetadata) throws Exception {
        if (audio == null)
            throw new Exception("Audio is null");
      
//        if (requestMetadata == null)
//            throw new Exception("RequestMetadata is null");
        
        this.audio = audio;
        this.requestMetadata = requestMetadata;
    }
}
