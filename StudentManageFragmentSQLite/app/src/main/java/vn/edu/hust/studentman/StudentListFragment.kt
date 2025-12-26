package vn.edu.hust.studentman

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private val viewModel: StudentViewModel by viewModels()
    private lateinit var studentAdapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        val btnAdd = view.findViewById<Button>(R.id.btn_add)

        studentAdapter = StudentAdapter(
            students = emptyList(),
            onEdit = { position ->
                val student = viewModel.allStudents.value?.get(position)
                student?.let {
                    val bundle = Bundle().apply {
                        putString("studentId", it.studentId)
                        putString("studentName", it.studentName)
                    }
                    findNavController().navigate(R.id.action_to_update, bundle)
                }
            },
            onDelete = { position ->
                val studentToDelete = viewModel.allStudents.value?.get(position)
                studentToDelete?.let {
                    viewModel.deleteStudent(it)
                }
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = studentAdapter
        }

        viewModel.allStudents.observe(viewLifecycleOwner) { studentList ->
            studentAdapter.updateData(studentList)
        }

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_to_add)
        }
    }
}