package com.villadevs.sportslistdetailpattern

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.villadevs.sportslistdetailpattern.databinding.FragmentNewsDetailsBinding
import com.villadevs.sportslistdetailpattern.databinding.FragmentSportsListBinding
import com.villadevs.sportslistdetailpattern.viewmodel.SportsViewModel


private const val ARG_PARAM1 = "param1"

class NewsDetailsFragment : Fragment() {

    private val sharedViewModel: SportsViewModel by activityViewModels()

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        sharedViewModel.currentSport.observe(viewLifecycleOwner) {
            binding.titleDetail.text = getString(it.titleResourceId)
            binding.sportsImageDetail.load(it.sportsImageBanner)
            binding.newsDetail.text = getString(it.newsDetails)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}