package com.example.export_cart;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/3 11:24
 * @Description:  购物车组件服务工具类，其他组件直接使用此类即可：页面跳转、获取服务。
 */
public class CartServiceUtil {

    /**
     * 跳转到购物车界面
     * @param param1
     * @param param2
     */
    public static void navigateCartPage(String param1, String param2){
        ARouter.getInstance()
                .build(CartRouterTable.PATH_CART_ACTIVITY)
                .withString("key1",param1)
                .withString("key2",param2)
                .navigation();
    }

    /**
     * 获取服务
     * @return
     */
    private static ICartService getService(){
        //return ARouter.getInstance().navigation(ICartService.class);//如果只有一个实现，这种方式也可以
        return (ICartService) ARouter.getInstance().build(CartRouterTable.PATH_CART_SERVICE).navigation();
    }

    /**
     *  获取购物车中商品数量
     * @return
     */
    public static CartInfo getCartProductCount(){
        return getService().getProductCountInCart();
    }
}
