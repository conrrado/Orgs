package com.conrradocamacho.orgs.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.databinding.ProductItemBinding
import com.conrradocamacho.orgs.extensions.formatPrice
import com.conrradocamacho.orgs.extensions.tryLoadingImage
import com.conrradocamacho.orgs.model.Product

class ProductListAdapter(
    products: List<Product> = emptyList(),
    var onClickProductItem: (product: Product) -> Unit = {},
    var onMenuProductEdit: (product: Product) -> Unit = {},
    var onMenuProductDelete: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val products = products.toMutableList()
    private val TAG = this::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, parent.context)
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

    inner class ViewHolder(binding: ProductItemBinding, context: Context):
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private val image = binding.productItemImage
        private val name = binding.productItemName
        private val descriptionItem = binding.productItemDescription
        private val price = binding.productItemPrice

        private lateinit var product: Product

        init {
            itemView.setOnClickListener {
                Log.i(TAG, "Click on item")
                if (::product.isInitialized) {
                    onClickProductItem(product)
                }
            }
            // in this listener you need to pass the value true, which means that it does not call
            // other click listeners like "onClickListener"
            itemView.setOnLongClickListener {
                Log.i(TAG, "Long click on item")
                if (::product.isInitialized) {
                    PopupMenu(context, itemView).apply {
                        setOnMenuItemClickListener(this@ViewHolder)
                        inflate(R.menu.menu_detail_product)
                        show()
                    }
                }
                true
            }
        }

        fun bind(product: Product) {
            this.product = product
            name.text = product.name
            descriptionItem.text = product.description
            price.formatPrice(product.price)
            image.tryLoadingImage(product.image)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return when(item?.itemId) {
                R.id.menu_detail_product_edit -> {
                    Log.i(TAG, "menu edit, product name: ${product.name}")
                    onMenuProductEdit(product)
                    true
                }
                R.id.menu_detail_product_delete -> {
                    Log.i(TAG, "menu delete, product name: ${product.name}")
                    onMenuProductDelete(product)
                    true
                }
                else -> false
            }
        }


    }
}
