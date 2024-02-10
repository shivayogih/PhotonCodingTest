package com.shcoading.photcodetest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shcoading.photcodetest.dataModels.SchoolListResponseItem
import androidx.navigation.fragment.navArgs
import com.shcoading.photcodetest.app.toObject
import com.shcoading.photcodetest.databinding.FragmentSchoolDetailsBinding


class SchoolDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSchoolDetailsBinding

    // private val viewModel: SchoolViewModel by viewModels()
    var school: SchoolListResponseItem? = null

    val args: SchoolDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.school.let {
            school = args.school.toObject<SchoolListResponseItem>()
            showDetails(school)
        }
    }

    private fun showDetails(school: SchoolListResponseItem?) {
        try {
            school?.let {
                binding.tvName.text = it.school_name
                binding.tvDBN.text = "DBN:" + it.dbn
                binding.tvDetails.text = it.overview_paragraph
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}