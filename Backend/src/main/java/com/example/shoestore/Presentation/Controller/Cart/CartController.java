package com.example.shoestore.Presentation.Controller.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Response.CartResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
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
    private final AuthenticationController authenticationController;
    @Autowired
    public CartController(
            @Qualifier("DataCartServiceImp") CartService loggedInCartService,
            @Qualifier("LocalCartServiceImp") CartService guestCartService,
            AuthenticationController authenticationController) {
        this.loggedInCartService = loggedInCartService;
        this.guestCartService = guestCartService;
        this.authenticationController=authenticationController;
    }
    private CartService getAppropriateService( HttpServletRequest request) {
        boolean isLoggedIn = checkIfUserIsLoggedIn(request);
        return isLoggedIn ? loggedInCartService : guestCartService;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getCart( HttpServletRequest request) throws Exception{
        try {
            CartService service = getAppropriateService(request);
            Cart response = service.getCart(getEmail(request));
            return ResponseUtils.buildSuccessResponse(response, "Get cart succesfully");
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
            Cart updatedCart = service.addItem(getEmail(request), item);
            return ResponseUtils.buildSuccessResponse(updatedCart, "Item added to cart successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }
    String getEmail(HttpServletRequest request)
    {
        String token = authenticationController.extractToken(request);
        return authenticationController.extractEmail(token);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> removeItem(
            HttpServletRequest request,
            @PathVariable String id) {
        try {
            CartService service = getAppropriateService(request);
            Cart updatedCart = service.removeItem(getEmail(request), id);
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
            Cart updatedCart = service.updateItemQuantity(getEmail(request), id, quantity);
            return ResponseUtils.buildSuccessResponse(updatedCart, "Item quantity updated successfully");
        } catch(Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> clearCart( HttpServletRequest request) {
        try {
            CartService service = getAppropriateService(request);
            service.clearCart(getEmail(request));
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
