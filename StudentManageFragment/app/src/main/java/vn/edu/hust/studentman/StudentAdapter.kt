package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
  private val students: List<StudentModel>,
  private val onEdit: (Int) -> Unit,
  private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.text_student_name)
    val id: TextView = view.findViewById(R.id.text_student_id)
    val edit: ImageView = view.findViewById(R.id.image_edit)
    val delete: ImageView = view.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.layout_student_item, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount() = students.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val student = students[position]
    holder.name.text = student.studentName
    holder.id.text = student.studentId
    holder.edit.setOnClickListener { onEdit(position) }
    holder.delete.setOnClickListener { onDelete(position) }
  }
}
