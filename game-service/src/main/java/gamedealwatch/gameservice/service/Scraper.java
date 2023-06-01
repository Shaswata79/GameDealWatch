package gamedealwatch.gameservice.service;

import gamedealwatch.gameservice.dto.ItemDto;

import java.util.List;

public interface Scraper {
    public List<ItemDto> scrape();
}
