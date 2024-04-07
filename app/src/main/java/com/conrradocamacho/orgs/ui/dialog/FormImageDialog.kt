package com.conrradocamacho.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.conrradocamacho.orgs.databinding.FormImageBinding
import com.conrradocamacho.orgs.extensions.tryLoadingImage

class FormImageDialog(private val context: Context) {

    fun show(
        defaultUrl: String? = null,
        whenImageLoaded: (url: String) -> Unit
    ) {
        val layoutInflater = LayoutInflater.from(context)
        FormImageBinding.inflate(layoutInflater).apply {

            defaultUrl?.let {
                formImageImageview.tryLoadingImage(it)
                formImageUrlEdit.setText(it)
            }

            formImageButtonRefresh.setOnClickListener {
                val url = formImageUrlEdit.text.toString()
                formImageImageview.tryLoadingImage(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formImageUrlEdit.text.toString()
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancelar") {_, _ -> }
                .show()
        }
    }
}