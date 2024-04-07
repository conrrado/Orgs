package com.conrradocamacho.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.conrradocamacho.orgs.databinding.FormImageBinding
import com.conrradocamacho.orgs.extensions.tryLoadingImage

class FormImageDialog(private val context: Context) {

    fun show(whenImageLoaded: (url: String) -> Unit) {
        val layoutInflater = LayoutInflater.from(context)
        val binding = FormImageBinding.inflate(layoutInflater)
        binding.formImageButtonRefresh.setOnClickListener {
            val url = binding.formImageUrlEdit.text.toString()
            binding.formImageImage.tryLoadingImage(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formImageUrlEdit.text.toString()
                Log.i(this.javaClass::getSimpleName.toString(), "show: $url")
                whenImageLoaded(url)
            }
            .setNegativeButton("Cancelar") {_, _ -> }
            .show()
    }
}