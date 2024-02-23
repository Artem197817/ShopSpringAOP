package gb.shop.shop.controller;

import gb.shop.payment.dto.TransferRequest;
import gb.shop.payment.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Controller
@AllArgsConstructor
public class AccountController {

    private final RestTemplate restTemplate;

    private final String urlAccount = "http://localhost:8082/account";


    public void saveAccount(Account account) {
        String url = urlAccount + "/save";
        HttpEntity<Account> request = new HttpEntity<>(account);
        try {
            restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String transfer(TransferRequest transferRequest) {
        String url = urlAccount + "/transfer";
        String message = "Ошибка при выполнении операции";
        HttpEntity<TransferRequest> request = new HttpEntity<>(transferRequest);
        try {
            message = restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
