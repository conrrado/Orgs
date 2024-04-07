package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.databinding.ActivityDetailProductBinding
import com.conrradocamacho.orgs.extensions.formatPrice
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product

class DetailProductActivity : AppCompatActivity(R.layout.activity_detail_product) {

    private val binding by lazy { ActivityDetailProductBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val product: Product? = intent.getParcelableExtra("product", Product::class.java)
        populateFields(product)
    }

    private fun populateFields(product: Product?) {
        product?.let {
            binding.apply {
                detailProductImage.tryLoadingImage(it.image)
                detailProductPrice.formatPrice(it.price)
                detailProductTitle.text = it.name
                detailProductDescription.text = it.description
            }

        }
    }
}