package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.database.AppDatabase
import com.conrradocamacho.orgs.databinding.ActivityFormProductBinding
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    private val binding by lazy { ActivityFormProductBinding.inflate(layoutInflater) }
    private var url: String? = null
    private var productId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        title = "Cadastrar produto"
        configSaveButton()

        binding.formProductImage.setOnClickListener {
            FormImageDialog(this).show(url) {
                url = it
                binding.formProductImage.tryLoadingImage(url)
            }
        }

        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        productId = intent.getLongExtra(DetailProductActivity.PRODUCT_ID_KEY, 0L)
        if (productId > 0L) {
            productDao.getById(productId).let { loadedProduct ->
                title = "Alterar produto"
                url = loadedProduct.image
                binding.formProductImage.tryLoadingImage(url)
                binding.formProductNameEdit.setText(loadedProduct.name)
                binding.formProductDescriptionEdit.setText(loadedProduct.description)
                binding.formProductPriceEdit.setText(loadedProduct.price.toPlainString())
            }
        }
    }

    private fun configSaveButton() {
        val save = binding.formProductSave
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        save.setOnClickListener {
            val newProduct = createProduct()
            if (productId > 0L) {
                productDao.update(newProduct)
            } else {
                productDao.save(newProduct)
            }
            finish()
        }
    }

    private fun createProduct(): Product {
        val nameEdit = binding.formProductNameEdit
        val descriptionEdit = binding.formProductDescriptionEdit
        val priceEdit = binding.formProductPriceEdit
        val stringValue = priceEdit.text.toString()
        val value = if (stringValue.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(stringValue)
        }

        return Product(
            id = productId,
            name = nameEdit.text.toString(),
            description = descriptionEdit.text.toString(),
            price = value,
            image = url
        )
    }
}