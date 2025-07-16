package com.rushaul.logisitcs_backend.utility;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {
    public static String generateOtp() {
        int otp = (int)(Math.random() * 9000) + 1000;
        return String.valueOf(otp);
    }
}
