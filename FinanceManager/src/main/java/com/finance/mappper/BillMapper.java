package com.finance.mappper;

import com.finance.dto.BillDTO;
import com.finance.model.Bill;

public class BillMapper {

	public static BillDTO toDTO(Bill bill) {
		BillDTO billDTO=new BillDTO();
		billDTO.setId(bill.getId());
		billDTO.setName(bill.getName());
		billDTO.setDueDate(bill.getDueDate());
		billDTO.setAmount(bill.getAmount());
		billDTO.setDescription(bill.getDescription());

		// Map user data
		if (bill.getUser() != null) {
			billDTO.setUser(UserInfoMapper.toDTO(bill.getUser())); // Map UserInfo to UserDTO
		}

		return billDTO;
	}
}