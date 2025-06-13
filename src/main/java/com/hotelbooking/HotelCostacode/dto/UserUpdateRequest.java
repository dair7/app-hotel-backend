package com.hotelbooking.HotelCostacode.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String email;
    private String phoneNumber;
}
