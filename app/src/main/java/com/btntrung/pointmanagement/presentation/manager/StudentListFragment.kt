package com.btntrung.pointmanagement.presentation.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.btntrung.pointmanagement.R
import com.btntrung.pointmanagement.databinding.FragmentStudentListBinding
import com.btntrung.pointmanagement.entity.Student
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class StudentListFragment : Fragment() {
    private val viewModel : StudentListViewModel by viewModel()
    private val args : StudentListFragmentArgs by navArgs()
    private lateinit var binding : FragmentStudentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)

        viewModel.getStudents(args.classroom.id)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerStudentList.adapter = StudentListAdapter(object : StudentClickListener {
            override fun onClick(student: Student) {
                Timber.d("click student")
            }
        })
        binding.recyclerStudentList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.students.observe(viewLifecycleOwner) {
            (binding.recyclerStudentList.adapter as StudentListAdapter).submitList(it)
        }
    }
}