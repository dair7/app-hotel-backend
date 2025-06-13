package com.hotelbooking.HotelCostacode.service.interfac;


import com.hotelbooking.HotelCostacode.dto.LoginRequest;
import com.hotelbooking.HotelCostacode.dto.Response;
import com.hotelbooking.HotelCostacode.dto.UserUpdateRequest;
import com.hotelbooking.HotelCostacode.entity.User;


public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    Response deleteMe(String email);

    Response updateMyProfile(String email, UserUpdateRequest request);

}
