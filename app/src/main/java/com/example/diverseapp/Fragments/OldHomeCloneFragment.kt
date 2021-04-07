package com.example.diverseapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.HorizontalCardsAdapter
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import com.example.diverseapp.ViewModels.CardsViewModel
import com.example.diverseapp.ViewModels.CardsViewModelFactory
import kotlinx.android.synthetic.main.fragment_old_home_clone.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.net.UnknownHostException

//Not included in the project
class OldHomeCloneFragment : Fragment(), KodeinAware {

    private lateinit var adapterHorizontal: HorizontalCardsAdapter
    private lateinit var viewModel: CardsViewModel
    private val cardsList: ArrayList<Card> = ArrayList()
    override val kodein by closestKodein()
    private val viewModelFactory: CardsViewModelFactory by instance()

    companion object {
        fun getInstance(): OldHomeCloneFragment {
            val fragment =
                OldHomeCloneFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance() = FirstTabFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old_home_clone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        setUpRecyclerView()
        setUpViewModel()
        setPullToRefresh()
    }

    private fun setUpRecyclerView() {
        recycler_view.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapterHorizontal =
            HorizontalCardsAdapter(cardsList, object : HorizontalCardsAdapter.ICardsItemSelected {

                override fun onCardsItemsClicked(position: Int) {
//                addFragment(cardsList[position])
                    (activity as MainActivity).switch(
                        MainActivity.Companion.FragmentType.CARDDETAILS, cardsList[position]
                    )
                }

            })
        recycler_view.adapter = adapterHorizontal
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CardsViewModel::class.java)
        viewModel.card.observe(this, Observer {
            it?.let {
                if (it.throwable is UnknownHostException) {
                    no_internet_layout.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                    empty_layout.visibility = View.GONE
                } else {
                    it.data?.let {
                        if (it.isEmpty()) {
                            no_internet_layout.visibility = View.GONE
                            empty_layout.visibility = View.VISIBLE
                            recycler_view.visibility = View.GONE

                        } else {
                            no_internet_layout.visibility = View.GONE
                            recycler_view.visibility = View.VISIBLE
                            empty_layout.visibility = View.GONE
                            cardsList.clear()
                            cardsList.addAll(it)
                            adapterHorizontal.notifyDataSetChanged()

                        }
                    }
                }
                refresh.isRefreshing = false
            }
        })
    }

    private fun setPullToRefresh() {
        refresh.setOnRefreshListener {
            viewModel.getAllCards()
        }
    }

    fun addFragment(card: Card?) {
        card?.let {
            fragmentManager?.beginTransaction()!!
                .replace(
                    R.id.fragmentFirstTabLayout,
                    CardDetailsFragment.newInstance(it)
                )
                .addToBackStack("ss")
                .commit()
        }
    }

    fun setOnClickListener() {
        viewMoreTextView.setOnClickListener {
            (activity as MainActivity).switch(
                MainActivity.Companion.FragmentType.CARDSLIST, null
            )
        }
    }
}