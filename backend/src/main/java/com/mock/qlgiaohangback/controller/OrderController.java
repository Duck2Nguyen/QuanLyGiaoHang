package com.mock.qlgiaohangback.controller;

import com.mock.qlgiaohangback.common.Constans;
import com.mock.qlgiaohangback.common.MessageResponse;
import com.mock.qlgiaohangback.common.ResponseHandler;
import com.mock.qlgiaohangback.dto.cod.CODByDateAndShopId;
import com.mock.qlgiaohangback.dto.order.OrderByDateAndCarrierId;
import com.mock.qlgiaohangback.dto.order.OrderByDateAndListId;
import com.mock.qlgiaohangback.dto.order.OrderCreateDTO;
import com.mock.qlgiaohangback.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;


    @Secured(value = "ROLE_SHOP")
    @PostMapping
    public ResponseEntity createOrder(@RequestBody @Valid OrderCreateDTO orderCreateDTO) {


        return ResponseHandler.generateResponse(MessageResponse.CREATED_SUCCESS,
                Constans.Code.CREATED_SUCCESS.getCode(),
                HttpStatus.CREATED,
                this.orderService.createOrder(orderCreateDTO));
    }

    //  API order chung
    @GetMapping("")

    public ResponseEntity getAll() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getAll());
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity getOrderInThirtyDays(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderInThirtyDays(startDate, endDate));
    }

    @GetMapping("sort")
    public ResponseEntity getOrderNewest(@RequestParam int page) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderNewest(page));
    }

    @GetMapping("not-done-yet")
    public ResponseEntity getOrdersNotDoneYet() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderNotDoneYet());
    }

    @GetMapping("/paging")
    public ResponseEntity getAllWithPaging(@RequestParam int page) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderWithPaging(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable Long id) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderById(id));
    }

    @GetMapping("/status={status}")
    public ResponseEntity getOrderByStatus(@PathVariable Constans.OrderStatus status) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByStatus(status));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity getOrderByCustomerName(@PathVariable String name) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByCustomerName(name));
    }


    //  API dành cho SHIPPER gọi order
    @GetMapping("/shipper")
    public ResponseEntity getAllByShipperId() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getAllByShipperId());
    }


    @GetMapping("/shipper/page")
    public ResponseEntity getAllByShipperIdByPage(@RequestParam(value = "pageIndex") String pageIndex,
                                                  @RequestParam(value = "pageSize") String pageSize) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getAllByShipperIdByPage(pageIndex,pageSize));
    }


    @GetMapping("/shipper/id")
    public ResponseEntity getOrderByIdAndShipperId(@RequestParam("id") Long id) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByIdAndShipperId(id));
    }

    @GetMapping("/shipper/status")
    public ResponseEntity getOrderByStatusAndShipperId(@RequestParam("status") Constans.OrderStatus status) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByStatusAndShipperId(status));
    }

    @GetMapping("/shipper/search")
    public ResponseEntity getOrderByCustomerNameAndShipperId(@RequestParam("name") String name) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByCustomerNameAndShipperId(name));
    }

    @GetMapping("/shipper/count-by-status")
    public ResponseEntity countOrderByStatus() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.countOrderByStatus());
    }

    @GetMapping("/shipper/count-by-date")
    public ResponseEntity countOrderDoneByDate(@RequestParam("date") String date, @RequestParam("nextDate") String nextDate) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.countOrderDoneByDate(date, nextDate));
    }

    @GetMapping("/shipper/statistic/cod")
    public ResponseEntity statisticCODByStatus(@RequestParam("status") String status) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.statisticCODByStatus(status));
    }

    @GetMapping("/shipper/statistic/ship_fee")
    public ResponseEntity statisticShipFeeByStatus(@RequestParam("status") String status) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.statisticShipFeeByStatus(status));
    }

    @GetMapping("/shipper/statistic/revenue")
    public ResponseEntity getRevenueToday() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getRevenueToday());
    }

    @GetMapping("/shipper/statistic/revenue-by-date")
    public ResponseEntity getRevenueByDate(@RequestParam("date") String date, @RequestParam("nextDate") String nextDate) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getRevenueByDate(date, nextDate));
    }

    // @PutMapping("/shipper/change-status")
    // public ResponseEntity changeStatus(@RequestParam(required=false,name="id") String id,@RequestParam(required=false,name="status") String status) {
    //     return ResponseHandler.generateResponse(MessageResponse.FOUND,
    //             Constans.Code.OK.getCode(),
    //             HttpStatus.OK,
    //             this.orderService.changeStatus(id,status));
    // }
    @PutMapping("/shipper/change-status/id={id}/status={status}")
    public ResponseEntity changeStatus(@PathVariable String id, @PathVariable String status) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.changeStatus(id, status));
    }
    //API cho điều phối

    //Trả về số lượng đơn theo trạng thái
    @GetMapping("/dieu-phoi/count-by-status")
    public ResponseEntity countByStatus() {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.countByStatus());

    }

    @GetMapping("/dieu-phoi/get-all-order")
    //   http://localhost:8080/api/order/dieu-phoi/get-all-order@pageIndex=&pageSize=
    public ResponseEntity getAllOrders(@RequestParam(value = "pageIndex") String pageIndex,
                                       @RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.findAllOrder(pageIndex, pageSize));

    }

    @GetMapping("/dieu-phoi/get-all-order-by-status")
    //   http://localhost:8080/api/order/dieu-phoi/get-all-order@pageIndex=&pageSize=&status=
    public ResponseEntity getAllOrdersByStatus(@RequestParam(value = "pageIndex") String pageIndex,
                                               @RequestParam(value = "pageSize", required = false) String pageSize,
                                               @RequestParam(value = "status") Constans.OrderStatus status) throws Exception {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.findAllOrderStatus(pageIndex, pageSize, status));
    }

    @GetMapping("/dieu-phoi/order-info-detail")
    //   http://localhost:8080/api/order/dieu-phoi/order-info-detail?orderId=
    public ResponseEntity getAllOrdersByStatus(@RequestParam(value = "orderId") Long orderId) throws Exception {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.findOrderRespInfDetailById(orderId));
    }
    @PutMapping("/dieu-phoi/assign-carrier")
    //    http://localhost:8080/api/order/dieu-phoi/assign-carrier?orderId=&carrierId=
    public ResponseEntity assignCarrier(@RequestParam Long orderId, @RequestParam String carrierId) throws Exception {
        return ResponseHandler.generateResponse(MessageResponse.UPDATE_SUCCESS,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.assignCarrier(orderId,carrierId));
    }
    //Lấy danh sách sản phẩm theo id đơn hàng
    @GetMapping("/dieu-phoi/get-product-list-by-orderId")
    //   http://localhost:8080/api/order/dieu-phoi/get-product-list-by-orderId@orderId=
    public ResponseEntity findAllProductOrderById(@RequestParam(value = "orderId") Long orderId) throws Exception {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.findAllProductOrderById(orderId));
    }

    @Secured(value = {"ROLE_COORDINATOR", "ROLE_SHOP"} )
    @DeleteMapping("/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id) {
        return ResponseHandler.generateResponse(MessageResponse.DELETE_SUCCESS,
                Constans.Code.DELETE_SUCCESSFUL.getCode(),
                HttpStatus.ACCEPTED,
                this.orderService.cancelOrder(id));
    }

    @PostMapping("/get-by-date-and-carrier-id")
    public ResponseEntity gitOrdersByAbove(@RequestBody OrderByDateAndCarrierId body) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByDateAndCarrierId(body));
    }

    @PostMapping("/get-by-date-and-list-id")
    public ResponseEntity getOrderByDateAndListId(@RequestBody OrderByDateAndListId body) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.updateOrderIsPaid(body));
    }
    @PostMapping("/get-by-date-and-shop-id")
    public ResponseEntity getByDateAndShopId(@RequestBody CODByDateAndShopId body) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByDateAndShopId(body));
    }

    @GetMapping("/get-by-shop-id/{id}")
    public ResponseEntity getByShopId(@PathVariable Long id) {
        return ResponseHandler.generateResponse(MessageResponse.FOUND,
                Constans.Code.OK.getCode(),
                HttpStatus.OK,
                this.orderService.getOrderByShopId(id));
    }

}

