package com.example.diverseapp.Fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.IsNotSelectedServiceAdapter
import com.example.diverseapp.Adapters.IsSelectedServiceAdapterWithoutIcon
import com.example.diverseapp.Adapters.SliderServiceAdapter
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import com.example.diverseapp.Utils.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_categories.*
import kotlinx.android.synthetic.main.fragment_carousel.testSearchView
import kotlinx.android.synthetic.main.fragment_slider.*
import java.util.concurrent.CopyOnWriteArrayList

//The middle fragment in the bottomnavbar
class AllCategoriesFragment : Fragment() {

    companion object {
        fun getInstance(): AllCategoriesFragment {
            val fragment =
                AllCategoriesFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    //Initialize the adapter for the first recyclerview at the top with the selected items
    private lateinit var allCategoriesSelectedServiceAdapter: IsSelectedServiceAdapterWithoutIcon

    //Initialize the adapter for the horizontal recyclerview in the middle
    private lateinit var allCategoriesCarouselAdapter: IsNotSelectedServiceAdapter

    //Initialize the adapter for the recyclerview at the bottom
    private lateinit var allCategoriesSliderAdapter: SliderServiceAdapter

    //Initialize the Arraylist for the middle horizontal recyclerview
    private var carouselNotSelectedServices = CopyOnWriteArrayList<Service>()

    //Initialize the Arraylist for the first recyclerview with the selected items
    private var selectedServices = CopyOnWriteArrayList<Service>()

    //Initialize the arraylist for the bottom recyclerview
    private var sliderNotSelectedServices = CopyOnWriteArrayList<Service>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call method to setup recyclerviews in onviewcreated
        setRecyclerViews()

        //Call method to setup searchview in onviewcreated
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

    // slide the view back down
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

        allCategoriesSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                //After starting to write some text prompt a scroll to the bottom of the screen
                scrollToBeUsed.fullScroll(View.FOCUS_DOWN)

                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //Scroll the user down immediately after typing to change the focus of the screen
                scrollToBeUsed.fullScroll(View.FOCUS_DOWN)

                //In case the user has deleted his selection slide down the bottom recyclerview and scroll the user up to see the latest selection
                if (TextUtils.isEmpty(query)) {
                    slideDown(allCategorieslayoutToBeAnimated)
                    scrollToBeUsed.fullScroll(View.FOCUS_UP)
                }

                else
                    //If the search isn't empty just raise the view containing the bottom recyclerview
                    slideUp(allCategorieslayoutToBeAnimated)
                return true
            }
        })
    }

    private fun setRecyclerViews() {

        //Populate the ArrayList for the carousel horizontal recyclerview
        carouselNotSelectedServices.add(
            Service(
                1,
                "Mechanic",
                false,
                R.drawable.mechanicimage
            )
        )
        carouselNotSelectedServices.add(
            Service(
                2,
                "Repair",
                false,
                R.drawable.maintenanceimage
            )
        )
        carouselNotSelectedServices.add(
            Service(
                3,
                "Gardener",
                false,
                R.drawable.lawnmowingimage
            )
        )
        carouselNotSelectedServices.add(
            Service(
                4,
                "Housekeeper",
                false,
                R.drawable.vacuumimage
            )
        )

        //Populate the ArrayList of the bottom recyclerview contained in the slider
        sliderNotSelectedServices.add(
            Service(
                1,
                "Mechanic",
                false,
                R.drawable.mechanicimage
            )
        )
        sliderNotSelectedServices.add(
            Service(
                2,
                "Repair",
                false,
                R.drawable.maintenanceimage
            )
        )
        sliderNotSelectedServices.add(
            Service(
                3,
                "Gardener",
                false,
                R.drawable.lawnmowingimage
            )
        )
        sliderNotSelectedServices.add(
            Service(
                4,
                "Housekeeper",
                false,
                R.drawable.vacuumimage
            )
        )
        sliderNotSelectedServices.add(
            Service(
                4,
                "Housekeeper",
                false,
                R.drawable.vacuumimage
            )
        )
        sliderNotSelectedServices.add(
            Service(
                4,
                "Housekeeper",
                false,
                R.drawable.vacuumimage
            )
        )

        //Setup the adapter for the first recyclerview with the selected items
        allCategoriesSelectedServiceAdapter = IsSelectedServiceAdapterWithoutIcon(selectedServices)
        isSelectedAllRecyclerview?.layoutManager =
            GridLayoutManager(context, 3)
        isSelectedAllRecyclerview?.adapter = allCategoriesSelectedServiceAdapter

        //Setup the adapter for the middle recyclerview with the carousel
        allCategoriesCarouselAdapter = IsNotSelectedServiceAdapter(carouselNotSelectedServices)
        carouselAllRecyclerview?.layoutManager =
            CustomLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        carouselAllRecyclerview?.adapter = allCategoriesCarouselAdapter

        //Setup the adapter for the bottom recyclerview
        allCategoriesSliderAdapter = SliderServiceAdapter(sliderNotSelectedServices)
        bubbleGridAllRecyclerview?.layoutManager =
            GridLayoutManager(context, 3)
        bubbleGridAllRecyclerview?.adapter = allCategoriesSliderAdapter

        //Setup the onclicklistener for the first recyclerview such that an item onclick removes that item from the list
        allCategoriesSelectedServiceAdapter.setOnSettingSelectedListener(object :
            IsSelectedServiceAdapterWithoutIcon.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                !service.isSelected
                selectedServices.remove(service)
                allCategoriesSelectedServiceAdapter.notifyDataSetChanged()

            }
        })

        //Setup the bottom recyclerview onclicklistener such that clicking an item adds it to the first recyclerview if it does not already exist in that recyclerview
        allCategoriesSliderAdapter.setOnSettingSelectedListener(object :
            SliderServiceAdapter.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                if (!service.isSelected) {
                    if (selectedServices.contains(service))
                        return
                    else {
                        selectedServices.add(
                            Service(
                                service.service_id,
                                service.service_name,
                                service.isSelected,
                                service.imageResource
                            )
                        )
                    }
                    allCategoriesSelectedServiceAdapter.notifyDataSetChanged()
                    scrollToBeUsed.fullScroll(View.FOCUS_UP)
                }
            }
        })

        //Setup the onclicklistener for the carousel horizontal recyclerview such that clicking an item adds it to the first recyclerview if it does not already exist in that recyclerview
        allCategoriesCarouselAdapter.setOnSettingSelectedListener(object :
            IsNotSelectedServiceAdapter.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                if (!service.isSelected) {
                    if (selectedServices.contains(service))
                        return
                    else {
                        selectedServices.add(
                            Service(
                                service.service_id,
                                service.service_name,
                                service.isSelected,
                                service.imageResource
                            )
                        )
                    }
                    allCategoriesSelectedServiceAdapter.notifyDataSetChanged()
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