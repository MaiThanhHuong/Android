package vn.edu.hust.studentman

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    val students = MutableLiveData<MutableList<StudentModel>>(
        mutableListOf(
            StudentModel("Nguyễn Văn A", "20200001"),
            StudentModel("Trần Thị B", "20200002"),
            StudentModel("Lê Văn C", "20200003"),
            StudentModel("Phạm Thị D", "20200004")
        )
    )

    fun addStudent(student: StudentModel) {
        students.value?.add(student)
        students.value = students.value
    }

    fun updateStudent(position: Int, student: StudentModel) {
        students.value?.set(position, student)
        students.value = students.value
    }

    fun deleteStudent(position: Int) {
        students.value?.removeAt(position)
        students.value = students.value
    }
}
