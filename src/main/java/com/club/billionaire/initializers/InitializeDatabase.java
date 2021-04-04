package com.club.billionaire.initializers;

import com.club.billionaire.dao.BillionairesRepository;
import com.club.billionaire.domain.Billionaires;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InitializeDatabase implements ApplicationRunner {


    private BillionairesRepository billionairesRepository;

    private Forbes400Properties forbes400Properties;

    private final RestTemplate restTemplate;

    @Autowired
    public InitializeDatabase(BillionairesRepository billionairesRepository,
                              Forbes400Properties forbes400Properties, RestTemplate restTemplate) {
        this.billionairesRepository = billionairesRepository;
        this.forbes400Properties = forbes400Properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("refreshing DB...");
        fillDatabaseDuringStartup();
        log.info("DB refresh complete...");
    }

    private void fillDatabaseDuringStartup() throws JsonProcessingException {
        List<Billionaires> billionaires = new ArrayList<>();
        final ResponseEntity<String> forbes400ResponseEntity = restTemplate.getForEntity(forbes400Properties.buildEndPoint(), String.class);
        if (forbes400ResponseEntity.getStatusCode().isError()) {
            throw new InitializationFailedException("Issue with forbes 400 service");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(forbes400ResponseEntity.getBody());
        if (jsonNode.isArray()) {
            for (JsonNode eachBillionaire : jsonNode) {
                Billionaires bill = new Billionaires();
                final String billionaireString = eachBillionaire.toString();
                String name = JsonPath.read(billionaireString, "$.person.name");
                bill.setFirstName(name);
                bill.setLastName(name);
                String company = JsonPath.read(billionaireString, "$.source");
                bill.setCompany(company);
                Number netWorth = JsonPath.read(billionaireString, "$.finalWorth");
                bill.setWealth(netWorth + "B");
                billionaires.add(bill);
            }
        }
        billionairesRepository.saveAll(billionaires);
    }


}
