package com.example.huwerk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.huwerk.databinding.ListingDetailsBinding
import com.squareup.picasso.Picasso

class ListingDetailsFragment : Fragment() {

    private var _binding: ListingDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)




        val listing = arguments?.getSerializable("listing") as? Listing


        if (listing != null) {
            binding.listingTitle.text = listing.title
            binding.listingDescription.text = listing.description
            binding.listingPrice.text = listing.price.toString()
            binding.listingLocation.text = listing.location


            Picasso.get().load(listing.imageUrl).into(binding.listingImage)
        }


        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
