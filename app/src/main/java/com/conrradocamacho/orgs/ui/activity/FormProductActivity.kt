package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.databinding.ActivityFormProductBinding
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product
import com.conrradocamacho.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    private val binding by lazy { ActivityFormProductBinding.inflate(layoutInflater) }
    private var url: String? = null

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
    }

    private fun configSaveButton() {
        val save = binding.formProductSave
        val dao = ProductDAO()
        save.setOnClickListener {
            val product = createProduct()
            dao.add(product)
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
            name = nameEdit.text.toString(),
            description = descriptionEdit.text.toString(),
            price = value,
            image = url
        )
    }
}