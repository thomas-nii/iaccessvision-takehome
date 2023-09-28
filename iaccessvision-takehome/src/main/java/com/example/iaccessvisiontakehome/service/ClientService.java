package com.example.iaccessvisiontakehome.service;

import com.example.iaccessvisiontakehome.constants.ApplicationEnum;
import com.example.iaccessvisiontakehome.constants.EnvironmentEnum;
import com.example.iaccessvisiontakehome.entity.Client;
import com.example.iaccessvisiontakehome.exception.InvalidFormException;
import com.example.iaccessvisiontakehome.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class ClientService {
    private static final String IP_PATTERN = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        if (isFormValid(client))
            return clientRepository.save(client);
        throw new InvalidFormException();
    }

    private boolean isFormValid(Client client) {
        return isValidApplication(client.getApplication()) &&
                isIpValid(client.getIpaddress()) &&
                isEnvironmentValid(client.getEnvironment());
    }

    private boolean isValidApplication(String application) {
        return Objects.equals(application, ApplicationEnum.APPLICATION_ONE.getName()) ||
                Objects.equals(application, ApplicationEnum.APPLICATION_TWO.getName());
    }

    private boolean isIpValid(String input) {
        return Pattern.compile(IP_PATTERN).matcher(input).matches();
    }

    private boolean isEnvironmentValid(String environment) {
        return environment.equals(EnvironmentEnum.STAGE.getName()) ||
                environment.equals(EnvironmentEnum.DEVELOPMENT.getName()) ||
                environment.equals(EnvironmentEnum.PRODUCTION.getName());
    }

    public List<String> getIps(String name, String environment, String application) {
        if (name != null && environment != null && application != null) {
            return clientRepository.findDistinctIpsByNameAndEnvironmentAndApplication(name, environment, application);
        } else if (name != null && environment != null) {
            return clientRepository.findDistinctIpsByNameAndEnvironment(name, environment);
        } else if (name != null && application != null) {
            return clientRepository.findDistinctIpaddressByNameAndApplication(name, application);
        } else if (environment != null && application != null) {
            return clientRepository.findDistinctIpaddressByEnvironmentAndApplication(environment, application);
        }
        return clientRepository.findDistinctIpsByNameOrApplicationOrEnvironment(name, environment, application);
    }

    @Transactional
    public void delete(String ipAddress) {
        if (isIpValid(ipAddress))
            clientRepository.deleteByIpaddress(ipAddress);
    }
}
