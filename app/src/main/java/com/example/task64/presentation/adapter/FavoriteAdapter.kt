package example.task64.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.task64.data.model.ImageModel
import com.example.task64.R

class FavoriteAdapter(private val context : Context) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    var favList = mutableListOf<ImageModel>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val favorite_image = itemView.findViewById<ImageView>(R.id.imageView)
        val favorite_imageId = itemView.findViewById<TextView>(R.id.image_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.favorite_imageId.text = favList[position].id.toString()

        Glide.with(context)
            .load(favList[position].thumb)
            .apply(RequestOptions().centerCrop())
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.favorite_image)


    }

    override fun getItemCount(): Int {
        Log.i("size" , favList.size.toString())
            return favList.size
    }

       fun update(list: List<ImageModel>) {
           favList.addAll(list)
           favList.clear()
         notifyDataSetChanged()

    }


}