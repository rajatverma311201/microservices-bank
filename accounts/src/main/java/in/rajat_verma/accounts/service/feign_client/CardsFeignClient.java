package in.rajat_verma.accounts.service.feign_client;

import in.rajat_verma.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);

}
