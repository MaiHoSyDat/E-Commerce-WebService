package com.example.case6.controller.admin;

import com.example.case6.model.Account;
import com.example.case6.model.Role;
import com.example.case6.model.dto.AccountDTO;
import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAccountService iAccountService;


    //  <1> Thêm mới 1 tài khoản cho nhân viên
    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(iAccountService.create(account), HttpStatus.OK);
    }

    //    <2> Tìm kiếm tương đối tài khoản theo Full_name hoặc Email kèm theo lựa chọn 1-Full_name và 2-Email
    @GetMapping("/getByLike")
    public ResponseEntity<Page<AccountDTO>> getByLike(@RequestParam(defaultValue = "0") int page, @RequestParam int num, @RequestParam String context) {
        return new ResponseEntity<>(iAccountService.getAllByLike(PageRequest.of(page, 10), num, context), HttpStatus.OK);
    }


    //  <3>  Lấy ra các tài khoản theo ROLE_ID
    @GetMapping("/getAccountByRole")
    public ResponseEntity<Page<AccountDTO>> getAllByRole(@RequestParam(defaultValue = "0") int page, @RequestParam long id) {
        Page<AccountDTO> accountDTOS = iAccountService.getAllByRoleId(PageRequest.of(page, 10), id);
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }


    //  <4>   Admin block/active tài khoản:
    @PutMapping("/blockOrActive")
    public ResponseEntity<Account> blockOrActive(@RequestParam long accountId, @RequestParam int statusId) {
        iAccountService.editStatus(accountId, statusId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
