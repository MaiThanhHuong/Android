package vn.edu.hust.studentman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import vn.edu.hust.studentman.databinding.FragmentAddStudentBinding

class AddStudentFragment : Fragment() {

    private val viewModel: StudentViewModel by activityViewModels()

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
                viewModel.addStudent(StudentModel(name, id))
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}
