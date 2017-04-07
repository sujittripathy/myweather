package com.weather.app.myweather;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
        new SpringApplication(App.class).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("called run.."+strings);
    }
}
