package com.btntrung.pointmanagement.presentation.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.btntrung.pointmanagement.R
import com.btntrung.pointmanagement.databinding.FragmentManagerMainBinding
import com.btntrung.pointmanagement.entity.Classroom
import com.btntrung.pointmanagement.entity.Semester
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ManagerMainFragment : Fragment() {
    private val viewModel: ManagerMainViewModel by viewModel()

    private lateinit var binding : FragmentManagerMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManagerMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.adapter = ClassroomListAdapter(object : ClassroomClickListener {
            override fun onClick(classroom: Classroom) {
                findNavController().navigate(ManagerMainFragmentDirections.actionManagerMainFragmentToStudentListFragment(classroom))
            }
        })
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())

        binding.btnSearch.setOnClickListener {
        }

        binding.spinnerManagerSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val semester = parent?.adapter?.getItem(position) as Semester
                viewModel.getClassrooms(semester.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        viewModel.semesters.observe(viewLifecycleOwner) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerManagerSemester.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewModel.classroom.observe(viewLifecycleOwner) {
            (binding.recycleView.adapter as ClassroomListAdapter).submitList(it)
        }
    }
}