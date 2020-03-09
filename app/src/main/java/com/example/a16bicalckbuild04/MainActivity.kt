package com.example.a16bicalckbuild04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //обработчик нажатий setOnClickListener, вывод символа в функцию setTextField
        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }
        btn_min.setOnClickListener { setTextFields("-") }
        btn_slo.setOnClickListener { setTextFields("+") }
        btn_um.setOnClickListener { setTextFields("*") }
        btn_del.setOnClickListener { setTextFields("/") }
        btn_opn.setOnClickListener { setTextFields("(") }
        btn_cls.setOnClickListener { setTextFields(")") }

        // функция очистки обоих полей ввода и вывода результата
        btn_ac.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }
        //функция удаления только одного символа
        btn_bck.setOnClickListener {
            val str = math_operation.text.toString() // привели значение к типу ToString
            //проверяем
            if (str.isNotEmpty()) // если эта строка будет не пустой с помощью функции isNotEmpty
                math_operation.text = str.substring(0, str.length - 1) //то выводим значение в окно ввода данных из переменной str при помощи параметра substring
            // которая позволяет обрезать строку с 0 символа по символ length - 1 (предпоследним)
            // дополнительно очищаем поле вывода результата
            result_text.text = ""
        }

        btn_rwn.setOnClickListener {
          try { //форматирование резульата ввода окна math_operation в string для дальнейшего вычисления с помошью класса ExpressionBuilder
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
              // вывод результата в переменую result
              // но есть косяк, ввыодится число с точкой и  нулем на конце если оно целое.
                val result = ex.evaluate()
                // исправление косяка, переводим результат из переменной result в тип данных toLong в переменную longRes
                // тем самым мы избавляемся ".0" вконце числа
                val longRes = result.toLong()
                // проверка на то если в друг у нас число окажется не целым и мы не округлили результат с помощью toLong
                if (result == longRes.toDouble()) // если значение result равно значению longRes.toDouble
                    result_text.text = longRes.toString() // то выводим округленную версию числа
                else
                    result_text.text = result .toString() // иначе выводим число с дробной частью как оно и есть
          } catch (e:Exception) {
              //вывод в териминал ошибок.
              Log.d("Ошибка","сообщение: ${e.message}") // выводим в лог из переменное e.message из класса Exception который проверяет ошибки выполнения операций
              // проверяем деление на ноль
              if ( e.message == "Division by zero!") // если значение переменной e.message "Division by zero!"
              {   // то выводим системным сообщением делить на ноль нельзя
                  val text = "На ноль делить нельзя!"
                  val duration = Toast.LENGTH_SHORT
                  // вывод метода
                  val toast = Toast.makeText(applicationContext, text, duration)
                  toast.show()
              }
          }
        }
    }
        //вывод из функции setTextFields из переменной str типа string которая хранит в себе результат ввода пользователя
        //которая в свою очереь передает результат в окно ввода math_operation
    fun setTextFields(str: String) {
            //условие на проверку повторной-добавочной математической операции
        if (result_text.text != "") { // если значение окна ввывода результата result_text не ровна пустому значению
            math_operation.text = result_text.text //  то мы переносим значение  из окна результата result_text.text, в окно ввода значение math_operation.text
            result_text.text = "" // и вычищаем значение вывода результата result_text.text
        }
        //ввывод в строку ввода
        math_operation.append(str)
    }
}
