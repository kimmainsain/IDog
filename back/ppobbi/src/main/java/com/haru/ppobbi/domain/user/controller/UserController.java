package com.haru.ppobbi.domain.user.controller;

import static com.haru.ppobbi.domain.user.constant.UserResponseMessage.GET_USER_INFO_SUCCESS;
import static com.haru.ppobbi.domain.user.constant.UserResponseMessage.SIGN_IN_SUCCESS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haru.ppobbi.domain.user.dto.UserRequestDto.SignUpOrInRequestDto;
import com.haru.ppobbi.domain.user.dto.UserResponseDto.SignUpOrInResponseDto;
import com.haru.ppobbi.domain.user.service.UserService;
import com.haru.ppobbi.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> signUpOrIn(
        @RequestBody SignUpOrInRequestDto signUpRequestDto)
        throws ParseException, JsonProcessingException {
        userService.signUpOrIn(signUpRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(SIGN_IN_SUCCESS,
                SignUpOrInResponseDto.builder().build())
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDto<?>> getUserInfo(
        @RequestHeader(value = "access-token") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDto.create(GET_USER_INFO_SUCCESS,
                userService.getUserInfo(accessToken))
        );
    }
}
