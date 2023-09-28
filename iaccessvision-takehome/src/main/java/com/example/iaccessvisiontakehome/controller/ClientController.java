package com.example.iaccessvisiontakehome.controller;

import com.example.iaccessvisiontakehome.entity.Client;
import com.example.iaccessvisiontakehome.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    public static final String REQUEST_MAPPING_REGISTER = "/register";
    public static final String REQUEST_MAPPING_FIND = "/find";
    public static final String REQUEST_MAPPING_DELETE = "/delete";
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(REQUEST_MAPPING_REGISTER)
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.addClient(client), HttpStatus.CREATED);
    }

    @GetMapping(REQUEST_MAPPING_FIND)
    public ResponseEntity<List<String>> getIps(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "environment", required = false) String environment,
            @RequestParam(value = "application", required = false) String application) {
        return new ResponseEntity<>(clientService.getIps(name, environment, application), HttpStatus.OK);
    }

    @DeleteMapping(REQUEST_MAPPING_DELETE)
    public ResponseEntity<?> deleteClientIp(@RequestParam("ipaddress") String ipAddress) {
        clientService.delete(ipAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
