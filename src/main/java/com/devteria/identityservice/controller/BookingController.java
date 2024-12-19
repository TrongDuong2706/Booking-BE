package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.BookingRequest;
import com.devteria.identityservice.dto.response.BookingResponse;
import com.devteria.identityservice.service.imp.BookingServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
    BookingServiceImp bookingServiceImp;
    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody BookingRequest request, HttpServletRequest httpRequest) {
        BookingResponse bookingResponse = bookingServiceImp.createBooking(request, httpRequest);
        return ApiResponse.<BookingResponse>builder()
                .result(bookingResponse)
                .build();
    }
    @GetMapping("/{bookingId}")
    public ApiResponse<BookingResponse> getOneBooking(@PathVariable int bookingId){

        return ApiResponse.<BookingResponse>builder()
                .result(bookingServiceImp.getOneBooking(bookingId))
                .build();
    }
    @GetMapping("/totalBookings")
    public ApiResponse<Long> getTotalBooking(){
        return ApiResponse.<Long>builder()
                .result(bookingServiceImp.getTotalBooking())
                .build();
    }
    @GetMapping("/monthly")
    public ApiResponse<Map<String, Double>> getMonthlyRevenue() {
        List<Object[]> data = bookingServiceImp.getMonthlyRevenue();

        Map<String, Double> monthlyRevenue = data.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],   // Tháng
                        row -> ((Number) row[1]).doubleValue()  // Doanh thu
                ));
        return ApiResponse.<Map<String, Double>>builder()
                .message("Data fetched successfully")
                .result(monthlyRevenue)
                .build();
    }
}
