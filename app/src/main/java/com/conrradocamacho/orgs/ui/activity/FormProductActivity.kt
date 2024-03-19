package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.databinding.ActivityFormProductBinding
import com.conrradocamacho.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    private val binding by lazy { ActivityFormProductBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        configSaveButton()
        setContentView(binding.root)
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
            price = value
        )
    }
}