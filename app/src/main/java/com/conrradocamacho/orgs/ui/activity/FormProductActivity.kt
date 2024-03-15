package com.conrradocamacho.orgs.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.conrradocamacho.orgs.R

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}