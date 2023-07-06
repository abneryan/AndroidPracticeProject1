package com.example.export_cart;


import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/3 11:14
 * @Description:购物车组件对外暴露的服务 必须继承IProvider
 */
public interface ICartService extends IProvider {
    /**
     * 获取购物车中商品数量
     * @return
     */
    CartInfo getProductCountInCart();
}
