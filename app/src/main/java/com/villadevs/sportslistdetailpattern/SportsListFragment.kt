package com.villadevs.sportslistdetailpattern

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.villadevs.sportslistdetailpattern.adapter.SportsAdapter
import com.villadevs.sportslistdetailpattern.data.SportsData
import com.villadevs.sportslistdetailpattern.databinding.FragmentSportsListBinding
import com.villadevs.sportslistdetailpattern.viewmodel.SportsViewModel

private const val ARG_PARAM1 = "param1"

class SportsListFragment : Fragment() {

    private val sharedViewModel: SportsViewModel by activityViewModels()

    private var _binding: FragmentSportsListBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
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
        _binding = FragmentSportsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = SportsAdapter {
            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            sharedViewModel.updateCurrentSport(it)
            // Navigate to the details screen
            val action = SportsListFragmentDirections.actionSportsListFragmentToNewsDetailsFragment()
            this.findNavController().navigate(action)
        }


        binding.recyclerView.adapter = adapter
        adapter.submitList(sharedViewModel.sportsData)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}