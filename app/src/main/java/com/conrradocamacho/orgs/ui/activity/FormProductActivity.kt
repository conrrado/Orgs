package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import android.util.Log
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

        val nameEdit = findViewById<EditText>(R.id.nameEdit)
        val descriptionEdit = findViewById<EditText>(R.id.descriptionEdit)
        val priceEdit = findViewById<EditText>(R.id.priceEdit)

        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener {

            val stringValue = priceEdit.text.toString()
            val value = if (stringValue.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(stringValue)
            }

            val product = Product(
                name = nameEdit.text.toString(),
                description = descriptionEdit.text.toString(),
                price = value
            )

            Log.i("FormProductActivity", "onCreate: $product")
            val dao = ProductDAO()
            dao.add(product)
            Log.i("FormProductActivity", "onCreate: ${dao.searchAll()}")
            finish()
        }
    }
}