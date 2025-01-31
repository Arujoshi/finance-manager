package com.finance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.finance.dto.BillDTO;
import com.finance.exception.BillNotFoundException;
import com.finance.mappper.BillMapper;
import com.finance.model.Bill;
import com.finance.model.UserInfo;
import com.finance.repository.BillRepository;
import com.finance.repository.UserInfoRepository;

import jakarta.transaction.Transactional;


@Service
public class BillService {


    @Autowired
    private BillRepository billRepository;
    
    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Add a new bill
    public BillDTO addBill(Bill bill) {
    	UserInfo user = userRepository.findById(bill.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert DTO to entity and set the user
        bill.setUser(user);
        
        // Return the saved bill as DTO
        return BillMapper.toDTO(billRepository.save(bill));
    	
    }
    
    public List<BillDTO> getBillsByUserId(Long userId){
    	List<Bill> bills=billRepository.findByUserUserId(userId);
    	return bills.stream().map(BillMapper::toDTO).collect(Collectors.toList());
    	
    }
    

    // Update an existing bill
    public BillDTO updateBill(Long billId, Bill billDetails) throws BillNotFoundException {
        Bill existingBill = billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException("Bill not found with id "+billId));

        existingBill.setName(billDetails.getName());
        existingBill.setAmount(billDetails.getAmount());
        existingBill.setDueDate(billDetails.getDueDate());
        return BillMapper.toDTO(billRepository.save(existingBill));
    }

    public BillDTO deleteBill(Long billId) throws BillNotFoundException {
    	Bill existingBill = billRepository.findById(billId)
                .orElseThrow(() -> new BillNotFoundException("Bill not found with id "+billId));
    	billRepository.deleteById(billId);
    	return BillMapper.toDTO(existingBill);
    }
    
    @Scheduled(cron = "0 0 9 * * ?")//Runs everyday  at 9AM
    @Transactional
    public void sendUpcomingBillReminders() {
    	
    	LocalDate dueDate = LocalDate.now().plusDays(3);
        List<Bill> bills = billRepository.findBillsDueInNextDays(dueDate); // Find bills due in the next 3 days

        for (Bill bill : bills) {
        	System.out.print(bill+"sending mail");            // Send email reminder
            String message = "Reminder: Your bill for " + bill.getName() + " is due on " + bill.getDueDate();
            emailService.sendEmail(bill.getUser().getEmail(), "Upcoming Bill Reminder", message);
        }
    }
}
