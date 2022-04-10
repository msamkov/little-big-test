package com.example.littlebigtest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContentTest {


    private String getContentDescription(final String description, final String trademark) {
        return Stream.of(description, trademark)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(", "));
    }

    private static final String DESCRIPTION = "Шорты";
    private static final String TRADEMARK = "Адидас";

    @Test
    public void test() {

        final String excpectedDesc = "Шорты, Адидас";

        final String actualDesc = getContentDescription(DESCRIPTION, TRADEMARK);

        Assertions.assertEquals(excpectedDesc, actualDesc);

    }


    @Test
    public void descriptionNull() {

        final String excpectedDesc = "Адидас";

        final String actualDesc = getContentDescription(null, TRADEMARK);

        Assertions.assertEquals(excpectedDesc, actualDesc);

    }


    @Test
    public void descriptionNullTrademarkNull() {

        final String excpectedDesc = "";

        final String actualDesc = getContentDescription(null, null);

        Assertions.assertEquals(excpectedDesc, actualDesc);

    }

    @Test
    public void trademarkNull() {

        final String excpectedDesc = "Шорты";

        final String actualDesc = getContentDescription(DESCRIPTION, null);

        Assertions.assertEquals(excpectedDesc, actualDesc);

    }

}
