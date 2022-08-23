package com.example.urldemoapp.spanner

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.XmlResourceParser
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.urldemoapp.MainActivity
import com.example.urldemoapp.R
import com.example.urldemoapp.databinding.ActivitySpannerTvBinding
import org.xmlpull.v1.XmlPullParserException
import java.io.Serializable


class SpannerTvActivity : AppCompatActivity() {
    var mTextView: TextView? = null
    var msp: SpannableString? = null
    var chartTV :TextView? =null
    var chartSP : SpannableString?=null
    lateinit var binding:ActivitySpannerTvBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpannerTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initChartTextView()
    }
    private fun initChartTextView(){
        chartTV = binding.tvTest
        //create a spannableString object
        val tv1 = "From article: "
        val title = "Here is the title"
        val tv3 = "read more"
        var url = "https://developer.android.com/reference/android/app/Notification.Builder#setFullScreenIntent(android.app.PendingIntent,%20boolean)"
        chartSP = SpannableString("$tv1 $title $tv3")
        //set from article text font
        chartSP?.let { it ->
            //set custom family font "from article"
            val typeface = this.let { ResourcesCompat.getFont(it, R.font.graphik_semibold) }
            it.setSpan( StyleSpan(typeface!!.style),
                0, tv1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            //set text color
            val startIndex = it.length - tv3.length
            val endIndex = it.length
            it.setSpan(ForegroundColorSpan(resources.getColor(R.color.acc_dark)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色
            //set underline
            it.setSpan(UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //set url
            it.setSpan( URLSpan(url), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //sett click event
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(binding.root.context, MainActivity::class.java)
//                    intent.putExtra("PassArticle", article as Serializable)
//                    intent.putExtra("PassArticleArray", mList as Serializable)
                    binding.root.context.startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    //super.updateDrawState(ds)
                    ds.setUnderlineText(true) //是否设置下划线 false不设置
                }
            }
            it.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }

        chartTV!!.text = chartSP
        chartTV!!.movementMethod = LinkMovementMethod.getInstance()

    }

    @SuppressLint("ResourceType")
    private fun initView(){
        mTextView = binding.myTextView//(TextView)findViewById(R.id.myTextView);
        //创建一个 SpannableString对象
        msp = SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot")
//        msp.apply {
            //设置字体(default,default-bold,monospace,serif,sans-serif)
            msp!!.setSpan( TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp!!.setSpan( TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            //设置字体大小（绝对值,单位：像素）
            msp!!.setSpan(AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            msp!!.setSpan(AbsoluteSizeSpan(20,true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)  //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。

            //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
            msp!!.setSpan(RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //0.5f表示默认字体大小的一半
            msp!!.setSpan(RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍

            //设置字体前景色
            msp!!.setSpan(ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

            //设置字体背景色
            msp!!.setSpan(BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置背景色为青色

            //设置字体样式正常，粗体，斜体，粗斜体
            msp!!.setSpan(StyleSpan(android.graphics.Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
            msp!!.setSpan(StyleSpan(android.graphics.Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
            msp!!.setSpan(StyleSpan(android.graphics.Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //斜体
            msp!!.setSpan(StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗斜体

            //设置下划线
            msp!!.setSpan(UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //设置删除线
            msp!!.setSpan(StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //设置上下标
            msp!!.setSpan( SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
            msp!!.setSpan( SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标

            //超级链接（需要添加setMovementMethod方法附加响应）
            msp!!.setSpan( URLSpan("tel:4155551212"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //电话
            msp!!.setSpan( URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //邮件
            msp!!.setSpan( URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //网络
            msp!!.setSpan( URLSpan("sms:4155551212"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //短信   使用sms:或者smsto:
            msp!!.setSpan( URLSpan("mms:4155551212"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //彩信   使用mms:或者mmsto:
            msp!!.setSpan( URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //地图

            //设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
            msp!!.setSpan(ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变
            //设置字体（依次包括字体名称，字体大小，字体样式，字体颜色，链接颜色）
            var csllink : ColorStateList? = null;
            var csl : ColorStateList? = null;
            var xppcolor : XmlResourceParser =getResources().getXml(R.color.color);
            try {
                csl= ColorStateList.createFromXml(getResources(),xppcolor);
            }catch(e:XmlPullParserException){
                // TODO: handle exception
                e.printStackTrace();
            }catch( e:XmlPullParserException){
                // TODO: handle exception
                e.printStackTrace();
            }

            val xpplinkcolor=getResources().getXml(R.color.linkcolor);
            try {
                csllink= ColorStateList.createFromXml(getResources(),xpplinkcolor);
            }catch(e: XmlPullParserException){
                // TODO: handle exception
                e.printStackTrace();
            }catch(e:XmlPullParserException){
                // TODO: handle exception
                e.printStackTrace();
            }
            msp!!.setSpan(TextAppearanceSpan("monospace",android.graphics.Typeface.BOLD_ITALIC, 30, csl, csllink), 51, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //设置项目符号
            msp!!.setSpan(BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH,Color.GREEN), 0 ,msp!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色

//            //设置图片
//            val drawable = getResources().getDrawable(R.drawable.icon);
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            msp!!.setSpan( ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTextView!!.setText(msp);
            mTextView!!.setMovementMethod(LinkMovementMethod.getInstance());
    }
    fun setCustomFontTypeSpan(
        context: Context?,
        source: String?,
        startIndex: Int,
        endIndex: Int,
        font: Int
    ): SpannableString? {
        val spannableString = SpannableString(source)
        val typeface = context?.let { ResourcesCompat.getFont(it, font) }
        spannableString.setSpan(
            StyleSpan(typeface!!.style),
            startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }
}