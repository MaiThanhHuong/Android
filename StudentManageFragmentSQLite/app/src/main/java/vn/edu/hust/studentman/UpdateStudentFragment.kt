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
import vn.edu.hust.studentman.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentUpdateStudentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_update_student,
            container,
            false
        )

        val studentId = arguments?.getString("studentId") ?: ""
        val studentName = arguments?.getString("studentName") ?: ""

        binding.etId.setText(studentId)
        binding.etName.setText(studentName)

        binding.btnUpdate.setOnClickListener {
            val newName = binding.etName.text.toString().trim()
            val newId = binding.etId.text.toString().trim()

            if (newName.isNotEmpty() && newId.isNotEmpty()) {

                val updatedStudent = StudentModel(newId, newName)
                viewModel.updateStudent(updatedStudent)

                Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}