package com.south.gobangserver;

import com.south.gobangserver.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GobangServerApplication {
    public static void main(String[] args) {
        new Server().start();
        SpringApplication.run(GobangServerApplication.class, args);
    }

}
