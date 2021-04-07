package com.example.diverseapp.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.CategoryDetailsAllAdapter
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.fragment_category_details_all.*
import java.net.URLEncoder.encode
import java.util.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.ArrayList
import android.util.Base64
import java.security.Key
import javax.crypto.Cipher

class CategoryDetailsAllFragment : Fragment() {

    companion object {
        fun getInstance(): CategoryDetailsAllFragment {
            val fragment =
                CategoryDetailsAllFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance() = CategoryDetailsAllFragment()
    }

    private lateinit var categoryDetailsAllAdapter : CategoryDetailsAllAdapter
    private val categoryDetailsAll = ArrayList<String>()
    val cipher: Cipher = Cipher.getInstance("AES")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_details_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViews()
    }

    //Setup the adapter for the recyclerview in this page
    private fun setRecyclerViews() {
        categoryDetailsAllAdapter = CategoryDetailsAllAdapter(categoryDetailsAll)
        categoryDetailsAllRecyclerView?.layoutManager = LinearLayoutManager(context)
        categoryDetailsAllRecyclerView?.adapter = categoryDetailsAllAdapter

        //Avoid duplicating the recyclerview in oncreate by calling .clear()
        categoryDetailsAll.clear()
        categoryDetailsAll.add("Lawn Starter Lawn Care")
        categoryDetailsAll.add("Outdoor Landscaping and Design")
        categoryDetailsAll.add("Math Tutoring")
        categoryDetailsAll.add("Spanish Lessons")
        categoryDetailsAll.add("Handyman")

//        fun encrypt(data: String, key: Key?, useInitializationVector: Boolean = false): String {
//            cipher.init(Cipher.ENCRYPT_MODE, key)
//
//            var result = ""
//            if (useInitializationVector) {
//                val iv = cipher.iv
//                val ivString = Base64.encodeToString(iv, Base64.DEFAULT)
//                result = ivString + "]"
//            }
//
//            val bytes = cipher.doFinal(data.toByteArray())
//            result += Base64.encodeToString(bytes, Base64.DEFAULT)
//
//            return result
//        }
//
//        fun decrypt(data: String, key: Key?, useInitializationVector: Boolean = false): String {
//            var encodedString: String
//
//            if (useInitializationVector) {
//                val split = data.split("]".toRegex())
//                if (split.size != 2) throw IllegalArgumentException("Passed data is incorrect. There was no IV specified with it.")
//
//                val ivString = split[0]
//                encodedString = split[1]
//                val ivSpec = IvParameterSpec(Base64.decode(ivString, Base64.DEFAULT))
//                cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
//            } else {
//                encodedString = data
//                cipher.init(Cipher.DECRYPT_MODE, key)
//            }
//
//            val encryptedData = Base64.decode(encodedString, Base64.DEFAULT)
//            val decodedData = cipher.doFinal(encryptedData)
//            return String(decodedData)
//        }
    }

}