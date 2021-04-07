package com.example.diverseapp.Fragments

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.IsNotSelectedServiceAdapter
import com.example.diverseapp.Adapters.IsSelectedServiceAdapterWithIcon
import com.example.diverseapp.Models.Service
import com.example.diverseapp.R
import com.example.diverseapp.Utils.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_carousel.*
import java.util.concurrent.CopyOnWriteArrayList

// After going to the category details activity, click on the tabview that says carousel to navigate to this fragment
class CarouselFragment : Fragment() {

    companion object {
        fun getInstance(): CarouselFragment {
            val fragment =
                CarouselFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    //Initialize the selected items adapter for the recyclerview that contains an icon for each selected item
    private lateinit var isSelectedServiceAdapterWithIcon: IsSelectedServiceAdapterWithIcon

    //Initialize the adapter for the carousel horizontal recyclerview that contains the items that have not been selected yet
    private lateinit var isNotSelectedServiceAdapter: IsNotSelectedServiceAdapter

    //The arraylist for the items in the carousel horizontal recyclerview
    private var services = CopyOnWriteArrayList<Service>()

    //The arraylist for the selected items recyclerview that the user populates with the onclick
    private var services2 = CopyOnWriteArrayList<Service>()

    //An arraylist containing more items than the others, this was for demonstration purposes and was used for the searchview suggestions
    //This was used to prove that the items in the suggestions can be different from the items in the other recyclerview
    private var allServices = CopyOnWriteArrayList<Service>()

    //An arraylist of strings to be populated by the names of all the services so that they can be checked later
    private var suggestions = CopyOnWriteArrayList<String>()

    //An arraylist that was used to avoid the concurrentException that happens when you act on an arraylist while iterating it
    private var services3 = CopyOnWriteArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carousel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViews()
        setSearchView()
    }

    private fun setSearchView() {

        //Populate the searchview with suggestions
        testSearchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(
            context,
            R.layout.search_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        for (item in allServices) {
            suggestions.add(item.service_name)
        }

        testSearchView.suggestionsAdapter = cursorAdapter
        testSearchView.queryHint = "Search ..."
        testSearchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            //Control what happens when you click on a suggestion
            //In our case, a click on a suggestion adds an item to the services2 arraylist to be displayed in the selected items recyclerview if it doesn't already exist in that recyclerview
            override fun onSuggestionClick(position: Int): Boolean {
                hideKeyboard()
                val cursor = testSearchView.suggestionsAdapter.getItem(position) as Cursor
                val selection =
                    cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                testSearchView.setQuery(selection, false)

                //Prepopulate services3 with all the items in services 2
                for (item in services2) {
                    services3.add(item.service_name)
                    isSelectedServiceAdapterWithIcon.notifyDataSetChanged()
                }

                //Populating the first item in the arraylist
                if (services2.size == 0) {
                    if (selection.equals("Mechanic"))//mechanic
                        services2.add(Service(1, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Repair"))//repair
                        services2.add(Service(2, selection.toString(), false, R.drawable.maintenanceimage))
                    else if (selection.equals("Gardener"))//gardener
                        services2.add(Service(3, selection.toString(), false, R.drawable.lawnmowingimage))
                    else if (selection.equals("Housekeeper"))//housekeeper
                        services2.add(Service(4, selection.toString(), false, R.drawable.vacuumimage))
                    else if (selection.equals("Painter"))
                        services2.add(Service(5, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Window Repair"))
                        services2.add(Service(6, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Small Appliance Repair"))
                        services2.add(Service(7, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Tile Installation"))
                        services2.add(Service(8, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Electrician"))
                        services2.add(Service(9, selection.toString(), false, R.drawable.mechanicimage))
                    else if (selection.equals("Construction Worker"))
                        services2.add(Service(10, selection.toString(), false, R.drawable.mechanicimage))
                    isSelectedServiceAdapterWithIcon.notifyDataSetChanged()
                    isNotSelectedServiceAdapter.notifyDataSetChanged()
                } else {

                    //Populating every following item in the arraylist if it doesn't already exist
                    if (!services3.contains(selection))
                        if (selection.equals("Mechanic"))//mechanic
                            services2.add(Service(1, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Repair"))//repair
                            services2.add(Service(2, selection.toString(), false, R.drawable.maintenanceimage))
                        else if (selection.equals("Gardener"))//gardener
                            services2.add(Service(3, selection.toString(), false, R.drawable.lawnmowingimage))
                        else if (selection.equals("Housekeeper"))//housekeeper
                            services2.add(Service(4, selection.toString(), false, R.drawable.vacuumimage))
                        else if (selection.equals("Painter"))
                            services2.add(Service(5, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Window Repair"))
                            services2.add(Service(6, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Small Appliance Repair"))
                            services2.add(Service(7, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Tile Installation"))
                            services2.add(Service(8, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Electrician"))
                            services2.add(Service(9, selection.toString(), false, R.drawable.mechanicimage))
                        else if (selection.equals("Construction Worker"))
                            services2.add(Service(10, selection.toString(), false, R.drawable.mechanicimage))
                    isSelectedServiceAdapterWithIcon.notifyDataSetChanged()
                    services3.clear()
                }
                return true
            }
        })
        testSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }

            //Filter the suggestions according to user input
            override fun onQueryTextChange(query: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }
        })
    }

    private fun setRecyclerViews() {

        //Populate the arraylist of the carousel horizontal recyclerview
        services.add(Service(1, "Mechanic", false, R.drawable.mechanicimage))//mechanic
        services.add(Service(2, "Repair", false, R.drawable.maintenanceimage))//repair
        services.add(Service(3, "Gardener", false, R.drawable.lawnmowingimage))//gardener
        services.add(Service(4, "Housekeeper", false, R.drawable.vacuumimage))//housekeeper

        //Populate the arraylist of the suggestions
        allServices.add(Service(1, "Mechanic", false, R.drawable.mechanicimage))//mechanic
        allServices.add(Service(2, "Repair", false, R.drawable.maintenanceimage))//repair
        allServices.add(Service(3, "Gardener", false, R.drawable.lawnmowingimage))//gardener
        allServices.add(Service(4, "Housekeeper", false, R.drawable.vacuumimage))//housekeeper
        allServices.add(Service(5, "Painter", false, R.drawable.mechanicimage))
        allServices.add(Service(6, "Window Repair", false, R.drawable.mechanicimage))
        allServices.add(Service(7, "Small Appliance Repair", false, R.drawable.mechanicimage))
        allServices.add(Service(8, "Tile Installation", false, R.drawable.mechanicimage))
        allServices.add(Service(9, "Electrician", false, R.drawable.mechanicimage))
        allServices.add(Service(10, "Construction Worker", false, R.drawable.mechanicimage))

        //Setup adapter for the selected items recyclerview at the top
        isSelectedServiceAdapterWithIcon = IsSelectedServiceAdapterWithIcon(services2)
        isSelectedRecyclerview?.layoutManager =
            GridLayoutManager(context, 3)
        isSelectedRecyclerview?.adapter = isSelectedServiceAdapterWithIcon

        //Setup the adapter for the carousel horizontal recyclerview
        isNotSelectedServiceAdapter = IsNotSelectedServiceAdapter(services)
        notSelectedRecyclerview?.layoutManager =
            CustomLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        notSelectedRecyclerview?.adapter = isNotSelectedServiceAdapter

        //Remove an item from the selected items recyclerview after clicking on the X icon
        isSelectedServiceAdapterWithIcon.setOnSettingSelectedListener(object :
            IsSelectedServiceAdapterWithIcon.ISettingSelected {
            override fun onItemClicked(position: Int, service: Service) {
                !service.isSelected
                services2.remove(service)
                isSelectedServiceAdapterWithIcon.notifyDataSetChanged()
                isNotSelectedServiceAdapter.notifyDataSetChanged()

            }
        })

        //After clicking on an item in the carousel horizontal recyclerview, add that item to the selected items recyclerview if it doesn't already exist
        isNotSelectedServiceAdapter.setOnSettingSelectedListener(object :
            IsNotSelectedServiceAdapter.ISettingSelected {
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
                    isSelectedServiceAdapterWithIcon.notifyDataSetChanged()
                    isNotSelectedServiceAdapter.notifyDataSetChanged()
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