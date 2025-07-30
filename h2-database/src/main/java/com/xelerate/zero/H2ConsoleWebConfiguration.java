package com.xelerate.zero;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
public class H2ConsoleWebConfiguration {

    @Autowired
    private ProductRepository productRepository; // Inject the repository


    private Server webServer;

    @EventListener(ContextRefreshedEvent.class)
    public void startH2Console() throws SQLException {
        this.webServer = Server.createWebServer(
                "-webAllowOthers", // Allow external connections (be careful in prod!)
                "-webPort", "8082" // Use a different port than your WebFlux app (e.g., 8082)
        ).start();
        System.out.println("H2 Console started at: " + webServer.getURL());


        // Add a simple database operation to ensure connection is active
        if (productRepository != null) {
            productRepository.save(new Product("Test Product 1"));
            productRepository.save(new Product("Test Product 2"));
            System.out.println("Added 2 products to H2 database.");
            productRepository.findAll().forEach(p -> System.out.println("Found product: " + p.getName()));
        } else {
            System.err.println("ProductRepository not injected, database interaction skipped.");
        }
    }

    @EventListener(ContextClosedEvent.class)
    public void stopH2Console() {
        if (this.webServer != null) {
            this.webServer.stop();
            System.out.println("H2 Console stopped.");
        }
    }
}