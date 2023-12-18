package com.sitebooks.librovermo;

import com.sitebooks.librovermo.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class LoginTest {
    @Autowired
    private MainController controller;
    @Test
    public void Login_password_Qwert123_login_GOsTorg() {
        String expected = "";
        String actual = controller.tryLogin("GOsTorg","Qwert123!");
        assertThat(actual, is(equalTo(expected)));
    }
}
