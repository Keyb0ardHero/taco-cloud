// tag::baseClass[]
package tacos.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

import javax.validation.Valid;

//end::baseClass[]
//tag::baseClass[]


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo){
    this.orderRepo=orderRepo;
  }

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order)
  {
    if(order.getName()==null){
      order.setName(user.getFullname());
    }

    if(order.getStreet()==null){
      order.setStreet(user.getStreet());
    }

    if(order.getCity()==null){
      order.setStreet(user.getCity());
    }

    if(order.getState()==null){
      order.setState(user.getState());
    }

    if(order.getZip()==null){
      order.setZip(user.getZip());
    }

    if(order.getStreet()==null){
      order.setStreet(user.getStreet());
    }
    return "orderForm";
  }




  //tag::handlePostWithValidation[]
  @PostMapping
  public String processOrder(@Valid Order order, Errors errors,SessionStatus sessionStatus,@AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    order.setUser(user);
    orderRepo.save(order);
    sessionStatus.setComplete();
    return "redirect:/design";
  }
  //end::handlePostWithValidation[]

  //tag::baseClass[]
  
}
//end::baseClass[]
