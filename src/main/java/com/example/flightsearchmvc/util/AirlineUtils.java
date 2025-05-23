package com.example.flightsearchmvc.util;

import java.util.Map;

/**
 * Utility class for resolving airline codes to their full names.
 * 
 * This is primarily used to improve the readability of search results by converting
 * IATA airline codes (e.g., "AA") into user-friendly names (e.g., "American Airlines").
 */
public class AirlineUtils {

    /**
     * Map of IATA airline codes to full airline names.
     * 
     * Includes major U.S. and international carriers that operate into or out of the United States.
     * Can be expanded with more codes or replaced with a dynamic lookup service in the future.
     */
    private static final Map<String, String> airlineMap = Map.ofEntries(
        Map.entry("AA", "American Airlines"),
        Map.entry("DL", "Delta Air Lines"),
        Map.entry("UA", "United Airlines"),
        Map.entry("WN", "Southwest Airlines"),
        Map.entry("AS", "Alaska Airlines"),
        Map.entry("B6", "JetBlue Airways"),
        Map.entry("HA", "Hawaiian Airlines"),
        Map.entry("NK", "Spirit Airlines"),
        Map.entry("F9", "Frontier Airlines"),
        Map.entry("G4", "Allegiant Air"),
        Map.entry("OO", "SkyWest Airlines"),
        Map.entry("MQ", "Envoy Air"),
        Map.entry("9E", "Endeavor Air"),
        Map.entry("YX", "Republic Airways"),
        Map.entry("YV", "Mesa Airlines"),
        Map.entry("PT", "Piedmont Airlines"),
        Map.entry("OH", "PSA Airlines"),
        Map.entry("QX", "Horizon Air"),
        Map.entry("G7", "GoJet Airlines"),
        Map.entry("9K", "Cape Air"),
        Map.entry("3M", "Silver Airways"),
        Map.entry("LF", "Contour Airlines"),
        Map.entry("9X", "Southern Airways Express"),
        Map.entry("4B", "Boutique Air"),
        Map.entry("MW", "Mokulele Airlines"),
        Map.entry("AN", "Advanced Air"),
        Map.entry("KG", "Denver Air Connection"),
        Map.entry("AC", "Air Canada"),
        Map.entry("BA", "British Airways"),
        Map.entry("LH", "Lufthansa"),
        Map.entry("AF", "Air France"),
        Map.entry("QR", "Qatar Airways"),
        Map.entry("EK", "Emirates"),
        Map.entry("TK", "Turkish Airlines"),
        Map.entry("NH", "All Nippon Airways"),
        Map.entry("JL", "Japan Airlines"),
        Map.entry("KE", "Korean Air"),
        Map.entry("QF", "Qantas"),
        Map.entry("LA", "LATAM Airlines"),
        Map.entry("AV", "Avianca"),
        Map.entry("AM", "Aeroméxico"),
        Map.entry("IB", "Iberia"),
        Map.entry("ET", "Ethiopian Airlines"),
        Map.entry("VS", "Virgin Atlantic"),
        Map.entry("SQ", "Singapore Airlines"),
        Map.entry("LX", "Swiss International Air Lines"),
        Map.entry("SK", "Scandinavian Airlines"),
        Map.entry("TP", "TAP Air Portugal")
    );

    /**
     * Returns the full name of an airline given its IATA code.
     * 
     * @param code the 2-letter IATA airline code
     * @return the full airline name if known; otherwise returns the code itself
     */
    public static String getAirlineName(String code) {
        return airlineMap.getOrDefault(code, code);
    }
}
