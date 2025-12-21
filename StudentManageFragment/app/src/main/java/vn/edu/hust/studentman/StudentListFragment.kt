package vn.edu.hust.studentman

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private val viewModel: StudentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        val btnAdd = view.findViewById<Button>(R.id.btn_add)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.students.observe(viewLifecycleOwner) { studentList ->
            recyclerView.adapter = StudentAdapter(
                students = studentList,
                onEdit = { position ->
                    val bundle = Bundle().apply {
                        putInt("position", position)
                    }
                    findNavController().navigate(
                        R.id.action_to_update,
                        bundle
                    )
                },
                onDelete = { position ->
                    viewModel.deleteStudent(position)
                }
            )
        }

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_to_add)
        }
    }
}
