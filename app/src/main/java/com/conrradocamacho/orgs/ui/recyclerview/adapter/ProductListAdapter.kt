package com.conrradocamacho.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.databinding.ProductItemBinding
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ProductListAdapter(
    products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size
    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val image = binding.productItemImage
        private val name = binding.productItemName
        private val descriptionItem = binding.productItemDescription
        private val price = binding.productItemPrice

        fun bind(product: Product) {
            name.text = product.name
            descriptionItem.text = product.description
            price.text = formatPrice(product.price)
            image.tryLoadingImage(product.image)
        }

        private fun formatPrice(price: BigDecimal): String {
            val formatter: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return formatter.format(price)
        }
    }

}
