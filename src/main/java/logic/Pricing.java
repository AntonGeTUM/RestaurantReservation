package logic;

/**
 * Enum for the price category of a restaurant
 */
public enum Pricing {
    $,
    $$,
    $$$,
    $$$$,
    $$$$$;

    public static Pricing random() {
        return values()[(int) (Math.random() * values().length)];
    }

}
