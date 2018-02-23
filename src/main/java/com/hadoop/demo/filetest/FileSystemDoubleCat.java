package com.hadoopdemo.filetest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileSystemDoubleCat {
    private static Logger logger = Logger.getLogger(FileSystemDoubleCat.class);

    public static void main(String []args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        FSDataInputStream in = null;
        in = fs.open(new Path(uri));
        try {
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(10);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }

    }
}
