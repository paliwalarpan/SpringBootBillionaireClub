package com.club.billionaire.service;

import com.club.billionaire.domain.Billionaires;

import java.util.List;

public interface BillionairesService {
	
	public List<Billionaires> listAll() ;
	public boolean deleteBillionaire(Long bilionaireiD);
	public Billionaires createBillionaires(Billionaires billionaires);
	public Billionaires updateBillionaire(Billionaires billionaires);
	public Billionaires getBillionaire(Long bilionaireiD);
}
