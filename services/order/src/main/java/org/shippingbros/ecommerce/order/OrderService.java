package org.shippingbros.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.shippingbros.ecommerce.customer.CustomerClient;
import org.shippingbros.ecommerce.exception.BusinessException;
import org.shippingbros.ecommerce.orderline.OrderLineRequest;
import org.shippingbros.ecommerce.orderline.OrderLineService;
import org.shippingbros.ecommerce.product.ProductClient;
import org.shippingbros.ecommerce.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;


    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No Customer exists with the provided ID: "+request.customerId()));


        // purchase the products --> product-ms (RestTemplate)
        this.productClient.purchaseProducts(request.products());

        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // TODO: start payment process

        // send the oder confirmation --> notification-ms (kafka)
        return null;
    }
}
