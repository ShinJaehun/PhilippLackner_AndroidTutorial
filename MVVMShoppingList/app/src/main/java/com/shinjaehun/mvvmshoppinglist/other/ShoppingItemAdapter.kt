package com.shinjaehun.mvvmshoppinglist.other

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shinjaehun.mvvmshoppinglist.R
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.databinding.ShoppingItemBinding
import com.shinjaehun.mvvmshoppinglist.ui.shoppinglist.ShoppingViewModel

const val TAG = "ShoppingItemAdapter"
class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
//    private lateinit var binding: ShoppingItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
//        binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ShoppingViewHolder(binding)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        // 헐 씨발... findviewbyid 적용하니까 이상하게 동작하던 문제가 싹 사라지네.....
        val currentShoppingItem = items[position]

        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
        tvName.text = currentShoppingItem.name

        val tvAmount = holder.itemView.findViewById<TextView>(R.id.tvAmount)
        tvAmount.text = "${currentShoppingItem.amount}"

        val ivDelete = holder.itemView.findViewById<ImageView>(R.id.ivDelete)
        ivDelete.setOnClickListener {
           Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
           viewModel.deleteItem(currentShoppingItem)
        }

        val ivPlus = holder.itemView.findViewById<ImageView>(R.id.ivPlus)
        ivPlus.setOnClickListener {
            Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
            currentShoppingItem.amount++
            viewModel.upsertItem(currentShoppingItem)
        }

        val ivMinus = holder.itemView.findViewById<ImageView>(R.id.ivMinus)
        ivMinus.setOnClickListener {
            Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
            currentShoppingItem.amount--
            viewModel.upsertItem(currentShoppingItem)
        }

//        holder.itemView.apply {
//            binding.tvName.text = currentShoppingItem.name
//            binding.tvAmount.text = "${currentShoppingItem.amount}"
//            binding.ivDelete.setOnClickListener {
//                Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
//                viewModel.delete(currentShoppingItem)
//            }
//            binding.ivPlus.setOnClickListener {
//                Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
//                currentShoppingItem.amount++
//                viewModel.upsert(currentShoppingItem)
//            }
//            binding.ivMinus.setOnClickListener {
//                Log.i(TAG, "current shopping item is ${currentShoppingItem.name}")
//                if (currentShoppingItem.amount > 0) {
//                    currentShoppingItem.amount--
//                    viewModel.upsert(currentShoppingItem)
//                }
//            }
//        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
//    inner class ShoppingViewHolder(binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)
    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}