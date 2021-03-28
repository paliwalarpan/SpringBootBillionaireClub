package com.club.billionaire.controller;

import com.club.billionaire.domain.Billionaires;
import com.club.billionaire.service.BillionairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BillionaireController {
	private static final Logger logger =
			LoggerFactory.getLogger(BillionaireController.class);

    private BillionairesService billionairesService;

    @Autowired
    public BillionaireController(BillionairesService billionairesService) {
        this.billionairesService = billionairesService;
    }

    @GetMapping(path = "/billionaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Billionaires> findAll() {
    	logger.info("findAll method ");
        return billionairesService.listAll();
    }

    @DeleteMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
    	logger.info("deleting record for - id - {}", id);
        billionairesService.deleteBillionaire(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    @PostMapping(path = "/billionaire", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody Billionaires billionaires) {
    	logger.info("billionaire record saving.");
    	logger.debug("save method, First Name - {}", billionaires.getFirstName());
    	logger.debug("save method Last Name - {}", billionaires.getLastName());
    	logger.debug("save method Comapny - {}", billionaires.getCompany());
    	logger.debug("save method Wealth - {}", billionaires.getWealth());
        
    	billionairesService.createBillionaires(billionaires);
        return "created";
    }

    @PutMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable long id, @RequestBody Billionaires billionaires) {
    	logger.info("billionaire record Updaing.");
    	logger.debug("update method, First Name - {}", billionaires.getFirstName());
    	logger.debug("update method Last Name - {}", billionaires.getLastName());
    	logger.debug("update method Comapny - {}", billionaires.getCompany());
    	logger.debug("update method Wealth - {}", billionaires.getWealth());
        
    	billionaires.setId(id);
        billionairesService.updateBillionaire(billionaires);
        return "updated";
    }
    
    @GetMapping(path = "/billionaire/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Billionaires fetchBillionaire(@PathVariable long id) {
    	logger.info("fetchBillionaire method for - id - {}", id);
        return billionairesService.getBillionaire(id);
    }

}
