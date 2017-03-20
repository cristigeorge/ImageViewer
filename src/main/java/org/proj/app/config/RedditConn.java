package org.proj.app.config;


import com.vdurmont.emoji.EmojiParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.proj.app.domain.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class RedditConn {

    private Set<Image> imageSet;

    public RedditConn() {
        this.imageSet = new LinkedHashSet<>();
    }

    /*
       Genereaza un set de imagini cu url unic de pe leddit
        */
    public Set<Image> readUrl(String url1, String q) throws Exception {
        q = q.replace(" ", "+");
        String x = "".equals(q) ? "" : "+";
        String link = "";
        if (q.isEmpty()) {
            link = "https://www.reddit.com/r/" + url1 + ".json";
        } else {
            link = "https://www.reddit.com" + ((url1.isEmpty()) ? "/" : "/r/" + url1 + "/") + "search.json?q=" + q + x + "site%3A%28i.reddituploads.com+OR+imgur.com+OR+i.redd.it%29&restrict_sr=on&sort=top&t=day&limit=25";
        }
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "thebaws133");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String jsonString = response.toString();
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arr = obj.getJSONObject("data").getJSONArray("children");
        int size = arr.length();

        for (int i = 0; i < size; i++) {
            String title = EmojiParser.removeAllEmojis(((JSONObject) arr.get(i)).getJSONObject("data").get("title").toString());
            String urlImg = ((JSONObject) arr.get(i)).getJSONObject("data").get("url").toString().replace("amp;", "");
            String extension = urlImg.substring(urlImg.lastIndexOf("."));
            if (extension.equals(".gifv")||urlImg.toLowerCase().contains("gfycat")) {

            } else if ((urlImg.startsWith("http://imgur.com")) && (urlImg.endsWith(".jpg"))) {

                String http = urlImg.substring(0, 7);
                String imgur = urlImg.substring(7, urlImg.length());
                String newString = http + "i." + imgur;
                imageSet.add(new Image(title, newString, extension));

            } else if (urlImg.startsWith("http://imgur.com")) {

                String http = urlImg.substring(0, 7);
                String imgur = urlImg.substring(7, urlImg.length());
                String newString = http + "i." + imgur +".jpg";
                imageSet.add(new Image(title, newString, extension));

            } else if (urlImg.startsWith("https://imgur.com")) {

                String http = urlImg.substring(0, 8);
                String imgur = urlImg.substring(8, urlImg.length());
                String newString = http + "i." + imgur + ".jpg";
                imageSet.add(new Image(title, newString, extension));

            } else if (urlImg.startsWith("http://i.imgur.com")) {

                String http = urlImg.substring(0, 8);
                String imgur = urlImg.substring(8, urlImg.length());
                String newString = http + imgur + ".jpg";
                imageSet.add(new Image(title, newString, extension));
            } else {
                imageSet.add(new Image(title, urlImg, extension));
            }

        }
        imageSet.forEach(System.out::println);
        return imageSet;
    }


}
