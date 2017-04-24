package com.microsoft.bing.speech;

import java.net.URI;

public class Preferences {

	public String speechLanguage;
    public URI serviceUri;
    public IAuthorizationProvider authorizationProvider;
    public boolean enableAudioBuffering = true;
    
    public Preferences(String speechLanguage, URI serviceUri, IAuthorizationProvider authorizationProvider, boolean enableAudioBuffering) throws Exception {
        if (speechLanguage == null)
            throw new Exception("speechLanguage is null");
        
        if (serviceUri == null)
            throw new Exception("serviceUri is null");
        
        if (authorizationProvider == null)
            throw new Exception("authorizationProvider is null");
      
        this.speechLanguage = speechLanguage;
        this.serviceUri = serviceUri;
        this.authorizationProvider = authorizationProvider;
        this.enableAudioBuffering = enableAudioBuffering;
    }
}
