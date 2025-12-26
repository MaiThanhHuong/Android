package vn.edu.hust.studentman

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val studentDao = StudentDatabase.getDatabase(application).studentDao()

    val allStudents: LiveData<List<StudentModel>> = studentDao.getAllStudents()

    fun addStudent(student: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.insertStudent(student)
        }
    }

    fun updateStudent(student: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.updateStudent(student)
        }
    }

    fun deleteStudent(student: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            studentDao.deleteStudent(student)
        }
    }
}