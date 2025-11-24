package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
  private val students = mutableListOf(
    StudentModel("Nguyễn Văn A", "20200001"),
    StudentModel("Trần Thị B", "20200002"),
    StudentModel("Lê Văn C", "20200003"),
    StudentModel("Phạm Thị D", "20200004")
  )

  private lateinit var studentAdapter: StudentAdapter
  private lateinit var etStudentId: EditText
  private lateinit var etFullName: EditText
  private lateinit var btnAdd: Button
  private lateinit var btnUpdate: Button
  private var selectedPosition: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    etStudentId = findViewById(R.id.et_student_id)
    etFullName = findViewById(R.id.et_full_name)
    btnAdd = findViewById(R.id.btn_add)
    btnUpdate = findViewById(R.id.btn_update)
    val recyclerViewStudents = findViewById<RecyclerView>(R.id.recycler_view_students)

    btnUpdate.isEnabled = false

    studentAdapter = StudentAdapter(students, ::selectStudentForEdit, ::deleteStudent)
    recyclerViewStudents.apply {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
    }

    btnAdd.setOnClickListener {
      addStudent()
    }

    btnUpdate.setOnClickListener {
      updateStudent()
    }
  }

  private fun addStudent() {
    val name = etFullName.text.toString().trim()
    val id = etStudentId.text.toString().trim()

    if (name.isNotEmpty() && id.isNotEmpty()) {
      val newStudent = StudentModel(name, id)
      students.add(newStudent)
      studentAdapter.notifyItemInserted(students.size - 1)

      clearInputFields()
      Snackbar.make(findViewById(R.id.main), "Added $name", Snackbar.LENGTH_SHORT).show()
    } else {
      Snackbar.make(findViewById(R.id.main), "Please enter both Name and ID", Snackbar.LENGTH_SHORT).show()
    }
  }

  private fun selectStudentForEdit(position: Int) {
    val student = students[position]

    etFullName.setText(student.studentName)
    etStudentId.setText(student.studentId)

    selectedPosition = position

    btnUpdate.isEnabled = true
    btnAdd.isEnabled = false

    Snackbar.make(findViewById(R.id.main), "Ready to edit ${student.studentName}", Snackbar.LENGTH_SHORT).show()
  }

  private fun updateStudent() {
    if (selectedPosition != -1) {
      val newName = etFullName.text.toString().trim()
      val newId = etStudentId.text.toString().trim()

      if (newName.isNotEmpty() && newId.isNotEmpty()) {
        students[selectedPosition] = StudentModel(newName, newId)
        studentAdapter.notifyItemChanged(selectedPosition)

        clearInputFields()
        btnUpdate.isEnabled = false
        btnAdd.isEnabled = true
        selectedPosition = -1

        Snackbar.make(findViewById(R.id.main), "Updated student data", Snackbar.LENGTH_SHORT).show()
      } else {
        Snackbar.make(findViewById(R.id.main), "Name and ID cannot be empty", Snackbar.LENGTH_SHORT).show()
      }
    }
  }

  private fun deleteStudent(position: Int) {
    val deletedStudent = students[position]
    students.removeAt(position)
    studentAdapter.notifyItemRemoved(position)

    if (selectedPosition == position) {
      clearInputFields()
      btnUpdate.isEnabled = false
      btnAdd.isEnabled = true
      selectedPosition = -1
    }

    Snackbar.make(findViewById(R.id.main), "Deleted ${deletedStudent.studentName}", Snackbar.LENGTH_LONG)
      .setAction("Undo") {
        students.add(position, deletedStudent)
        studentAdapter.notifyItemInserted(position)
        Snackbar.make(findViewById(R.id.main), "Undo successful", Snackbar.LENGTH_SHORT).show()
      }.show()
  }

  private fun clearInputFields() {
    etFullName.text.clear()
    etStudentId.text.clear()
    etFullName.requestFocus()
  }
}