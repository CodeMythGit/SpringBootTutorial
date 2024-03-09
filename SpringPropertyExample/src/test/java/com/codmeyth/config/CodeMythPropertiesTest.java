package com.codmeyth.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
public class CodeMythPropertiesTest {


    @Value("${name}")
    private String name;

    @Test
    public void testPropertyValue() {
        assertThat(name).isEqualTo("xyz");
    }
}
