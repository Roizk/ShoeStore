package com.example.shoestore.Presentation.Controller.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Response.CartResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Authenticate.AuthenticateService;
import com.example.shoestore.Presentation.Controller.Authen.AuthenticationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.shoestore.Domain.Service.Cart.CartService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/client/cart")
public class CartController {

    private final CartService loggedInCartService;
    private final CartService guestCartService;
    private final AuthenticateService authenticateService;
    @Autowired
    public CartController(
            @Qualifier("DataCartServiceImp") CartService loggedInCartService,
            @Qualifier("LocalCartServiceImp") CartService guestCartService,
            AuthenticateService authenticateService) {
        this.loggedInCartService = loggedInCartService;
        this.guestCartService = guestCartService;
        this.authenticateService=authenticateService;
    }
    private CartService getAppropriateService( HttpServletRequest request) {
        boolean isLoggedIn = checkIfUserIsLoggedIn(request);
        return isLoggedIn ? loggedInCartService : guestCartService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getCart( HttpServletRequest request){
        try {
            CartService service = getAppropriateService(request);
            Cart response = service.getCart(authenticateService.getUserEmail());
            return ResponseUtils.buildSuccessResponse(response, "Get cart successfully");
        } catch(Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addItem(
            HttpServletRequest request,
            @RequestBody CartItem item) {
        try {

            CartService service = getAppropriateService(request);
            Cart updatedCart = service.addItem(authenticateService.getUserEmail(), item);
            return ResponseUtils.buildSuccessResponse(updatedCart, "Item added to cart successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> removeItem(
            HttpServletRequest request,
            @PathVariable String id) {
        try {
            CartService service = getAppropriateService(request);
            Cart updatedCart = service.removeItem(authenticateService.getUserEmail(), id);
            return ResponseUtils.buildSuccessResponse(updatedCart, "Item removed from cart successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> updateItemQuantity(
            HttpServletRequest request,
            @PathVariable String id,
            @RequestParam int quantity) {
        try {
            CartService service = getAppropriateService(request);
            Cart updatedCart = service.updateItemQuantity(authenticateService.getUserEmail(), id, quantity);
            return ResponseUtils.buildSuccessResponse(updatedCart, "Item quantity updated successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> clearCart( HttpServletRequest request) {
        try {
            CartService service = getAppropriateService(request);
            service.clearCart(authenticateService.getUserEmail());
            return ResponseUtils.buildSuccessResponse(null, "Cart cleared successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    private boolean checkIfUserIsLoggedIn(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        return authHeader!=null;
    }
}
