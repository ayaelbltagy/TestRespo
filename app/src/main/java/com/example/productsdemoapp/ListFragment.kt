package com.example.productsdemoapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productsdemoapp.databinding.FragmentListBinding
import com.example.productsdemoapp.viewModels.ProductViewModel


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        binding.viewModel = viewModel
        // call adapter and send list to view it
        // setup your adapter
        var adapter = ProductsAdapter(ClickListener{
            viewModel.saveThisProduct(it)
        })
        // show dialog till api get response
        binding.statusLoadingWheel.visibility = View.VISIBLE
        binding.asteroidRecycler.adapter = adapter
        viewModel.showedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                // hide dialog as list is ready
                binding.statusLoadingWheel.visibility = View.GONE
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // to handle back button press
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
            activity?.finish()
            false
        }


    }
}