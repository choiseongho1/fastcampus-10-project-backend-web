package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        new CustomWebApplicationServer(8080).start();
    }
}