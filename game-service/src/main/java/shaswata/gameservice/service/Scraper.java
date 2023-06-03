package shaswata.gameservice.service;

import shaswata.gameservice.dto.ItemDto;

import java.util.List;

public interface Scraper {
    public List<ItemDto> scrape();
}
