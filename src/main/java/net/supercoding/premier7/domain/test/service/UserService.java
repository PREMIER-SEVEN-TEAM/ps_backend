package net.supercoding.premier7.domain.test.service;

import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.domain.test.dto.LoginRequestDto;
import net.supercoding.premier7.domain.test.dto.UserRequestDto;
import net.supercoding.premier7.domain.test.dto.UserResponseDto;
import net.supercoding.premier7.domain.test.entity.User;
import net.supercoding.premier7.domain.test.repository.UserRepository;
import net.supercoding.premier7.domain.test.security.JwtProvider;
import net.supercoding.premier7.domain.test.dto.LoginResponseDto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signup(UserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.createToken(user.getEmail(), user.getRole(), user.getId());

        return new LoginResponseDto(token, user.getEmail(), user.getRole());
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(user.getId(), user.getEmail(), user.getRole(), user.getIsActive()))
                .collect(Collectors.toList());
    }

    public void updateUserStatus(Long userId, Boolean isActive) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        user.setIsActive(isActive);
        userRepository.save(user);
    }
}
