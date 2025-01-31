package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.exception.BillNotFoundException;
import com.finance.model.Bill;
import com.finance.service.BillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bill")
public class BillController {

	@Autowired
	private BillService billService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getBillsByUserId(@PathVariable("userId")Long userId){
		return new ResponseEntity<>(billService.getBillsByUserId(userId),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addBill(@RequestBody @Valid Bill bill){
		return new ResponseEntity<>(billService.addBill(bill),HttpStatus.OK);
	}
	
	@PutMapping("/{billId}")
	public ResponseEntity<?> updateBill(@PathVariable("billId") Long billId,@RequestBody @Valid Bill bill) throws BillNotFoundException{
		return new ResponseEntity<>(billService.updateBill(billId, bill),HttpStatus.OK);
	}
	
	@DeleteMapping("/{billId}")
	public ResponseEntity<?> deleteBill(@PathVariable("billId") Long billId) throws BillNotFoundException{
		return new ResponseEntity<>(billService.deleteBill(billId),HttpStatus.OK);
	}
}
