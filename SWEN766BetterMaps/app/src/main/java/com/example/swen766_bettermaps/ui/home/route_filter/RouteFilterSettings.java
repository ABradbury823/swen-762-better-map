package com.example.swen766_bettermaps.ui.home.route_filter;

/**
 * Data object representing the current route filter settings.
 * This object implements the builder pattern, so instantiate it with
 * <code>RouteFilterSettings settings = new RouteFilterSettings.Builder()
 * {setSettings...}.build()</code>
 */
public class RouteFilterSettings {

    private final boolean isFastestRoute;  // whether user prefers the fastest route
    private final boolean isIndoorsOnly;   // whether user wants only indoor routes

    /**
     * Object that implements the builder pattern to construct a
     * Route Filter Settings object. Defines defaults as:
     * {`isFastestRoute = true, isIndoorsOnly = false}`
     */
    public static class Builder {
        private boolean isFastestRoute = true;
        private boolean isIndoorsOnly = false;

        /**
         * Set if the user prefers the fastest route.
         * @param isFastestRoute
         * @return Reference to the builder, for chain method call instantiation.
         */
        public Builder setFastestRoute(boolean isFastestRoute) {
            this.isFastestRoute = isFastestRoute;
            return this;
        }

        /**
         * Set if the user prefers only traveling indoors.
         * @param isIndoorsOnly
         * @return Reference to the builder, for chain method call instantiation.
         */
        public Builder setUseIndoorsOnly(boolean isIndoorsOnly) {
            this.isIndoorsOnly = isIndoorsOnly;
            return this;
        }

        /**
         * Build the route filter settings.
         * @return Route filter settings. Defaults: `isFastestRoute = true`,
         * `isIndoorsOnly = false`.
         */
        public RouteFilterSettings build() {
            return new RouteFilterSettings(this);
        }
    }

    // private to prevent explicit instantiation
    private RouteFilterSettings(Builder builder) {
        this.isFastestRoute = builder.isFastestRoute;
        this.isIndoorsOnly = builder.isIndoorsOnly;
    }

    /**
     * Does the user prefer using the fastest route? True = enabled.
     */
    public boolean getIsFastestRoute() {
        return this.isFastestRoute;
    }

    /**
     * Does the user prefer traveling indoors? True = enabled.
     */
    public boolean getIsIndoorsOnly() {
        return this.isIndoorsOnly;
    }

}
