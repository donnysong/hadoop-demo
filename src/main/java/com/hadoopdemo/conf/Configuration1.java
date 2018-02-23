package com.hadoopdemo.conf;

import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Configuration1 {
    private static Logger logger = Logger.getLogger(Configuration1.class);
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.addResource("configuration-1.xml");
//        conf.writeXml(System.out);
        //assertThat(conf.get("color"), is("yellow"));
        System.setProperty("size", "16");
        logger.error(conf.get("color"));
        logger.error(conf.get("size"));
    }
}
