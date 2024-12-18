package com.swaggy7.licenseweb.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CustomServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream inputStream;

    public CustomServletInputStream(byte[] data) {
        this.inputStream = new ByteArrayInputStream(data);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public boolean isFinished() {
        return inputStream.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // Not implemented
    }
}