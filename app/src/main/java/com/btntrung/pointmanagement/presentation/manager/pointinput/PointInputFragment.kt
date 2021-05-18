package com.btntrung.pointmanagement.presentation.manager.pointinput

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.btntrung.pointmanagement.R
import com.btntrung.pointmanagement.databinding.FragmentPointInputBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointInputFragment : Fragment() {
    private val viewModel : PointInputViewModel by viewModel()
    private val args : PointInputFragmentArgs by navArgs()

    private lateinit var binding : FragmentPointInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPointInputBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.student.value = args.student

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSubjectDetail(args.subjectId)

        binding.buttonSavePoint.setOnClickListener {
            viewModel.savePoint(args.student.uid, args.semesterId, args.managerId) //TODO: REMOVE HARDCODE
        }

        viewModel.savePointResponse.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(PointInputFragmentDirections.actionPointInputFragmentToManagerMainFragment())
                Toast.makeText(requireContext(), "Update point success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}