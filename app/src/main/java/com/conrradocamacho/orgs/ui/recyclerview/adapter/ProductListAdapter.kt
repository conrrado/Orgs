package com.conrradocamacho.orgs.ui.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.databinding.ProductItemBinding
import com.conrradocamacho.orgs.extensions.formatPrice
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product

class ProductListAdapter(
    products: List<Product>,
    var onClickProductItem: (product: Product) -> Unit = {}
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

    inner class ViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val image = binding.productItemImage
        private val name = binding.productItemName
        private val descriptionItem = binding.productItemDescription
        private val price = binding.productItemPrice

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                Log.i(ProductListAdapter::class.simpleName, "Click on item")
                if (::product.isInitialized) {
                    onClickProductItem(product)
                }
            }
        }

        fun bind(product: Product) {
            this.product = product
            name.text = product.name
            descriptionItem.text = product.description
            price.formatPrice(product.price)
            image.tryLoadingImage(product.image)
        }
    }
}
