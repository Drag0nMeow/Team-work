package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputeTest {
    @Test
    void typeCheckTest() {
        String f = "";
        new Compute().typeCheck(f,0);
    }
}