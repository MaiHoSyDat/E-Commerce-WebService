package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CodeDTO;
import com.example.case6.repository.ICartDetailRepo;
import com.example.case6.repository.ICartRepo;
import com.example.case6.repository.ICodeRepo;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    ICartRepo iCartRepo;
    @Autowired
    ICartDetailService iCartDetailService;
    @Autowired
    IProductService iProductService;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IOrderDetailService iOrderDetailService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IShopService iShopService;
    @Autowired
    ICodeRepo iCodeRepo;

    @Override

    public List<Cart> getAll() {
        return iCartRepo.findAll();
    }

    @Override
    public Cart getById(long id) {
        return iCartRepo.findById(id);
    }

    @Override
    public Cart create(Cart cart) {
        return iCartRepo.save(cart);
    }

    @Override
    public Cart edit(Cart cart) {
        return iCartRepo.save(cart);
    }

    @Override
    public void deleteById(long id) {
        iCartRepo.deleteById(id);
    }

    @Override
    public CartDetail addToCart(Account account, long productId, int quantity) {
        Cart cart = getByAccount(account);
        Product product = iProductService.getById(productId);
        CartDetail cartDetail;
        if (cart != null) {

            int index = -1;
            List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
            if (cartDetails != null) {

                for (int i = 0; i < cartDetails.size(); i++) {
                    if (cartDetails.get(i).getProduct().getId() == productId) {
                        index = i;
                    }
                }

                if (index != -1) {
                    cartDetails.get(index).setQuantity(cartDetails.get(index).getQuantity() + quantity);
                    cartDetail = iCartDetailService.save(cartDetails.get(index));

                } else {
                    cartDetail = iCartDetailService.save(new CartDetail(0, product, cart, quantity));
                }
            } else {
                cartDetail = iCartDetailService.save(new CartDetail(0, product, cart, quantity));
            }
            return cartDetail;
        } else {
            cart = new Cart(0, account);
            create(cart);
            CartDetail cartDetail1 = new CartDetail(0, product, cart, quantity);
            cartDetail = iCartDetailService.save(cartDetail1);
        }
        return cartDetail;
    }

    @Override
    public Cart getByAccount(Account account) {
        return iCartRepo.findByAccountId(account.getId());
    }

    @Override
    public List<CartDetail> updateCart(List<CartDetail> cartDetails) {
        return iCartDetailService.updateQuantityByCart(cartDetails);

    }

    @Override
    public void deleteProductByCar(long cartDetailId) {
        iCartDetailService.deleteCartDetail(cartDetailId);
    }

    @Override
    public List<CartDetail> getAllCartDetail(Account account) {
        Cart cart = getByAccount(account);
        return iCartDetailService.getByCart(cart);
    }

    @Override
    public boolean payment(Account account, String fullName, String phone, String address, List<CodeDTO> codeDTOs) {
        Customer customer = iCustomerService.getByAccount(account);
        Cart cart = iCartRepo.findByAccountId(account.getId());
        List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
        List<Shop> shops = new ArrayList<>();
        for (CartDetail cd : cartDetails) {
            Product product = iProductService.findById(cd.getProduct().getId()).get();
            if (cd.getQuantity() > product.getQuantity()) {
                return false;
            }
        }
        for (CodeDTO cd : codeDTOs) {
            Shop shop = iShopService.findShopById(cd.getShopId());
            shops.add(shop);
        }
        for (Shop s : shops) {
            Code code = null;
            for (CodeDTO cd : codeDTOs) {
                if (cd.getShopId() == s.getId()) {
                    if (cd.getId() != -1) {
                        code = iCodeRepo.findById(cd.getId());
                        code.setQuantity(code.getQuantity() - 1);
                        iCodeRepo.save(code);
                    }
                }
            }
            Order order = new Order(customer, fullName, phone, address, code, s);
            iOrderService.save(order);
            for (CartDetail cd : cartDetails) {
                if (cd.getProduct().getShop().getId() == s.getId()) {
                    OrderDetail orderDetail = new OrderDetail(order, cd.getProduct(), cd.getQuantity());
                    iOrderDetailService.save(orderDetail);
                    Product product = iProductService.findById(cd.getProduct().getId()).get();
                    product.setQuantity(product.getQuantity() - cd.getQuantity());
                    iProductService.save(product);
                    iCartDetailService.deleteCartDetail(cd.getId());
                }
            }
        }

        return true;
    }


}