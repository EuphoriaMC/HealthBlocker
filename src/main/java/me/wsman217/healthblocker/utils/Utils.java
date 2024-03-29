package me.wsman217.healthblocker.utils;

import org.bukkit.block.Block;

public class Utils {
    public static Directions getDirection(Block block) {
        String data = block.getBlockData().getAsString();
        String parse = data.substring(data.indexOf("[") + 1).replace("facing=", "*");
        int i = parse.indexOf("*") + 1;
        int j = parse.indexOf(",", parse.indexOf("*"));
        j = j < 0 ? parse.length() - 1 : j;
        return Directions.fromString(parse.substring(i, j));
    }

    public static boolean isInt(String toTest) {
        try {
            Integer.parseInt(toTest);
            return true;
        } catch (NumberFormatException ignore) {
            return false;
        }
    }

    public static int parseInt(String intToBe) {
        try {
            return Integer.parseInt(intToBe);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }
}
