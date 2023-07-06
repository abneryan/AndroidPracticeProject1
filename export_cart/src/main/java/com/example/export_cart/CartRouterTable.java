package com.example.export_cart;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/3 11:22
 * @Description:
 * 购物车组件路由表
 * 即 购物车组件中 所有可以从外部跳转的页面 的路由信息
 */
public interface CartRouterTable {
    //购物车页面
    String PATH_CART_ACTIVITY = "/cart/cartActivity";
    //购物车服务
    String PATH_CART_SERVICE = "/cart/service";
    //购物车组件fragment
    String PATH_CART_FRAGMENT_CART = "/cart/cartFragment";
}
