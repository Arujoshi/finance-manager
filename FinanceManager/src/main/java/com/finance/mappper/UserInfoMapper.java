package com.finance.mappper;

import com.finance.dto.UserDTO;
import com.finance.model.UserInfo;

public class UserInfoMapper {

	public static UserDTO toDTO(UserInfo userInfo) {
        return new UserDTO(
            userInfo.getUserId(),
            userInfo.getName(),
            userInfo.getEmail(),
            userInfo.getPhone()
        );
    }
}
