package com.example.prac_work_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity(), View.OnClickListener {

    lateinit var btnRes : Button
    lateinit var btnBack : Button
    lateinit var etHp : EditText // Вхідні дані Hp
    lateinit var etCp : EditText // Cp
    lateinit var etSp : EditText // Sp
    lateinit var etNp : EditText // Np
    lateinit var etOp : EditText // Op
    lateinit var etWp : EditText // Wp
    lateinit var etAp : EditText // Ap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRes = findViewById(R.id.btn_res)
        btnBack = findViewById(R.id.btn_back)
        etHp = findViewById(R.id.et_hp)
        etCp = findViewById(R.id.et_cp)
        etSp = findViewById(R.id.et_sp)
        etNp = findViewById(R.id.et_np)
        etOp = findViewById(R.id.et_op)
        etWp = findViewById(R.id.et_wp)
        etAp = findViewById(R.id.et_ap)
        btnRes.setOnClickListener(this)
        btnBack.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        val hp = etHp.text.toString().toDouble()
        val cp = etCp.text.toString().toDouble()
        val sp = etSp.text.toString().toDouble()
        val np = etNp.text.toString().toDouble()
        val op = etOp.text.toString().toDouble()
        val wp = etWp.text.toString().toDouble()
        val ap = etAp.text.toString().toDouble()

        when(v?.id){
            R.id.btn_res -> {
                // Розрахунок коефіцієнтів
                val krs = 100 / (100 - wp)
                val krg = 100 / (100 - wp - ap)

                // Розрахунок складу сухої маси палива
                val hS = hp * krs
                val cS = cp * krs
                val sS = sp * krs
                val nS = np * krs
                val oS = op * krs
                val aS = ap * krs

                // Розрахунок складу горючої маси палива
                val hG = hp * krg
                val cG = cp * krg
                val sG = sp * krg
                val nG = np * krg
                val oG = op * krg

                // Розрахунок нижчої теплоти згорання для робочої маси
                val qrn = (339 * cp + 1030 * hp - 108.8 * (op - sp) - 25 * wp) / 1000
                val qrg = (qrn + 0.025 * wp) * (100 / (100 - wp - ap))

                // Розрахунок нижчої теплоти згорання для сухої маси
                val qd = (qrn + 0.025 * wp) * (100 / (100 - wp))

                fun Double.round(decimals: Int): Double {
                    return "%.${decimals}f".format(this).toDouble()
                }

                val resultText = """
                    Коефіцієнт переходу від робочої до сухої маси = ${krs.round(3)}
                    Коефіцієнт переходу від робочої до горючої маси = ${krg.round(3)}
                
                    Склад сухої маси палива:
                    Hc = ${hS.round(2)} %
                    Cc = ${cS.round(2)} %
                    Ss = ${sS.round(2)} %
                    Ns = ${nS.round(2)} %
                    Os = ${oS.round(2)} %
                    As = ${aS.round(2)} %
                
                    Склад горючої маси палива:
                    Hc = ${hG.round(2)} %
                    Cc = ${cG.round(2)} %
                    Ss = ${sG.round(2)} %
                    Ns = ${nG.round(2)} %
                    Os = ${oG.round(2)} %
                
                    Нижча теплота згоряння:
                    Робоча маса = ${qrn.round(3)} МДж/кг
                    Суха маса = ${qd.round(3)} МДж/кг
                    Горюча маса = ${qrg.round(3)} МДж/кг
                """.trimIndent()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT_TEXT", resultText)
                startActivity(intent)
            }
            R.id.btn_back -> {
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
