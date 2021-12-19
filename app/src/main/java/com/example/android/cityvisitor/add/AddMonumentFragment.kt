package com.example.android.cityvisitor.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.android.cityvisitor.R
import com.example.android.cityvisitor.databinding.AddMonumentFragmentBinding
import com.google.android.material.snackbar.Snackbar

class AddMonumentFragment : Fragment() {

    private val viewModel: AddMonumentViewModel by lazy {
        ViewModelProvider(this).get(AddMonumentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = AddMonumentFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.application_submitted),
                    Snackbar.LENGTH_LONG // How long to display the message.
                ).show()
                viewModel.doneShowingSnackbar()

                binding.EditTextCategory.text.clear()
                binding.EditTextInfo.text.clear()
                binding.EditTextNameMonument.text.clear()
                binding.EditTextWebsite.text.clear()
                binding.EditTextLat.text.clear()
                binding.EditTextLng.text.clear()
                binding.EditTextRate.text.clear()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

}
