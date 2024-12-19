package com.example.demo.controller;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 생성
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.createReservation(reservationRequestDto.getItemId(),
                reservationRequestDto.getUserId(),
                reservationRequestDto.getStartAt(),
                reservationRequestDto.getEndAt());
        return ResponseEntity.ok("예약이 성공적으로 생성됐습니다.");
    }

    // 예약 상태 업데이트
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long id, @RequestBody String status) {
        try {
            reservationService.updateReservationStatus(id, status);
            return ResponseEntity.ok("상태가 성공적으로 변경됐습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 모든 예약 조회
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAll() {
        List<ReservationResponseDto> reservations = reservationService.getReservations();
        return ResponseEntity.ok(reservations);
    }

    // 특정 사용자 또는 아이템에 관한 예약 조회
    @GetMapping("/search")
    public ResponseEntity<List<ReservationResponseDto>> searchAll(@RequestParam(required = false) Long userId,
                                                                  @RequestParam(required = false) Long itemId) {
        List<ReservationResponseDto> reservations = reservationService.searchAndConvertReservations(userId, itemId);
        return ResponseEntity.ok(reservations);
    }
}
