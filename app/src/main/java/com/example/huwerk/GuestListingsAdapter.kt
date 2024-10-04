package com.example.huwerk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.huwerk.databinding.ListingItemBinding
import com.squareup.picasso.Picasso
import com.example.huwerk.Listing
import android.util.Log

class GuestListingsAdapter(
    private val listings: List<Listing>,
    private val onItemClick: (Listing) -> Unit
) : RecyclerView.Adapter<GuestListingsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listing: Listing) {
            binding.title.text = listing.title
            binding.description.text = listing.description
            binding.price.text = listing.price.toString()
            binding.location.text = listing.location


            Picasso.get().load(listing.imageUrl).into(binding.listingImage)


            binding.root.setOnClickListener {
                onItemClick(listing)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListingItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listings[position])
    }

    override fun getItemCount(): Int {
        return listings.size
    }
}


