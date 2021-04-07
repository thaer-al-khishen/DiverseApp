package com.example.diverseapp.Fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card_details.*

//Not included in the project
class CardDetailsFragment : Fragment() {

    private lateinit var card: Card

    companion object {
        fun newInstance(card: Card) = CardDetailsFragment().putArgs {
            putSerializable("cardDetail", card)
        }

        fun getInstance(): CardsListFragment {
            val fragment =
                CardsListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

        private inline fun <FRAGMENT : Fragment> FRAGMENT.putArgs(argsBuilder: Bundle.() -> Unit): FRAGMENT =
            this.apply { arguments = Bundle().apply(argsBuilder) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card = (arguments?.getSerializable("cardDetail") as? Card)!!
        loadDetails()
    }

    private fun loadDetails() {
        fragmentCardDetailsName.text = card.name
        fragmentCardDetailsRarity.text = card.rarity
        fragmentCardDetailsPower.text = card.power
        fragmentCardDetailsToughness.text = card.toughness
        fragmentCardDetailsFlavor.text = card.flavor
        fragmentCardDetailsArtist.text = card.artist

        if (!TextUtils.isEmpty(card.imageUrl))
            Picasso.get().load(card.imageUrl).into(fragmentCardDetailsImage)

    }

}