package com.hotelbooking.HotelCostacode.service.interfac;


import com.hotelbooking.HotelCostacode.dto.Response;
import com.hotelbooking.HotelCostacode.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);


}
