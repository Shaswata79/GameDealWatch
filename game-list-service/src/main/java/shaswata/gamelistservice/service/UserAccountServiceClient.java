package shaswata.gamelistservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shaswata.gamelistservice.dto.UserDto;


@FeignClient(
        name = "accounts",
        url = "${clients.accounts.url}"
)
public interface UserAccountServiceClient {

    @PostMapping (path = "/accounts/user/updateListID")
    UserDto updateUserListID(@RequestParam("email") String email, @RequestParam("listID") String listID);

}













//    public void updateUserAccount(String email, Long gameListID) throws Exception {
//        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//        requestParams.add("email", email);
//        requestParams.add("listID", gameListID.toString());
//        try {
//            restTemplate.postForObject("http://USER-ACCOUNT-SERVICE/user/updateListID", requestParams, UserDto.class);
//        }catch (RestClientException e){
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public UserDto getUser(String email){
//        UserDto userDto = restTemplate.getForObject("http://USER-ACCOUNT-SERVICE/user/get/" + email, UserDto.class);
//        return userDto;
//    }

