import {
  ResponseReceived,
  OrderDisplayPagingType,
  OrderInThirtyDays,
  OrderDisplayType,
  OrderDPResponse,
  OrderInfDetailResponse,
  ProductInfInOrderByDP,
} from "@Common/types";
import httpCommon from "@Services/http-common";

class OrderService {
  getAllOrderByShipperId = () => {
    return httpCommon.get("/order/shipper");
  };
  getAllOrderByShipperIdByPage = (pageIndex: number, pageSize: number) => {
    return httpCommon.get("/order/shipper/page", {
      params: {
        pageIndex,
        pageSize
      },
    });
  };

  getOrderByIdAndShipperId = (id: number) => {
    return httpCommon.get("/order/shipper/id", {
      params: {
        id,
      },
    });
  };
  getOrderByStatusAndShipperId = (status: String) => {
    return httpCommon.get("/order/shipper/status", {
      params: {
        status,
      },
    });
  };
  getOrderByCustomerNameAndShipperId = (name: String) => {
    return httpCommon.get("/order/shipper/search", {
      params: {
        name,
      },
    });
  };
  countOrderByStatus = () => {
    return httpCommon.get("/order/shipper/count-by-status");
  };
  countOrderDoneByDate = (date: String, nextDate: String) => {
    return httpCommon.get("/order/shipper/count-by-date", {
      params: {
        date,
        nextDate,
      },
    });
  };
  statisticCODByStatus = (status: String) => {
    return httpCommon.get("/order/shipper/statistic/cod", {
      params: {
        status,
      },
    });
  };
  statisticShipFeeByStatus = (status: String) => {
    return httpCommon.get("/order/shipper/statistic/ship_fee", {
      params: {
        status,
      },
    });
  };
  getRevenueToday = () => {
    return httpCommon.get("/order/shipper/statistic/revenue");
  };
  getRevenueByDate = (date: String, nextDate: String) => {
    return httpCommon.get("/order/shipper/statistic/revenue-by-date", {
      params: {
        date,
        nextDate,
      },
    });
  };
  changeStatus = (id: String, status: String) => {
    return httpCommon.put(
      `/order/shipper/change-status/id=${id}/status=${status}`
    );
  };

  getOrdersByDateAndCarrierId = (body: Object) => {
    return httpCommon.post(
      "/order/get-by-date-and-carrier-id", body
    )
  }

  getOrdersByDateAndListId = (body: Object) => {
    return httpCommon.post(
      "/order/get-by-date-and-list-id", body
    )
  }

  getOrdersByDateAndShopId = (body: Object) => {
    return httpCommon.post(
      "/order/get-by-date-and-shop-id", body
    )
  }

  getOrdersByShopId = (id: Number) => {
    return httpCommon.get(
      "/order/get-by-shop-id/" + id
    )
  }

  getAll = () => {
    return httpCommon.get(
      "/order"
    )
  }

  async createOrder(data: any): Promise<ResponseReceived<boolean>> {
    return httpCommon.post("/order", data).then();
  }

  async getOrderWithPaging(
    page: number
  ): Promise<ResponseReceived<OrderDisplayPagingType>> {
    return httpCommon
      .get<ResponseReceived<OrderDisplayPagingType>>("/order/paging", {
        params: {
          page,
        },
      })
      .then();
  }
  // Lấy số lượng đơn trong tổng quan
  getCountOrderAllStatus = () => {
    return httpCommon.get("order/dieu-phoi/count-by-status");
  };
  // Lấy toàn bộ đơn hàng với phân trang
  getAllOrderByDPWithPagination = (pageIndex: String, pageSize: String): Promise<ResponseReceived<OrderDPResponse>> => {
    return httpCommon.get("/order/dieu-phoi/get-all-order", {
      params: {
        pageIndex,
        pageSize,
      }
    })
  }

  // Lấy toàn bộ đơn hàng theo trạng thái với phân trang
  getAllOrderStatusByDPWithPagination = (status: String, pageIndex?: String, pageSize?: String): Promise<ResponseReceived<OrderDPResponse>> => {
    return httpCommon.get("/order/dieu-phoi/get-all-order-by-status", {
      params: {
        status,
        pageIndex,
        pageSize,
      }
    })
  }

  //Lấy thông tin về gửi nhận vận chuyển cho chi tiết đơn hàng
  getOrderInfDetail = (orderId: Number): Promise<ResponseReceived<OrderInfDetailResponse>> => {
    return httpCommon.get("/order/dieu-phoi/order-info-detail", {
      params: {
        orderId
      }
    })
  }

  //Lấy chi tiết sản phẩm cho đơn hàng
  getProductInfDetail = (orderId: number): Promise<ResponseReceived<ProductInfInOrderByDP>> => {
    return httpCommon.get("order/dieu-phoi/get-product-list-by-orderId", {
      params: {
        orderId
      }
    })
  }

  //Gán đơn hàng cho shippper
  assignCarrier = (orderId: Number, carrierId: String) => {
    return httpCommon.put(`/order/dieu-phoi/assign-carrier?orderId=${orderId}&carrierId=${carrierId}`)
  }


  async cancelOrder(orderId: number): Promise<ResponseReceived<number>> {
    return httpCommon.delete(`/order/${orderId}`).then();
  }


  async countOrderInThirtyDays(
    startDate: string,
    endDate: string
  ): Promise<ResponseReceived<OrderInThirtyDays[]>> {
    return httpCommon
      .get("/order", {
        params: {
          startDate,
          endDate,
        },
      })
      .then();
  }

  async getOrderNewest(
    page: number
  ): Promise<ResponseReceived<OrderDisplayPagingType>> {
    return httpCommon
      .get<ResponseReceived<OrderDisplayPagingType>>("/order/sort", {
        params: {
          page,
        },
      })
      .then();
  }

  async getNotDoneYet(): Promise<ResponseReceived<OrderDisplayType[]>> {
    return httpCommon.get("/order/not-done-yet").then();
  }
}

export default new OrderService();
