package com.conrradocamacho.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.database.AppDatabase
import com.conrradocamacho.orgs.databinding.ActivityDetailProductBinding
import com.conrradocamacho.orgs.extensions.formatPrice
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product

class DetailProductActivity : AppCompatActivity(R.layout.activity_detail_product) {

    companion object {
        const val PRODUCT_ID_KEY = "product_id"
        private val TAG = this::class.simpleName
    }

    private val binding by lazy { ActivityDetailProductBinding.inflate(layoutInflater) }
    private var product: Product? = null
    private val productDao by lazy { AppDatabase.getInstance(this).productDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val productId = intent.getLongExtra(PRODUCT_ID_KEY, 0L)
        product = productDao.getById(productId)
        product?.let {
            populateFields()
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_detail_product_edit -> {
                Intent(this, FormProductActivity::class.java).apply {
                    putExtra(PRODUCT_ID_KEY, product?.id ?: 0L)
                    startActivity(this)
                }
            }
            R.id.menu_detail_product_delete -> {
                product?.let { productDao.delete(it) }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateFields() {
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