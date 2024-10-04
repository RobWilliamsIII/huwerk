import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.huwerk.databinding.GuestWelcomeBinding
import com.example.huwerk.GuestListingsAdapter
import com.example.huwerk.Listing
import com.example.huwerk.ListingDetailsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.huwerk.RetrofitClientInstance
import com.example.huwerk.ListingApiService
import com.example.huwerk.R

class GuestWelcomeFragment : Fragment() {

    private var _binding: GuestWelcomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: GuestListingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GuestWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.guestListingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch listings from the backend
        fetchListings()
    }

    private fun fetchListings() {
        val retrofitService = RetrofitClientInstance.retrofitInstance.create(ListingApiService::class.java)
        retrofitService.getListings().enqueue(object : Callback<List<Listing>> {
            override fun onResponse(call: Call<List<Listing>>, response: Response<List<Listing>>) {
                if (response.isSuccessful) {
                    val listings = response.body() ?: emptyList()

                    adapter = GuestListingsAdapter(listings) { listing ->
                        navigateToListingDetails(listing) // Navigate to details screen
                    }
                    binding.guestListingsRecyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Listing>>, t: Throwable) {
            }
        })
    }

    private fun navigateToListingDetails(listing: Listing) {
        val listingDetailsFragment = ListingDetailsFragment()


        val bundle = Bundle()
        bundle.putSerializable("listing", listing)
        listingDetailsFragment.arguments = bundle


        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listingDetailsFragment)
            .addToBackStack(null) // Add to back stack
            .commit()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
