package com.heavenhr.hhrh.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheManager {

    // with real cache tool it will be invalidated frequently in terms of days
    private static Map<Integer, String> offerTitles = new HashMap<>();

    public static Map<Integer, String> getOfferTitles() {

        return offerTitles;
    }
}
