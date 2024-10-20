package logic;

/**
 * Enum for the price category of a restaurant
 */
public enum Pricing {
    $,
    $$,
    $$$;

    public static Pricing random(boolean fineDining) {
        if (fineDining) return Pricing.$$$;
        return values()[(int) (Math.random() * 2)];
    }

}
