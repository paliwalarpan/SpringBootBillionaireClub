package com.club.billionaire.dao;

import com.club.billionaire.domain.Billionaires;
import org.springframework.data.repository.CrudRepository;

public interface BillionairesRepository extends CrudRepository<Billionaires,Long> {

}