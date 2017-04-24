package com.microsoft.bing.speech;

public class DeviceMetadata {

	public enum DeviceType {Near, Far};

    public enum DeviceFamily {Unknown, Desktop, Laptop, Tablet, Mobile, HolographicOrVR, Xbox, Whiteboard, Automotive, IotDevice};

    public enum OsName {Unknown, Android, IOS, Windows, WindowsMobile, Linux};

    public String OsVersion;

    public String make;

    public String model;

    public enum NetworkType {Unknown, Wifi, Ethernet, CellGPRS, CellEdge, CellWCDMA, CellHSDPA, CellHSUPA, Cell1xRTT, CellEVDO0, CellEVDOA, CellEVDOB, CellEHRPD, CellLTE};

    public DeviceMetadata(DeviceType type, DeviceFamily family, NetworkType networkType, OsName osName, String osVersion, String make, String model) throws Exception {
        if (osVersion == null)
            throw new Exception("OsVersion is null");
        
        if (make == null)
            throw new Exception("make is null");
        
        if (model == null)
            throw new Exception("model is null");
      
        //this.Type = type;
        //this.Family = family;
        //this.OsName = osName;
        this.OsVersion = osVersion;
        //this.NetworkType = networkType;
        this.make = make;
        this.model = model;
    }
}
