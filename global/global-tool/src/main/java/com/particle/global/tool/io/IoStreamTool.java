package com.particle.global.tool.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangwei
 * @since 2018/10/31 20:00
 */
public class IoStreamTool {

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] inputStreamToByteArray(InputStream in) throws IOException {

        return inputStreamToByteArrayOutputStream(in).toByteArray();
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream inputStreamToByteArrayOutputStream(InputStream in) throws IOException {

        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return  swapStream;
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static ByteArrayInputStream inputStreamToByteArrayInputStream(InputStream in) throws IOException {

        return new ByteArrayInputStream(inputStreamToByteArray(in));
    }
}
