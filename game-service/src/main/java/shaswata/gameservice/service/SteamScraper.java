package shaswata.gameservice.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import shaswata.gameservice.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;


@Service
public class SteamScraper implements Scraper{

    public List<ItemDto> scrape() {
        List<ItemDto> items = new ArrayList<>();

        try {
            for(int i = 1; i < 2; i++){    //each page contains 25 items
                String page = Integer.toString(i);
                String url = "https://store.steampowered.com/search/?category1=998&os=win&supportedlang=english&page=" + page + "&ndl=1";
                Document document = Jsoup.connect(url).get();
                Elements gameElements = document.select(".search_result_row");
                System.out.println(gameElements.size());

                for (Element gameElement : gameElements) {
                    String name = gameElement.select(".title").text();
                    String dateReleased = gameElement.select(".search_released").text();
                    String price = gameElement.select(".search_price").text();
                    String link = gameElement.attr("href");

                    ItemDto itemDto = new ItemDto();
                    itemDto.setStoreName("Steam");
                    itemDto.setGame(name);
                    itemDto.setCurrentPrice(extractPrice(price));
                    itemDto.setUrl(link);
                    items.add(itemDto);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return items;
    }

    private Double extractPrice(String strPrice){
        Double price = null;
        if(strPrice.equals("Free To Play") || strPrice.equals("Free to Play")){
            price = 0.0;
        }else {
            int dollarSign = strPrice.indexOf('$');
            int spaceIndex = strPrice.indexOf(' ', dollarSign+2);
            if(spaceIndex != -1){
                price = Double.parseDouble(strPrice.substring(dollarSign+2, spaceIndex));
            }else {
                price = Double.parseDouble(strPrice.substring(dollarSign+2));
            }

        }
        System.out.println(price);
        return price;
    }



}
