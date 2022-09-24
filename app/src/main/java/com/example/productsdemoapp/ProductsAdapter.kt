package com.example.productsdemoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productsdemoapp.database.ProductEntity
import com.example.productsdemoapp.databinding.ListItemBinding

class ProductsAdapter (val clickListener:  ClickListener) : ListAdapter<ProductEntity, ProductsAdapter.ViewHolder>(MainAdapterCallback()) {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(viewGroup)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind(getItem(position)!!,clickListener)

    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder private constructor(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductEntity, clickListener:  ClickListener) {
            binding.entity = item
            binding.adapterClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(view)
            }
        }
    }


}

class MainAdapterCallback : DiffUtil.ItemCallback<ProductEntity>() {
    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem.id == newItem.id
    }

}

class  ClickListener(val clickListener: (pro:ProductEntity) -> Unit) {
    fun onItemClick(pro: ProductEntity) = clickListener(pro)
}
