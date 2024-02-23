package gb.shop.shop.controller;

import gb.shop.shop.aspects.LogAs;
import gb.shop.shop.service.WarehouseService;
import gb.shop.warehouse.model.Warehouse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/warehouse")
@AllArgsConstructor
public class WarehouseController {


    private RestTemplate restTemplate;
    private final String urlWarehouse = "http://localhost:8083/warehouse";

    private final WarehouseService warehouseService;

    public void saveWarehouse(Warehouse warehouse) {
        String url = urlWarehouse + "/save";
        HttpEntity<Warehouse> request = new HttpEntity<>(warehouse);
        try {
            restTemplate.postForObject(url, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@LogAs
    public List<Warehouse> getAllWarehouse() {
        String url = urlWarehouse + "/all";
        try {
            Object[] objects = restTemplate.getForEntity(url, Object[].class).getBody();
            assert objects != null;
            return warehouseService.parserWarehouse(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Warehouse getWarehouseById(Long id) {
        String url = urlWarehouse + "/{id}";
        return restTemplate.getForObject(url, Warehouse.class, id);
    }

    public String reserve(Long id, Integer quantity) {
        String url = urlWarehouse + "/{id}/{quantity}";
        return restTemplate.getForObject(url, String.class, id, quantity);
    }
}

