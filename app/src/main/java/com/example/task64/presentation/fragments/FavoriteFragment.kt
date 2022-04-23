package example.task64.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.task64.databinding.FragmentFavoriteBinding
import com.example.task64.presentation.viewmodel.favoriteFragmentvm.FavoriteFragViewModel
import example.task64.adapter.FavoriteAdapter
import com.example.task64.data.model.ImageModel
import com.example.task64.presentation.viewmodel.favoriteFragmentvm.ImagesApplication
import com.example.task64.presentation.viewmodel.favoriteFragmentvm.FavoriteViewModelFactory

class FavoriteFragment : Fragment() {
    private lateinit var fragmentFavoriteBinding : FragmentFavoriteBinding
    lateinit var favAdapter : FavoriteAdapter
     var thumbImage : String? = null
     var idImage : Int? = null
    private val favoriteFragViewModel: FavoriteFragViewModel by activityViewModels {
        FavoriteViewModelFactory((requireActivity().application as ImagesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return fragmentFavoriteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favAdapter = FavoriteAdapter(this.requireContext())
        fragmentFavoriteBinding.favRcView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        fragmentFavoriteBinding.favRcView.adapter = favAdapter
        fragmentFavoriteBinding.favRcView.setHasFixedSize(true)

         thumbImage = arguments?.get("favorite_thumb").toString()
         idImage =  arguments?.getInt("favorite_id")
        Log.i("id_img" , idImage.toString())
         val newImage = idImage?.let { ImageModel(it.toInt(), " " , thumbImage!!) }

        newImage?.let { favoriteFragViewModel.insert(it) }

        favoriteFragViewModel.favoriteImages.observe(requireActivity() , {
            it.let {
                favAdapter.update(it)
                favAdapter.favList = it as MutableList<ImageModel>
            }
        })

        fragmentFavoriteBinding.fabDelete.setOnClickListener {
            favoriteFragViewModel.deleteAll()
            favAdapter.notifyDataSetChanged()
            fragmentFavoriteBinding.textview.visibility = View.VISIBLE
        }

    }


}