package com.example.case6.controller.shop;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import com.example.case6.model.dto.RevenueDTO;
import com.example.case6.model.dto.ShopReviewDTO;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/shops")
public class SShopControllerAPI {
    @Autowired
    private IShopService shopService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IAccountRepo iAccountRepo;

    @GetMapping
    public ResponseEntity<List<Shop>> getAllShop() {
        return new ResponseEntity<>(shopService.getAllShop(), HttpStatus.OK);
    }

    @GetMapping("/page/{offset}")
    public ResponseEntity<List<Shop>> getFiveShopsPage(@PathVariable int offset) {
        return new ResponseEntity<>(shopService.getFiveShopsPage(offset), HttpStatus.OK);
    }

    //12
    @GetMapping("/{idShop}")
    public ResponseEntity<Shop> findShopById(@PathVariable long idShop) {
        return new ResponseEntity<>(shopService.findShopById(idShop), HttpStatus.OK);
    }

    //10
    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        shopService.save(shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    //13
    @PostMapping("/{idShop}")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
        shopService.editShop(shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    // tìm shop theo account login
    @GetMapping("/login/{idAccount}")
    public ResponseEntity<Shop> getShopByAccountLogin(@PathVariable long idAccount) {
        return new ResponseEntity<>(shopService.getShopByAccountLogin(idAccount), HttpStatus.OK);
    }

    @GetMapping("/dto/{idShop}")
    public ResponseEntity<ShopReviewDTO> findShopDTO(@PathVariable long idShop) {
        return new ResponseEntity<>(shopService.findShopDTO(idShop), HttpStatus.OK);
    }

    @GetMapping("/login/dto/{idAccount}")
    public ResponseEntity<ShopReviewDTO> findShopDTOByAccountLogin(@PathVariable long idAccount) {
        return new ResponseEntity<>(shopService.findShopDTO(idAccount), HttpStatus.OK);
    }

    @PostMapping("/save/account/{idAccount}")
    public ResponseEntity<?> saveShop(@PathVariable Long idAccount,
                                      @RequestBody Shop shop) {
        Optional<Account> accountOptional = iAccountService.getAccountByAccountId(idAccount);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            shop.setAccount(account);
            shopService.save(shop);
            account.setStatus(new Status(1));
            iAccountRepo.save(account);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/save/shop/{idShop}")
    public ResponseEntity<?> editInformationShop(@PathVariable Long idShop,
                                                 @RequestBody Shop shop) {
        Optional<Shop> shopOptional = shopService.findById(idShop);
        if (shopOptional.isPresent()) {
            shop.setId(idShop);
            shopService.save(shop);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/revenue")
    public ResponseEntity<List<RevenueDTO>> getRevenue(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>(shopService.getRevenue(account),HttpStatus.OK);
    }
}
