package com.example.case6.service;

import com.example.case6.model.Code;

import java.util.List;

public interface ICodeService {
    List<Code> getAllCodeByShopId(long idShop);
    void setQuantity(Code code);
    Code findById(long idCode);
}
