package com.example.case6.service.impl;

import com.example.case6.model.Code;
import com.example.case6.repository.ICodeRepo;
import com.example.case6.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements ICodeService {
    @Autowired
    private ICodeRepo iCodeRepo;
    @Override
    public List<Code> getAllCodeByShopId(long idShop) {
        return iCodeRepo.getAllCodeByShopId(idShop);
    }

    @Override
    public void setQuantity(Code code) {
        iCodeRepo.save(code);
    }

    @Override
    public Code findById(long idCode) {
        Optional<Code> codeOptional = iCodeRepo.findById(idCode);
        return codeOptional.orElse(null);
    }
}
