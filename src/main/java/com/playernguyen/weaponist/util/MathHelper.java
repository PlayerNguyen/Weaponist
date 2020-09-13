package com.playernguyen.weaponist.util;

import java.util.concurrent.ThreadLocalRandom;

public class MathHelper {

    public static int randomInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
