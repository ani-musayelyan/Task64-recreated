package example.task64.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import example.task64.fragments.FullFragment
import com.example.task64.data.model.ImageModel
import com.example.task64.R

class ImageAdapter(private val context : Context) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    var imagesList = mutableListOf<ImageModel>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val imageId = itemView.findViewById<TextView>(R.id.image_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageId.text = imagesList[position].id.toString()

        val originalUrl = GlideUrl(
            imagesList[position].original , LazyHeaders.Builder()
                .addHeader("User-Agent" , "5")
                .build()
        )


        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val activity = p0!!.context as AppCompatActivity
                val fullFragment = FullFragment()
                val bundle = Bundle()
                bundle.putString("url" , imagesList[position].original)
                bundle.putString("thumb" , imagesList[position].thumb)
                bundle.putInt("id" , imagesList[position].id)
                fullFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .add(R.id.placeholder,fullFragment)
                    .addToBackStack(null)
                    .commit()
              //  activity.supportFragmentManager.popBackStack()
            }

        })


        val imageUrl = GlideUrl(
            imagesList[position].thumb , LazyHeaders.Builder()
                .addHeader("User-Agent" , "5")
                .build()
        )
                Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().centerCrop())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)


    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

}