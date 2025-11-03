package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Khai báo các view
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var birthday: EditText
    private lateinit var selectDateButton: Button
    private lateinit var calendarView: CalendarView
    private lateinit var address: EditText
    private lateinit var email: EditText
    private lateinit var termsCheck: CheckBox
    private lateinit var registerButton: Button

    // Biến để lưu background mặc định
    private var defaultEditTextBg: Drawable? = null
    private var defaultRadioGroupBg: Drawable? = null
    private var defaultCheckboxColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        firstName = findViewById(R.id.editFirstName)
        lastName = findViewById(R.id.editLastName)
        genderGroup = findViewById(R.id.radioGroupGender)
        birthday = findViewById(R.id.editBirthday)
        selectDateButton = findViewById(R.id.buttonSelectDate)
        calendarView = findViewById(R.id.calendarView)
        address = findViewById(R.id.editAddress)
        email = findViewById(R.id.editEmail)
        termsCheck = findViewById(R.id.checkBoxTerms)
        registerButton = findViewById(R.id.buttonRegister)

        defaultEditTextBg = firstName.background
        defaultRadioGroupBg = genderGroup.background
        defaultCheckboxColor = termsCheck.currentTextColor

        setupCalendar()

        registerButton.setOnClickListener {
            if (validateInputs()) {
                // Nếu tất cả đều hợp lệ
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
            } else {
                // Nếu có lỗi
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCalendar() {
        // Nhấn nút "Select" để ẩn/hiện CalendarView
        selectDateButton.setOnClickListener {
            if (calendarView.visibility == View.VISIBLE) {
                calendarView.visibility = View.GONE
            } else {
                calendarView.visibility = View.VISIBLE
            }
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            birthday.setText(selectedDate)

            calendarView.visibility = View.GONE
        }
    }

    private fun validateInputs(): Boolean {
        resetErrorBackgrounds()

        var isValid = true

        if (firstName.text.toString().trim().isEmpty()) {
            firstName.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (lastName.text.toString().trim().isEmpty()) {
            lastName.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (genderGroup.checkedRadioButtonId == -1) { // -1 nghĩa là chưa chọn cái nào
            genderGroup.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (birthday.text.toString().trim().isEmpty()) {
            birthday.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (address.text.toString().trim().isEmpty()) {
            address.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (email.text.toString().trim().isEmpty()) {
            email.setBackgroundColor(Color.RED)
            isValid = false
        }

        if (!termsCheck.isChecked) {
            termsCheck.setTextColor(Color.RED) // Đổi màu chữ của CheckBox
            isValid = false
        }

        return isValid
    }

    private fun resetErrorBackgrounds() {
        firstName.background = defaultEditTextBg
        lastName.background = defaultEditTextBg
        birthday.background = defaultEditTextBg
        address.background = defaultEditTextBg
        email.background = defaultEditTextBg

        genderGroup.background = defaultRadioGroupBg

        termsCheck.setTextColor(defaultCheckboxColor)
    }
}