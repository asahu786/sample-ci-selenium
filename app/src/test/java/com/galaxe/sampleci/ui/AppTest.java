package com.galaxe.sampleci.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App using TestNG.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void sanityTest() {
        // TestNG uses Assert instead of JUnit's assertTrue
        Assert.assertTrue(true, "App should run without error0");
    }
}