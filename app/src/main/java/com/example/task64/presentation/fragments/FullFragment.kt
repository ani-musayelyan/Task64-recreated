package example.task64.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.bumptech.glide.Glide
import com.example.task64.presentation.FragmentsTrans
import com.example.task64.databinding.FragmentFullBinding

class FullFragment : Fragment() {
//    private lateinit var  favoriteFragViewModel: FavoriteFragViewModel
    private lateinit var fragmentFullBinding : FragmentFullBinding
    private lateinit var fragmentsTrans: FragmentsTrans

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFullBinding = FragmentFullBinding.inflate(inflater, container, false)

        fragmentsTrans = activity as FragmentsTrans

        val url = arguments?.get("url")
        val thumb = arguments?.get("thumb")
        val id = arguments?.getInt("id")
        Log.i("fullog", id.toString())

        Glide.with(requireContext())
            .load(url)
            .centerCrop()
            .into(fragmentFullBinding.fullImgView)

        fragmentFullBinding.addToFavBtn.setOnClickListener {
//            favoriteFragViewModel.imageThumbUrl.value = thumb.toString()
//            favoriteFragViewModel.imageId.value = id.toString().toInt()
            val bundle = Bundle()
            bundle.putString("favorite_thumb", thumb.toString())
            bundle.putInt("favorite_id", id!!)
            val favoriteFragment = FavoriteFragment()
            favoriteFragment.arguments = bundle
            fragmentsTrans.transact(favoriteFragment)
        }



            return fragmentFullBinding.root
    }


}