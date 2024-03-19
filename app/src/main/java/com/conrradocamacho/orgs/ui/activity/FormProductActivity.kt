package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.dao.ProductDAO
import com.conrradocamacho.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        configSaveButton()
    }

    private fun configSaveButton() {
        val save = findViewById<Button>(R.id.form_product_save)
        val dao = ProductDAO()
        save.setOnClickListener {
            val product = createProduct()
            dao.add(product)
            finish()
        }
    }

    private fun createProduct(): Product {
        val nameEdit = findViewById<EditText>(R.id.form_product_name_edit)
        val descriptionEdit = findViewById<EditText>(R.id.form_product_description_edit)
        val priceEdit = findViewById<EditText>(R.id.form_product_price_edit)
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