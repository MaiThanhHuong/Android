package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import vn.edu.hust.studentman.databinding.FragmentAddStudentBinding

class AddStudentFragment : Fragment() {
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddStudentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_student,
            container,
            false
        )

        binding.btnAdd.setOnClickListener {
            val id = binding.etId.text.toString().trim()
            val name = binding.etName.text.toString().trim()

            if (id.isNotEmpty() && name.isNotEmpty()) {
                val newStudent = StudentModel(studentId = id, studentName = name)

                viewModel.addStudent(newStudent)

                Toast.makeText(requireContext(), "Đã thêm sinh viên: $name", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ MSSV và Tên", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}