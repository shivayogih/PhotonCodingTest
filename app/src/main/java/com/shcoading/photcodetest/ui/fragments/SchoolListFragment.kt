package com.shcoading.photcodetest.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shcoading.photcodetest.R
import com.shcoading.photcodetest.api_services.Resource
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem
import com.shcoading.photcodetest.databinding.FragmentSchoolListBinding
import com.shcoading.photcodetest.ui.adaptors.SchoolListAdapter
import com.shcoading.photcodetest.viewModels.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolListFragment : Fragment() {

    private lateinit var binding: FragmentSchoolListBinding

    private val viewModel: SchoolViewModel by viewModels()

    private var schoolList: List<SchoolListResponseItem>? = listOf()

    var adapterSchool: SchoolListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterSchool = SchoolListAdapter(requireActivity(), listOf())
        binding.rvSchools.adapter = adapterSchool

        adapterSchool?.setOnItemClickListener { position, school ->
            school.let {

                val args = bundleOf("school" to it.toJSON())
                findNavController().navigate(
                    R.id.action_schoolListFragment_to_schoolDetailsFragment, args
                )

            }

        }

        viewModel.getSchoolList(requireActivity())

        viewModel.schoolListResponse.observe(viewLifecycleOwner, Observer { it ->
            try {
                when (it) {
                    is Resource.Success -> {
                        hideDialog()
                        it.data?.let {
                            schoolList = it
                            showSchoolList()
                        }
                    }
                    is Resource.Error -> {
                        hideDialog()
                        schoolList = listOf()
                    }
                    is Resource.Loading -> {
                        showDialog()
                        schoolList = listOf()
                    }
                    is Resource.NoNetworkConnection -> {
                        hideDialog()
                        Toast.makeText(requireActivity(), "response timeout", Toast.LENGTH_SHORT)
                            .show()
                        schoolList = listOf()
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.message?.let { it1 -> Log.e("Error", it1) }
            }
        })
    }

    private fun showDialog() {
        binding.imgPg.visibility = View.VISIBLE
    }

    private fun hideDialog() {
        binding.imgPg.visibility = View.GONE
    }


    fun showSchoolList() {
        schoolList?.let {

            val count=it.size
            binding.tvCount.text = "Count: $count"
            adapterSchool?.items = it
            adapterSchool?.notifyDataSetChanged()
        }
    }

}