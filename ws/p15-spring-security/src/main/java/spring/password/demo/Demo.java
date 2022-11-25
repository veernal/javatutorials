package spring.password.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Demo {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String pwd = "demo@123";
        String encodedPwd = encoder.encode(pwd);

        System.out.println(pwd + " : "+encodedPwd);
    }
}
