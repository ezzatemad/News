package com.example.news.article

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.news.R
import com.example.news.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        // Hide toolbar icons based on your logic
        val toolBarMenu = activity?.findViewById<ImageView>(R.id.ic_menu)
        val toolBarSearch = activity?.findViewById<ImageView>(R.id.main_ic_search)
        val toolBarArrow = activity?.findViewById<ImageView>(R.id.ic_back)

        toolBarArrow?.setOnClickListener {
            findNavController().navigateUp()
        }




        toolBarMenu?.visibility = View.GONE
        toolBarSearch?.visibility = View.GONE
        toolBarArrow?.visibility = View.VISIBLE

        // Bind article information to UI
        binding.articleTitleTv.text = args.articleInfo?.author
        binding.articleDescTv.text = args.articleInfo?.description
        binding.articleDateTv.text = args.articleInfo?.publishedAt

        // Load the image using Glide
        val imageView = binding.articleIv
        val lottieAnimationView = binding.lottieAnimationView

        lottieAnimationView.visibility = View.VISIBLE

        Glide.with(requireContext())
            .load(args.articleInfo?.urlToImage)
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
            })
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
