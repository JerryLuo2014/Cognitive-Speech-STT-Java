package com.microsoft.bing.speech;

import java.util.UUID;

public class RequestMetadata {

	public UUID identifier;

    public DeviceMetadata deviceMetadata;

    public ApplicationMetadata applicationMetadata;

    public String serviceName;

    public RequestMetadata(UUID requestIdentifier, DeviceMetadata deviceMetadata, ApplicationMetadata applicationMetadata, String serviceName) throws Exception {
        if (requestIdentifier == null)
            throw new Exception("requestIdentifier is null");
        
        if (deviceMetadata == null)
            throw new Exception("deviceMetadata is null");
        
        if (applicationMetadata == null)
            throw new Exception("applicationMetadata is null");
        
        if (serviceName == null)
            throw new Exception("serviceName is null");
        
        this.identifier = requestIdentifier;
        this.deviceMetadata = deviceMetadata;
        this.applicationMetadata = applicationMetadata;
        this.serviceName = serviceName;
    }
}
