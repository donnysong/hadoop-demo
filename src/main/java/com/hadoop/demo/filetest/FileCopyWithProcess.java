package com.hadoop.demo.filetest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
//bin/hadoop --loglevel DEBUG com.hadoopdemo.filetest.FileCopyWithProcess test.txt hdfs://localhost:9003/user/root/input/test.txt
//bin/hadoop fs -cat input/test.txt
public class FileCopyWithProcess {
    private static Logger logger = Logger.getLogger(FileCopyWithProcess.class);

    public static void main(String []args) throws IOException {
        String localSrc = args[0];
        String dest = args[1];

        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dest), conf);
        OutputStream out = fs.create(new Path(dest), new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });

        IOUtils.copyBytes(in, out, 4096, true);
    }
}
