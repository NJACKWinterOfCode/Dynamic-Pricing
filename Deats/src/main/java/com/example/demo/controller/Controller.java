package com.example.demo.controller;


import com.example.demo.exchanges.*;
import com.example.demo.services.CartService;
import com.example.demo.services.GroceryService;
import com.example.demo.services.MarketService;
import com.example.demo.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
@RequestMapping(Controller.MARKET_API_ENDPOINT)
public class Controller {

    static final String MARKET_API_ENDPOINT = "/deats";

    private static final String MARKETS_API = "/markets";

    private static final String GROCERS_API = "/grocers";

    private static final String PRODUCTS_API = "/products";

    private static final String CART_VIEW_API = "/cart/view";

    private static final String CART_UPDATE_API = "/cart/add";

    private static final String CART_REMOVE_API = "/cart/remove";

    private static final String CART_CHECKOUT_API = "/cart/checkout";

    private static final String CART_PRESENT_API = "/cart/present";

    private static final String CART_CREATE_API = "/cart/create";


    @Autowired
    private MarketService marketService;
    @Autowired
    private GroceryService groceryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;


    //http://localhost:8080/deats/markets
    @GetMapping(MARKETS_API)
    public ResponseEntity<GetMarketsResponse> getMarkets(GetMarketsRequest getMarketsRequest) {
        GetMarketsResponse getMarketsResponse;

        getMarketsResponse = marketService.findMarketsCloseBy(getMarketsRequest);

        return ResponseEntity.ok().body(getMarketsResponse);
    }

    // http://localhost:8080/deats/grocers?marketName=Azadpur
    @GetMapping(GROCERS_API)
    public ResponseEntity<GetGrocersResponse> getGrocers(GetGrocersRequest getGrocersRequest) {

        GetGrocersResponse getGrocersResponse = groceryService.findGroceryByMarket(getGrocersRequest);
        return ResponseEntity.ok().body(getGrocersResponse);
    }

    // http://localhost:8080/deats/products?groceryName=Sharma kirana&marketName=Shahdara
    @GetMapping(PRODUCTS_API)
    public ResponseEntity<GetProductsResponse> getProducts(GetProductRequest getProductRequest) {

        GetProductsResponse getProductsResponse = productService.findProductByGrocery(getProductRequest);

        return ResponseEntity.ok().body(getProductsResponse);
    }


    //http://localhost:8080/deats/cart/present?cartId=5da8ab2d7d1820b0bddbf6aa
    // reponse is not json simple true or false
    @GetMapping(CART_PRESENT_API)
    public ResponseEntity<Boolean> presentCart(GetCartRequest getCartRequest) {

        boolean ans = cartService.present(getCartRequest);

        return ResponseEntity.ok().body(ans);

    }


    //http://localhost:8080/deats/cart/view?cartId=5da94f827d1820b0bddbf6ab
    @GetMapping(CART_VIEW_API)
    public ResponseEntity<GetCartResponse> viewCart(GetCartRequest getCartRequest) {

        GetCartResponse getCartResponse = cartService.view(getCartRequest);

        return ResponseEntity.ok().body(getCartResponse);
    }

    //http://localhost:8080/deats/cart/create?email=utk@gmail.com
    @GetMapping(CART_CREATE_API)
    public ResponseEntity<GetCartResponse> createCart(CreateCartRequest createCartRequest) {

        GetCartResponse getCartResponse = cartService.create(createCartRequest);

        return ResponseEntity.ok().body(getCartResponse);
    }

    //http://localhost:8080/deats/cart/add?cartId=5da9897972eba53c8036c9e9&productIndex=0&market=aliganj&grocer=qwe&quantity=15
    @GetMapping(CART_UPDATE_API)
    public ResponseEntity<GetCartResponse> addItem(GetCartUpdateRequest getCartUpdateRequest) {
        GetCartResponse getCartResponse = cartService.add(getCartUpdateRequest);

        return ResponseEntity.ok().body(getCartResponse);


    }
    // http://localhost:8080/deats/cart/remove?cartId=5da9897972eba53c8036c9e9&productIndex=0&market=aliganj&grocer=qwe&quantity=15
    @GetMapping(CART_REMOVE_API)
    public ResponseEntity<GetCartResponse> removeItem(GetCartUpdateRequest getCartUpdateRequest) {

        GetCartResponse getCartResponse = cartService.remove(getCartUpdateRequest);

        return ResponseEntity.ok().body(getCartResponse);
    }

    // http://localhost:8080/deats/cart/checkout?cartId=5da94f827d1820b0bddbf6ab
    @GetMapping(CART_CHECKOUT_API)
    public ResponseEntity<GetCartResponse> checkout(GetCartRequest getCartRequest) {

        GetCartResponse getCartResponse = cartService.checkout(getCartRequest);

        return ResponseEntity.ok().body(getCartResponse);
    }


}
