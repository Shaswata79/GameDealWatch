package shaswata.gamelistservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shaswata.gamelistservice.dto.ItemDto;
import shaswata.gamelistservice.dto.PriceDto;

import java.util.List;


@FeignClient(
        name = "game",
        url = "${clients.game.url}"
)
public interface GameServiceClient {

    @GetMapping("/game/user/getItems")
    List<ItemDto> getItems(@RequestParam List<String> ids);

    @GetMapping("/game/user/getItem")
    ItemDto getItem(@RequestParam String id);

    @GetMapping("/game/user/latestPrices")
    List<PriceDto> getLatestPrices();

}













//    private final RestTemplate restTemplate;
//
//    public List<ItemDto> getItems(List<String> itemIDs) throws Exception {
//        List<ItemDto> itemDtoList = null;
//        String baseUrl = "http://GAME-SERVICE/user/getItems";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("ids", itemIDs);
//        String url = builder.toUriString();
//
//        ParameterizedTypeReference<List<ItemDto>> typeRef = new ParameterizedTypeReference<List<ItemDto>>() {};
//        try{
//            ResponseEntity<List<ItemDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
//            itemDtoList = responseEntity.getBody();
//        }catch (RestClientException e){
//            throw new Exception(e.getMessage());
//        }
//        return itemDtoList;
//    }
//
//
//    public ItemDto getItem(Long itemID){
//        String baseUrl = "http://GAME-SERVICE/user/getItem";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("id", itemID);
//        String url = builder.toUriString();
//
//        ParameterizedTypeReference<ItemDto> typeRef = new ParameterizedTypeReference<ItemDto>() {};
//        ResponseEntity<ItemDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
//        ItemDto itemDto = responseEntity.getBody();
//
//        return itemDto;
//    }
