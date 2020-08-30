package com.club.billionaire.service;

import com.club.billionaire.dao.BillionairesRepository;
import com.club.billionaire.domain.Billionaires;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillionairesServiceImpl implements BillionairesService {

	@Autowired
	private BillionairesRepository billionairesRepository;

	public List<Billionaires> listAll() {
		return (List<Billionaires>) billionairesRepository.findAll();
	}

	@Override
	public boolean deleteBillionaire(Long bilionaireiD) {
		Billionaires billionaire = new Billionaires(bilionaireiD);
		 billionairesRepository.delete(billionaire);
		 return  true;
	}

	@Override
	public Billionaires createBillionaires(Billionaires billionaires) {
		return billionairesRepository.save(billionaires);
	}

	@Override
	public Billionaires updateBillionaire(Billionaires billionaires) {
		return billionairesRepository.save(billionaires);
	}

	@Override
	public Billionaires getBillionaire(Long bilionaireiD) {
		return billionairesRepository.findById(bilionaireiD).get();
	}

}
