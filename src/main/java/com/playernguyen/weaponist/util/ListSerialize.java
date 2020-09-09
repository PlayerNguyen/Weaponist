package com.playernguyen.weaponist.util;

import java.util.Arrays;
import java.util.List;

public class ListSerialize {

    public static List<String> deserializeStringList(String raw) {
        return Arrays.asList(raw.split("\\|"));
    }
}
