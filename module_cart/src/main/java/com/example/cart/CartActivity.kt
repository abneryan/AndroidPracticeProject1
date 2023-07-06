package com.example.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.constant.ARouterConstants
import com.example.export_cart.CartRouterTable
import com.example.module_cart.R
import com.example.module_cart.databinding.ActivityCartBinding

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/2 17:15
 * @Description:
 */
@Route(path = CartRouterTable.PATH_CART_ACTIVITY)
class CartActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart)
        dataBinding.lifecycleOwner =this
        dataBinding.commonTitel.tvTitel.text = "Cart"
        dataBinding.commonTitel.ivBack.setOnClickListener{
            finish()
        }
    }
}