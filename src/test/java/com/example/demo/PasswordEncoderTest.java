package com.example.demo;

import com.example.demo.util.PasswordEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordEncoderTest {

    @Test
    @DisplayName("패스워드 인코더 encode 메소드 검증")
    void testEncode() {
        String rawPassword = "test1234";

        String encodedPassword = PasswordEncoder.encode(rawPassword);

        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    @DisplayName("패스워드 인코더 matches 메소드 성공 검증")
    void testMatches() {
        String rawPassword = "test1234";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        boolean matches = PasswordEncoder.matches(rawPassword, encodedPassword);

        assertTrue(matches);
    }


    @Test
    @DisplayName("패스워드 인코더 matches 메소드 실패 검증")
    void testMatchedsWithIncorrectPassword() {

        String rawPassword = "test1234";
        String encodedPassword = PasswordEncoder.encode(rawPassword);
        String incorrectPassword = "fail1234";

        boolean matches = PasswordEncoder.matches(incorrectPassword, encodedPassword);

        assertFalse(matches);
    }


}
