package com.shinjaehun.mvvmnewsapp.adapters

import android.content.ClipData.Item
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shinjaehun.mvvmnewsapp.R
import com.shinjaehun.mvvmnewsapp.databinding.ItemArticlePreviewBinding
import com.shinjaehun.mvvmnewsapp.models.Article
import com.shinjaehun.mvvmnewsapp.models.NewsResponse
import com.shinjaehun.mvvmnewsapp.util.Resource
//class NewsAdapter(private var news: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private lateinit var binding: ItemArticlePreviewBinding

    // view binding을 해야 하기 때문에...
//    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    inner class ArticleViewHolder(binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root)

//    굳이 이렇게 differCallback을 사용할 필요는 없지 않을까?
//    일단 article을 view에 표현하는 것은 성공했음
//    뭔가 다른 게 또 있을지도...
    private val differCallback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        return ArticleViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_article_preview,
//                parent,
//                false
//            )
//        )
        binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
//        return news.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
//        val article = news[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(binding.ivArticleImage)
            binding.tvSource.text = article.source.name
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClkickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}