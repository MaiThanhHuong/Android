package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import vn.edu.hust.studentman.databinding.FragmentUpdateStudentBinding

class UpdateStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()

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

        val position = arguments?.getInt("position") ?: -1
        val student = viewModel.students.value?.getOrNull(position)

        binding.etId.setText(student?.studentId ?: "")
        binding.etName.setText(student?.studentName ?: "")

        binding.btnUpdate.setOnClickListener {
            val newId = binding.etId.text.toString().trim()
            val newName = binding.etName.text.toString().trim()

            if (position >= 0 && newId.isNotEmpty() && newName.isNotEmpty()) {
                viewModel.updateStudent(position, StudentModel(newName, newId))
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}
