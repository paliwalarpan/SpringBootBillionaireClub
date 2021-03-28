package com.club.billionaire.initializers;

import com.club.billionaire.dao.BillionairesRepository;
import com.club.billionaire.domain.Billionaires;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeDatabase {

    @Autowired
    private BillionairesRepository billionairesRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabaseDuringStartup() throws JsonProcessingException {
        List<Billionaires> billionaires = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> forEntity = restTemplate.getForEntity("https://forbes400.herokuapp.com/api/forbes400?limit=50", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(forEntity.getBody());
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
                bill.setWealth(String.valueOf(netWorth) + "B");
                billionaires.add(bill);
                System.out.println("alll");

            }
        }
        billionairesRepository.saveAll(billionaires);
     /*   System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getBody());*/
    }

}
