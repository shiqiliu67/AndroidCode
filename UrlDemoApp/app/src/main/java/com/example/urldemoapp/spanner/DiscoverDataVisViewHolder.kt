package com.example.urldemoapp.spanner

//import android.content.Intent
//import android.graphics.Bitmap
//import android.text.Spannable
//import android.text.SpannableString
//import android.text.Spanned
//import android.text.TextPaint
//import android.text.method.LinkMovementMethod
//import android.text.style.*
//import android.util.Log
//import android.view.View
//import android.webkit.WebSettings
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.TextView
//import androidx.core.content.res.ResourcesCompat
//import androidx.core.net.toUri
//import androidx.core.view.isVisible
//import androidx.recyclerview.widget.RecyclerView
//import com.accenture.tlapp.R
//import com.accenture.tlapp.data.new_response.Item
//import com.accenture.tlapp.databinding.RowDiscoverAdapterDatavisBinding
//import com.accenture.tlapp.ui.specific_article.SpecificArticleActivity
//import com.accenture.tlapp.ui.specific_article.SpecificArticleFragment
//import com.squareup.picasso.Picasso
//import jp.wasabeef.picasso.transformations.CropSquareTransformation
//import java.io.Serializable
//import java.net.URI
//import java.net.URL
//
//class DiscoverDataVisViewHolder(var binding: RowDiscoverAdapterDatavisBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//
//    lateinit var article: Item
//    var chartTV: TextView? = null
//    var chartSP: SpannableString? = null
//    fun bind(article: Item, mList: ArrayList<Item>) {
//        try {
//            binding.layoutOther.isVisible = true
//            this.article = article
//            // binding.tvArticleTitle.text = article.title
//            binding.tvChartTag.text = "Charts"//article.content_type.capitalize()
//            setWebUrl("https://accenturemobileapps.storied.co/charts-batch-1/graph-1-2-rise-of-the-forerunners")//article.thumbnail_url
//            Log.e(
//                TAG,
//                "bind: chart:${article.thumbnail_url},${article.animated_thumbnail_url},$article",
//            )
//            setDescription(article, mList)
////            binding.tvChartLink.setOnClickListener {
////                val intent = Intent(binding.root.context, SpecificArticleActivity::class.java)
////                intent.putExtra("PassArticle", article as Serializable)
////                intent.putExtra("PassArticleArray", mList as Serializable)
////                binding.root.context.startActivity(intent)
////            }
//        } catch (e: Exception) {
//            Log.e(TAG, "bind: ${e.stackTrace}")
//        }
//    }
//
//    private fun setDescription(article: Item, mList: ArrayList<Item>) {
//        chartTV = binding.tvChartDes
//        //create a spannableString object
//        val tv1 = "From article: "
//        val title = article.title
//        val tv3 = "Read more"
//        var url = article.url
//        chartSP = SpannableString("$tv1 $title $tv3")
//        //set from article text font
//        chartSP?.let { it ->
//            //set custom family font "from article"
//            val typeface =
//                this.let { ResourcesCompat.getFont(binding.root.context, R.font.graphik_semibold) }
//            it.setSpan(
//                StyleSpan(typeface!!.style),
//                0, tv1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
//            //set text color
//            val startIndex = it.length - tv3.length
//            val endIndex = it.length
//            it.setSpan(
//                ForegroundColorSpan(binding.root.context.resources.getColor(R.color.acc_dark)),
//                startIndex,
//                endIndex,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//            );  //设置前景色为洋红色
//            //set underline
//            it.setSpan(UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            //set url
////            it.setSpan(URLSpan(url), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            val clickableSpan = object : ClickableSpan() {
//                override fun onClick(widget: View) {
//                    val intent = Intent(binding.root.context, SpecificArticleActivity::class.java)
//                    intent.putExtra("PassArticle", article as Serializable)
//                    intent.putExtra("PassArticleArray", mList as Serializable)
//                    binding.root.context.startActivity(intent)
//                }
//
//                override fun updateDrawState(ds: TextPaint) {
//                    //super.updateDrawState(ds)
//                    ds.setUnderlineText(true) //是否设置下划线 false不设置
//                }
//            }
//            it.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//
//        }
//
//
//        chartTV!!.text = chartSP
//        chartTV!!.movementMethod = LinkMovementMethod.getInstance()
//    }
//
//    private fun setWebUrl(url: String) {
//        val settings: WebSettings = binding.chartWebview.settings
//        settings.let {
//            it.setRenderPriority(WebSettings.RenderPriority.HIGH)
//            it.javaScriptEnabled = true
//            it.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//            it.domStorageEnabled = true
//        }
//        binding.chartWebview.loadUrl(url)
//        Log.e(TAG, "setWebUrl: $url")
//        binding.chartWebview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view.loadUrl(url)
//                return true
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                binding.layoutChart.requestLayout()
//            }
//
//
//            override fun onLoadResource(view: WebView?, url: String?) {
//                super.onLoadResource(view, url)
//            }
//
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                super.onPageStarted(view, url, favicon)
//            }
//        }
//    }
//
//    companion object {
//        private val TAG = "DiscoverDataVisViewHolder"
//    }
//}