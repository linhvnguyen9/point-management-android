package com.btntrung.pointmanagement.presentation.manager.studentlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.btntrung.pointmanagement.databinding.FragmentStudentListBinding
import com.btntrung.pointmanagement.entity.Student
import com.btntrung.pointmanagement.presentation.manager.StudentClickListener
import com.btntrung.pointmanagement.presentation.manager.StudentListAdapter
import com.google.firebase.auth.FirebaseAuth
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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.getStudents(args.classroom.id)
        viewModel.classroom.value = args.classroom

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerStudentList.adapter = StudentListAdapter(object : StudentClickListener {
            override fun onClick(student: Student) {
                val managerId = FirebaseAuth.getInstance().currentUser.uid
                findNavController().navigate(StudentListFragmentDirections.actionStudentListFragmentToPointInputFragment(args.classroom.subjectId, student, managerId, args.semesterId))
            }
        })
        binding.recyclerStudentList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.students.observe(viewLifecycleOwner) {
            (binding.recyclerStudentList.adapter as StudentListAdapter).submitList(it)
        }
    }
}