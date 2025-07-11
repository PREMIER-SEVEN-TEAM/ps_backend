package net.supercoding.premier7.domain.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.domain.test.dto.LoginRequestDto;
import net.supercoding.premier7.domain.test.dto.LoginResponseDto;
import net.supercoding.premier7.domain.test.dto.UserRequestDto;
import net.supercoding.premier7.domain.test.dto.UserResponseDto;
import net.supercoding.premier7.domain.test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입을 진행")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto dto) {
        userService.signup(dto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하며 JWT 토큰을 반환")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = userService.login(dto);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "로그아웃", description = "클라이언트 측에서 JWT를 제거하며, 서버는 단순 응답")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("로그아웃 완료");
    }

    @Operation(summary = "회원 탈퇴", description = "사용자 ID로 회원을 탈퇴(삭제)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("회원 탈퇴 완료");
    }

    @Operation(summary = "회원 전체 조회", description = "관리자만 접근 가능하며, 전체 회원 목록을 반환")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "회원 상태 변경", description = "관리자만 접근 가능하며, 사용자의 활성 상태(isActive)를 수정")
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam Boolean isActive) {
        userService.updateUserStatus(id, isActive);
        return ResponseEntity.ok("회원 상태 변경 완료");
    }
}
