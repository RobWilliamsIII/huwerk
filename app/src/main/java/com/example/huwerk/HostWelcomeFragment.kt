package com.example.huwerk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.huwerk.databinding.HostWelcomeBinding

class HostWelcomeFragment : Fragment() {

    private var _binding: HostWelcomeBinding? = null
    private val binding get() = _binding!!
    private val listings = mutableListOf<Listing>()
    private lateinit var adapter: ListingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HostWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = ListingsAdapter(listings)
        binding.hostListingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.hostListingsRecyclerView.adapter = adapter


        parentFragmentManager.setFragmentResultListener("newListing", this) { _, bundle ->
            val listing = bundle.getSerializable("listing") as Listing
            listings.add(listing)
            adapter.notifyItemInserted(listings.size - 1)
        }


        binding.createListingButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreateListingFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
