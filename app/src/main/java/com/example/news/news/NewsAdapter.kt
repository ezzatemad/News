package com.example.news.news


import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.model.news.Articles
import com.example.news.R
import com.example.news.databinding.NewsItemLayoutBinding

class NewsAdapter(
    var context: Context,
    var articlesList: List<Articles?>? = null,
    var newItemClickListener: NewsItemClickListener
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = articlesList!![position]
        if (item != null) {
            holder.bind(item)
        }

        val imageView = holder.itemView.findViewById<ImageView>(R.id.new_item_iv)
        val lottieAnimationView =
            holder.itemView.findViewById<LottieAnimationView>(R.id.item_lottieAnimationView)


        Log.d("TAG", "onBindViewHolder: ${item?.urlToImage}")

        // Start animation
        lottieAnimationView.visibility = View.VISIBLE
//        imageView.visibility = View.GONE

        Glide.with(context)
            .load(item?.urlToImage)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    // Hide the loading animation on load failure
                    lottieAnimationView.visibility = View.GONE
                    imageView.visibility = View.VISIBLE
                    return false // Return false to allow Glide to handle the error placeholder
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    // Hide the loading animation on resource ready
                    lottieAnimationView.visibility = View.GONE
                    imageView.visibility = View.VISIBLE
                    return false // Return false to allow Glide to handle the resource
                }
            }).into(imageView)


        holder.itemView.setOnClickListener {
            if (item != null) {
                newItemClickListener.newItemClick(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return articlesList?.size ?: 0
    }

    fun bindNewNews(newData: List<Articles?>) {
        articlesList = newData
        notifyDataSetChanged()
    }


    inner class NewsViewHolder(var binding: NewsItemLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Articles) {
            binding.newsArticle = item
            binding.executePendingBindings()
        }
    }
}
