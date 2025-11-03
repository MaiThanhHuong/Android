package com.example.calculator_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var result: TextView
    private var currentExpression = StringBuilder()
    private var justCalculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        result = findViewById(R.id.result)

        // Đặt hiển thị ban đầu
        display.text = "0"
        result.text = ""

        val buttonIds = intArrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonDot
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                val buttonText = button.text.toString()

                if (justCalculated) {
                    // Nếu vừa nhấn "="
                    justCalculated = false // Reset cờ

                    if (buttonText in listOf("+", "-", "*", "/", "^")) {
                        // Nếu nhấn phép toán: Lấy kết quả cũ làm vế đầu
                        currentExpression = StringBuilder(result.text.toString())
                    } else {
                        // Nếu nhấn số: Bắt đầu phép tính mới
                        currentExpression.clear()
                    }
                    result.text = "" // Xóa kết quả cũ
                }

                // Xử lý trường hợp nhập số 0 đầu tiên
                if (currentExpression.toString() == "0" && buttonText != ".") {
                    currentExpression.clear()
                }

                currentExpression.append(buttonText)
                display.text = currentExpression.toString()
            }
        }

        // Nút CE - Clear All
        findViewById<Button>(R.id.buttonCE).setOnClickListener {
            currentExpression.clear()
            display.text = "0"
            result.text = ""
            justCalculated = false
        }

        // Nút C - Clear Memory (giống CE)
        findViewById<Button>(R.id.buttonC).setOnClickListener {
            currentExpression.clear()
            display.text = "0"
            result.text = ""
            justCalculated = false
        }

        // Nút BS - Backspace
        findViewById<Button>(R.id.buttonBS).setOnClickListener {
            if (justCalculated) {
                justCalculated = false
                result.text = ""
            }

            if (currentExpression.isNotEmpty()) {
                currentExpression.deleteCharAt(currentExpression.length - 1)
                display.text = if (currentExpression.isEmpty()) "0" else currentExpression.toString()
            } else {
                display.text = "0"
            }
            result.text = "" // Luôn xóa kết quả nếu đang sửa
        }

        // Nút +/-
        findViewById<Button>(R.id.buttonExponent).setOnClickListener {
            // 1. Trường hợp: Vừa nhấn "=" (justCalculated == true)
            // Ta sẽ đổi dấu của KẾT QUẢ.
            if (justCalculated) {
                var currentResult = result.text.toString()
                // Không làm gì nếu kết quả rỗng hoặc là "0"
                if (currentResult.isEmpty() || currentResult == "0") return@setOnClickListener

                currentResult = if (currentResult.startsWith("-")) {
                    currentResult.substring(1) // Bỏ dấu -
                } else {
                    "-$currentResult" // Thêm dấu -
                }

                result.text = currentResult
                currentExpression = StringBuilder(currentResult) // Cập nhật biểu thức
                display.text = currentExpression.toString() // Cập nhật hiển thị
                return@setOnClickListener
            }

            // 2. Trường hợp: Đang gõ biểu thức
            if (currentExpression.isEmpty()) {
                currentExpression.append("-")
                display.text = currentExpression.toString()
                return@setOnClickListener
            }

            val operators = charArrayOf('+', '*', '/') // Các toán tử ưu tiên (không có '-')
            var lastOpIndex = currentExpression.lastIndexOfAny(operators)

            val lastMinusIndex = currentExpression.lastIndexOf('-')

            if (lastMinusIndex > lastOpIndex) {
                // Kiểm tra dấu '-'
                val charBeforeMinus = currentExpression.getOrNull(lastMinusIndex - 1)
                if (charBeforeMinus == null || charBeforeMinus in operators || lastMinusIndex == 0) {
                    // Đây là dấu của số
                    // lastOpIndex giữ nguyên
                    if(lastMinusIndex == 0) {
                        lastOpIndex = -1
                    }
                } else {
                    // Đây là toán tử trừ
                    lastOpIndex = lastMinusIndex
                }
            }

            // Điểm bắt đầu của số cuối cùng
            val numberStartIndex = lastOpIndex + 1

            if (numberStartIndex >= currentExpression.length) {
                // Trường hợp "10+", nhấn "+/-" thành "10+-"
                currentExpression.append("-")
            } else if (currentExpression[numberStartIndex] == '-') {
                // Trường hợp "10+-5" hoặc "-5", nhấn "+/-" để xóa dấu "-"
                currentExpression.deleteCharAt(numberStartIndex)
            } else {
                // Trường hợp "10+5" hoặc "5", nhấn "+/-" để thêm dấu "-"
                currentExpression.insert(numberStartIndex, "-")
            }

            display.text = currentExpression.toString()
            result.text = "" // Xóa kết quả
            justCalculated = false
        }

        // Nút = - Tính toán kết quả
        findViewById<Button>(R.id.buttonEqual).setOnClickListener {
            if (currentExpression.isEmpty()) {
                result.text = "0" // Nếu không có gì, kết quả là 0
                return@setOnClickListener
            }

            try {
                val expression: Expression = ExpressionBuilder(currentExpression.toString()).build()
                val resultValue = expression.evaluate()

                // Định dạng kết quả (số nguyên hoặc số thực)
                val formattedResult = if (resultValue == resultValue.toInt().toDouble()) {
                    resultValue.toInt().toString()
                } else {
                    resultValue.toString()
                }

                result.text = formattedResult

                justCalculated = true
            } catch (_: Exception) {
                result.text = getString(R.string.calculation_error)
                justCalculated = true
            }
        }
    }
}