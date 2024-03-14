package com.conrradocamacho.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.conrradocamacho.orgs.R
import com.conrradocamacho.orgs.model.Product

class ProductListAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.name)
            name.text = product.name
            val description = itemView.findViewById<TextView>(R.id.description)
            description.text = product.description
            val price = itemView.findViewById<TextView>(R.id.price)
            price.text = product.price.toPlainString()
        }
    }

}
