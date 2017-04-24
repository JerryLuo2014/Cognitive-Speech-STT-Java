package com.microsoft.bing.speech;

public class ApplicationMetadata {

	public String name;
    public String version;

    public ApplicationMetadata(String name, String version) throws Exception {
        if (name == null)
            throw new Exception("Application name is null");
        
        if (version == null)
            throw new Exception("Application version is null");

        this.name = name;
        this.version = version;
    }
}
