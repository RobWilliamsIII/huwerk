package com.example.huwerk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.huwerk.databinding.CreateListingBinding


class CreateListingFragment : Fragment() {

    private var _binding: CreateListingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createButton.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val description = binding.descriptionInput.text.toString()
            val price = binding.priceInput.text.toString().toDoubleOrNull() ?: 0.0
            val location = binding.locationInput.text.toString()
            val imageUrl = binding.imageUrlInput.text.toString()

            val listing = Listing(title, description, price, location, imageUrl)


            parentFragmentManager.setFragmentResult("newListing", Bundle().apply {
                putSerializable("listing", listing)
            })


            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
