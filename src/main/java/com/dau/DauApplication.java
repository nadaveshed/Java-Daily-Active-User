package com.dau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toMap;

@SpringBootApplication
public class DauApplication {

    public static void main(String[] args) {
        SpringApplication.run(DauApplication.class, args);
    }
}
