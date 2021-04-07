package com.example.diverseapp.Fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diverseapp.Adapters.IsSelectedServiceAdapterWithoutIcon
import com.example.diverseapp.Adapters.SliderServiceAdapter
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.fragment_carousel.isSelectedRecyclerview
import kotlinx.android.synthetic.main.fragment_carousel.testSearchView
import kotlinx.android.synthetic.main.fragment_slider.*
import java.util.concurrent.CopyOnWriteArrayList


class SliderFragment : Fragment() {

    var isUp = false

    companion object {
        fun getInstance(): SliderFragment {
            val fragment =
                SliderFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    //Initialize the selected items adapter for the recyclerview that doesn't contain an icon for each selected item
    private lateinit var isSelectedServiceAdapterWithoutIcon: IsSelectedServiceAdapterWithoutIcon

    //Initialize the adapter for the grid recyclerview that contains the items that have not been selected
    private lateinit var isNotSelectedServiceGridAdapter: SliderServiceAdapter

    //The arraylist for the items in the grid recyclerview
    private var services = CopyOnWriteArrayList<Service>()

    //The arraylist for the selected items recyclerview that the user populates with the onclick
    private var services2 = CopyOnWriteArrayList<Service>()

    //An arraylist containing more items than the others, this was for demonstration purposes and was used for the searchview suggestions
    //This was used to prove that the items in the suggestions can be different from the items in the other recyclerview
//    private var allServices = CopyOnWriteArrayList<Service>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutToBeAnimated.setVisibility(View.INVISIBLE)
        isUp = false
        setRecyclerViews()
        setSearchView()
    }

    //Method to raise the slider containing the bottom recyclerview
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)

    }

    //Method to lower the slider containing the bottom recyclerview
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    private fun setSearchView() {

        testSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (TextUtils.isEmpty(query)) {
                    slideDown(layoutToBeAnimated)
                } else
                    slideUp(layoutToBeAnimated)

                return true
            }
        })

    }

    private fun setRecyclerViews() {

        //Populate the arraylist of the bottom grid recyclerview
        services.add(Service(1, "Mechanic", false, R.drawable.mechanicimage))//mechanic
        services.add(Service(2, "Repair", false, R.drawable.mechanicimage))//repair
        services.add(Service(3, "Gardener", false, R.drawable.mechanicimage))//gardener
        services.add(Service(4, "Housekeeper", false, R.drawable.mechanicimage))//housekeeper

//        allServices.add(Service(1, "Mechanic", false, R.drawable.mechanicimage))//mechanic
//        allServices.add(Service(2, "Repair", false, R.drawable.mechanicimage))//repair
//        allServices.add(Service(3, "Gardener", false, R.drawable.mechanicimage))//gardener
//        allServices.add(Service(4, "Housekeeper", false, R.drawable.mechanicimage))//housekeeper
//        allServices.add(Service(5, "Painter", false, R.drawable.mechanicimage))
//        allServices.add(Service(6, "Window Repair", false, R.drawable.mechanicimage))
//        allServices.add(Service(7, "Small Appliance Repair", false, R.drawable.mechanicimage))
//        allServices.add(Service(8, "Tile Installation", false, R.drawable.mechanicimage))
//        allServices.add(Service(9, "Electrician", false, R.drawable.mechanicimage))
//        allServices.add(Service(10, "Construction Worker", false, R.drawable.mechanicimage))

        //Setup adapter for the selected items recyclerview at the top
        isSelectedServiceAdapterWithoutIcon = IsSelectedServiceAdapterWithoutIcon(services2)
        isSelectedRecyclerview?.layoutManager =
            GridLayoutManager(context, 3)
        isSelectedRecyclerview?.adapter = isSelectedServiceAdapterWithoutIcon

        //Setup the adapter for the bottom recyclerview
        isNotSelectedServiceGridAdapter = SliderServiceAdapter(services)
        bubbleGridRecyclerview?.layoutManager =
            GridLayoutManager(context, 2)
        bubbleGridRecyclerview?.adapter = isNotSelectedServiceGridAdapter

        //Remove an item from the selected items recyclerview after clicking on the X icon
        isSelectedServiceAdapterWithoutIcon.setOnSettingSelectedListener(object :
            IsSelectedServiceAdapterWithoutIcon.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                !service.isSelected
                services2.remove(service)
                isSelectedServiceAdapterWithoutIcon.notifyDataSetChanged()

            }
        })

        //After clicking on an item in the grid recyclerview, add that item to the selected items recyclerview if it doesn't already exist
        isNotSelectedServiceGridAdapter.setOnSettingSelectedListener(object :
            SliderServiceAdapter.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                if (!service.isSelected) {
                    if (services2.contains(service))
                        return
                    else {
                        services2.add(
                            Service(
                                service.service_id,
                                service.service_name,
                                service.isSelected,
                                service.imageResource
                            )
                        )
                    }
                    isSelectedServiceAdapterWithoutIcon.notifyDataSetChanged()
                }
            }
        })

    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let {
            activity?.hideKeyboard(it)
        }
    }

}