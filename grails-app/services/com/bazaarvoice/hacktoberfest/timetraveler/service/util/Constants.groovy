package com.bazaarvoice.hacktoberfest.timetraveler.service.util

import org.apache.commons.lang.StringUtils
import org.joda.time.Interval

import java.text.MessageFormat
import java.text.SimpleDateFormat

class Constants {
    private static Map<String, String> CLIENT_DEV_API_KEYS = new HashMap<String, String>() {{
        put("petsmart", "6rxf2cqb45bvikkh81upgtaw5")
        put("bestbuy", "bazm2nnzvt3t6q4i6pvq98xv3")
        put("costco", "6pjbmpe6rf5ywppqopxlxm9wh")
        put("homedepot", "m7lebkidnsp30y9y8n9l1ay4y")
        put("petco", "eoscn2t51n6p4q7l26fq0h6em")
        put("petsmart", "6rxf2cqb45bvikkh81upgtaw5")
        put("walmart", "tuqwdwcw3guot4dourkh1fvlv")
        put("verizon", "6ybx2uv5z2jidrkoy4fsz4vvl")
//        put("", "")
    }}

// LUVEEN TODO get some good sample product IDs to work with!
    private static Map<String, List<Tuple<String>>> CLIENT_PRODUCT_NAMES = new HashMap<String, List<Tuple<String>>>() {{
//        put("petsmart", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("Costco", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("HomeDepot", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("PETCO", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("PetSmart", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("Walmart", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
//        put("Walmart", new ArrayList<String>() {{add(""); add(""); add(""); add(""); add(""); add(""); }})
        put("verizon", new ArrayList<Tuple<String>>() {{ add(new Tuple("5947", "Motorola DROID RAZR M")); add(new Tuple("5954", "LG Spectrum 2")); }})
//        put("bestbuy", new ArrayList<String>() {{add("3049133"); add("1318913"); add("1318986"); }})
//        put("", "")
    }}

    private static String CLIENT_REVIEWS_URL = "http://api.bazaarvoice.com/data/reviews.json?apiversion=5.4&passkey={0}&filter=productId:{1}&filter=SubmissionTime:gt:{2}&filter=SubmissionTime:lt:{3}"
    private static String CLIENT_PAGEVIEWS_URL = "https://magpie.bazaarvoice.com/api/pageviews/total/clientName/{0}?start_date={1}&end_date={2}&passkey=e7da1235-02e6-4a77-b0f8-a0444f278aab"
    private static SimpleDateFormat CLIENT_PAGEVIEWS_DATE_FORMAT = new SimpleDateFormat("yyyymmdd")

    public static String getClientReviewsUrl(String clientName, String productId, Interval interval) {
        assert(StringUtils.isNotEmpty(clientName))
        final String clientNameLowerCase = clientName.toLowerCase()
        final String startTimestamp = String.valueOf(interval.getStartMillis() / 1000)
        final String endTimestamp = String.valueOf(interval.getEndMillis() / 1000)
        assert(CLIENT_DEV_API_KEYS.containsKey(clientNameLowerCase))

        final String result = MessageFormat.format(CLIENT_REVIEWS_URL, CLIENT_DEV_API_KEYS.get(clientNameLowerCase), productId, startTimestamp, endTimestamp)
//        System.out.println("clientName: " + clientName + ", productId: " + productId + ", url: " + result)

        return result
    }

    public static String getClientPageviewsUrl(String clientName, Interval interval) {
        assert(interval.start != null)
        assert(interval.end != null)
        assert(StringUtils.isNotEmpty(clientName))

        return MessageFormat.format(
                CLIENT_REVIEWS_URL,
                clientName,
                CLIENT_PAGEVIEWS_DATE_FORMAT.format(interval.start),
                CLIENT_PAGEVIEWS_DATE_FORMAT.format(interval.end))
    }

    public static Map<String, String> getClientProductTuples(String clientName) {
        assert(StringUtils.isNotEmpty(clientName))
        return CLIENT_PRODUCT_NAMES.get(clientName.toLowerCase())
    }
}
