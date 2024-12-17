package com.jesusfc.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author Jesús Fdez. Caraballo
 * Created on abr - 2024
 */
@AllArgsConstructor
@RequestMapping("/service_2")
@RestController
public class Service2Controller {


    @GetMapping("/get")
    public ResponseEntity<String> getService2() {
        return new ResponseEntity<>("El servicio 2 responde OK", HttpStatus.OK);
    }

    @GetMapping("/getFeign")
    public ResponseEntity<String> getServiceFeign() {
        return new ResponseEntity<>("El servicio Feign desde el servicio 2 -> responde OK", HttpStatus.OK);
    }
}
