package com.example.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.constant.ARouterConstants
import com.example.export_cart.CartRouterTable
import com.example.export_cart.CartServiceUtil
import com.example.module_home.R
import com.example.module_home.databinding.ActivityHomeBinding

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/2 17:15
 * @Description:
 */
@Route(path = ARouterConstants.HOME_ACTIVITY)
class HomeActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        dataBinding.lifecycleOwner = this
        dataBinding.commonTitel.tvTitel.text = "Home"
        dataBinding.commonTitel.ivBack.setOnClickListener {
            finish()
        }
        dataBinding.tvGoCart.setOnClickListener {
            //通过路由跳转到 购物车组件的购物车页面（但没有依赖购物车组件）
            CartServiceUtil.navigateCartPage("1", "2")
        }
        dataBinding.tvCartProductCount.text =
            "购物车中商品数量:${CartServiceUtil.getCartProductCount().productCount}"

        var manager: FragmentManager = getSupportFragmentManager()
        var transaction: FragmentTransaction = manager.beginTransaction();

        //使用ARouter获取Fragment实例 并添加
        var userFragment: Fragment =
            ARouter.getInstance().build(CartRouterTable.PATH_CART_FRAGMENT_CART)
                .navigation() as Fragment
        transaction.add(R.id.fl_test_frament, userFragment, "tag")
        transaction.commit()
    }
}