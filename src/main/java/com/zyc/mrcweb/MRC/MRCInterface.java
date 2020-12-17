package com.zyc.mrcweb.MRC;

public interface MRCInterface {
    //Read Answer from tmp.txt
    ResultSet getAnswer();
    //Write Answer to tmp.json and run Python Script
    void runMRCScript();
}
