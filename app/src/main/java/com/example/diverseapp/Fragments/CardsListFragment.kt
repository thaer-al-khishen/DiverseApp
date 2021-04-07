package com.example.diverseapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.VerticalCardsAdapter
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import com.example.diverseapp.ViewModels.CardsViewModel
import com.example.diverseapp.ViewModels.CardsViewModelFactory
import kotlinx.android.synthetic.main.fragment_cards_list.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.net.UnknownHostException

//Not included in the project
class CardsListFragment : Fragment(), KodeinAware {

    private lateinit var adapterVertical: VerticalCardsAdapter
    private lateinit var viewModel: CardsViewModel
    private val cardsList: ArrayList<Card> = ArrayList()
    override val kodein by closestKodein()
    private val viewModelFactory: CardsViewModelFactory by instance()

    companion object {
        fun getInstance(): CardsListFragment {
            val fragment =
                CardsListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance() = CardsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cards_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
        setPullToRefresh()
    }

    private fun setUpRecyclerView() {
        recycler_view_vertical_list.layoutManager = LinearLayoutManager(activity)
        adapterVertical = VerticalCardsAdapter(cardsList, object : VerticalCardsAdapter.ICardsItemSelected {

            override fun onCardsItemsClicked(position: Int) {
//                addFragment(cardsList[position])
                (activity as MainActivity).switch(
                    MainActivity.Companion.FragmentType.CARDDETAILS, cardsList[position])
            }

        })
        recycler_view_vertical_list.adapter = adapterVertical
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CardsViewModel::class.java)
        viewModel.card.observe(this, Observer {
            it?.let {
                if (it.throwable is UnknownHostException) {
                    no_internet_layout2.visibility = View.VISIBLE
                    recycler_view_vertical_list.visibility = View.GONE
                    empty_layout2.visibility = View.GONE
                } else {
                    it.data?.let {
                        if (it.isEmpty()) {
                            no_internet_layout2.visibility = View.GONE
                            empty_layout2.visibility = View.VISIBLE
                            recycler_view_vertical_list.visibility = View.GONE

                        } else {
                            no_internet_layout2.visibility = View.GONE
                            recycler_view_vertical_list.visibility = View.VISIBLE
                            empty_layout2.visibility = View.GONE
                            cardsList.clear()
                            cardsList.addAll(it)
                            adapterVertical.notifyDataSetChanged()

                        }
                    }
                }
                refresh2.isRefreshing = false
            }
        })
    }

    private fun setPullToRefresh() {
        refresh2.setOnRefreshListener {
            viewModel.getAllCards()
        }
    }

//    fun addFragment(card: Card?) {
//        card?.let {
//            fragmentManager?.beginTransaction()!!
//                .replace(
//                    R.id.fragmentFirstTabLayout,
//                    CardDetailsFragment.newInstance(it)
//                )
//                .addToBackStack("ss")
//                .commit()
//        }
//    }
}