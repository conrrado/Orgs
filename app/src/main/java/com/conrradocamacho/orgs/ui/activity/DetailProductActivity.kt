package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.database.AppDatabase
import com.conrradocamacho.orgs.databinding.ActivityDetailProductBinding
import com.conrradocamacho.orgs.extensions.formatPrice
import com.conrradocamacho.orgs.extensions.tryLoadingImage

class DetailProductActivity : AppCompatActivity(R.layout.activity_detail_product) {

    private val binding by lazy { ActivityDetailProductBinding.inflate(layoutInflater) }
    private val TAG = this::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val productId: Long = intent.getLongExtra("product_id", 0L)
        populateFields(productId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_detail_product_edit -> {
                Log.i(TAG, "onOptionsItemSelected edit")
            }
            R.id.menu_detail_product_delete -> {
                Log.i(TAG, "onOptionsItemSelected delete")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateFields(productId: Long) {
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        val product = productDao.getById(productId)
        product.let {
            binding.apply {
                detailProductImage.tryLoadingImage(it.image)
                detailProductPrice.formatPrice(it.price)
                detailProductTitle.text = it.name
                detailProductDescription.text = it.description
            }

        }
    }
}